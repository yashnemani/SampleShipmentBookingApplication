package com.banyan.FullLoadRequest.Services.Booking;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.controllers.UpdateController;

@Service
public class BookingSchedulerService {

	@Autowired
	BookingRepository bookRepo;
	@Autowired
	BookingHandlerService handlerService;
	@Autowired
	UpdateController updateController;

	private Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
	int start = 0;

	@Scheduled(cron = "0 49 * * * ?")
	public void generateBookingsFromQueue() {

		// Get Last Timestamp from Booking Queue on Reboot
		if (start == 0) {
			timeStamp = bookRepo.getLastTimestamp();
			System.err.println("Get Timestamp from Booking_Queue on Reboot: " + timeStamp);
			start++;
		}

		// Populate Booking Queue with New Rate Quotes Scheduled for Shipment
		try{
			bookRepo.insertIntoBookingQueue(timeStamp);
		}
		catch(Exception e) {
			System.out.println("JDBC Exception "+e.getMessage());
			Logger.error("Insert into Booking Queue JDBC Exception "+e.getMessage()+" "+e.getStackTrace());
		}
		Logger.info("Processing a new Batch from BookingQueue...");

		// Retrieve IDs from Booking Queue and generate Bookings in system
		List<BigDecimal> rateIdList = new ArrayList<>();
		timeStamp = new Timestamp(System.currentTimeMillis());
		rateIdList = bookRepo.findAllFromQueue();
		System.out.println("Queue Size: " + rateIdList.size());
		rateIdList.forEach(a -> System.out.println(a));
		rateIdList.forEach(a -> handlerService.handleBooking(a));
	}

	@Transactional
	@Scheduled(cron = "0 33 * * * ?")
	public void updateBanyanLoads() {

		// Insert Bookings to be updated into Banyan Update Queue
		try {
			bookRepo.insertIntoUpdateQueue();
		} catch (Exception e) {
			System.out.println("InsertToUpdateQueueException " + e.getMessage());
			Logger.error("InsertToUpdateQueueException " + e.getMessage());
		}

		// Insert the updated References into Booking References
		bookRepo.insertNewBookingReferences();
		List<BigDecimal> updateLoadList = new ArrayList<>();

		// Get All Banyan Bookings to be updated from Update Queue
		updateLoadList = bookRepo.getFromUpdateQueue();
		System.out.println("Size of Update List: " + updateLoadList.size());
		List<BigDecimal> updateSuccessList = new ArrayList<>();
		updateLoadList.stream().filter(u -> updateController.updateLoad(u.intValue()).equals("200"))
				.forEach(a -> updateSuccessList.add(a));

		String successList = "(";
		if (updateSuccessList.size() == 0)
			successList = successList + ")";

		// Create a SuccessList String to use in JDBC Delete Sql Query
		for (int i = updateSuccessList.size() - 1; i >= 0; i--) {
			if (i != 0)
				successList = successList + updateSuccessList.get(i) + ",";
			else
				successList = successList + updateSuccessList.get(i) + ")";
		}
		System.out.println("Success List String: " + successList);

		// Delete successfully update Bookings from Banyan Update Queue
		try {
			bookRepo.deleteFromUpdateQueue(successList);
		} catch (Exception e) {
			System.out.println("DeleteFromUpdateQueueException " + e.getMessage());
			Logger.error("DeleteFromUpdateQueueException " + e.getMessage());
		}
	}
}
