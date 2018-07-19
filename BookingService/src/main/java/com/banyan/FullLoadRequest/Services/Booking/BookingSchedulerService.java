package com.banyan.FullLoadRequest.Services.Booking;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.controllers.UpdateController;

@Service
public class BookingSchedulerService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(BookingSchedulerService.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	
	@Autowired
	BookingRepository bookRepo;
	@Autowired
	BookingHandlerService handlerService;
	@Autowired
	UpdateController updateController;

	private Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
	int start = 0;
	int type = 1;

	@Scheduled(cron = "0 7 * * * ?")
	public void generateBookingsFromQueue() {

		// Get Last Timestamp from Booking Queue on Reboot
		if (start == 0) {
			timeStamp = bookRepo.getLastTimestamp();
			log.info("Get Timestamp from Booking_Queue on Reboot: " + timeStamp);
			start++;
		}

		// Populate Booking Queue with New Rate Quotes Scheduled for Shipment
		try{
			bookRepo.insertIntoBookingQueue(timeStamp);
		}
		catch(Exception e) {
			nxtLogger.error("Insert into Booking Queue JDBC Exception "+e.getMessage()+" "+e.getStackTrace());
		}
		log.info("Processing a new Batch from BookingQueue...");

		// Retrieve IDs from Booking Queue and generate Bookings in system
		List<BigDecimal> rateIdList = new ArrayList<>();
		timeStamp = new Timestamp(System.currentTimeMillis());
		rateIdList = bookRepo.findAllFromQueue();
		log.info("Queue Size: " + rateIdList.size());
		rateIdList.forEach(a -> log.info(a.toString()));
		rateIdList.forEach(a -> handlerService.handleBooking(a));
	}

	@Transactional
	@Scheduled(cron = "0 0/30 * * * ?")
	public void updateBanyanLoads() {

		// Insert Bookings to be updated into Banyan Update Queue
		try {
			bookRepo.insertIntoUpdateQueue();
		} catch (Exception e) {
			nxtLogger.error("InsertToUpdateQueueException " + e.getMessage());
		}

		// Insert the updated References into Booking References
		bookRepo.insertNewBookingReferences();
		List<BigDecimal> updateLoadList = new ArrayList<>();

		// Get All Banyan Bookings to be updated from Update Queue
		updateLoadList = bookRepo.getFromUpdateQueue();
		log.info("Size of Update List: " + updateLoadList.size());
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
		log.info("Success List String: " + successList);

		// Delete successfully update Bookings from Banyan Update Queue
		try {
			bookRepo.deleteFromUpdateQueue(successList);
		} catch (Exception e) {
			nxtLogger.error("DeleteFromUpdateQueueException " + e.getMessage());
		}
	}
}
