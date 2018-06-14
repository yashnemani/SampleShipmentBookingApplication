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
public class GenerateBookingsFromQueue {

	@Autowired
	BookingRepository bookRepo;
	@Autowired
	BookingHandlerService handlerService;
	@Autowired
	UpdateController updateController;

	private Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
	int start = 0;

	@Scheduled(cron = "0 0 * * * ?")
	public void getBookingsFromQueue() {

		// Get Last Timestamp from Booking Queue on Reboot
		if (start == 0) {
			timeStamp = bookRepo.getLastTimestamp();
			System.out.println("Get Timestamp from Booking_Queue on Reboot: " + timeStamp);
			start++;
		}

		// Populate Booking Queue
		bookRepo.insertIntoBookingQueue(timeStamp);
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
	@Scheduled(cron = "0 42 * * * ?")
	public void updateBanyanLoads() {

		// Insert Bookings to be updated into Banyan Update Queue
		bookRepo.insertIntoUpdateQueue();
		// Insert the updated References into Booking References
		bookRepo.insertNewBookingReferences();
		List<BigDecimal> updateLoadList = new ArrayList<>();
		// Get All Banyan Bookings to be updated from Update Queue
		updateLoadList = bookRepo.getFromUpdateQueue();
		System.out.println("Size of Update List: " + updateLoadList.size());
		updateLoadList.forEach(u -> updateController.updateLoad(u.intValue()));
		// Clear Banyan Update Queue
		bookRepo.clearUpdateQueue();
	}
}
