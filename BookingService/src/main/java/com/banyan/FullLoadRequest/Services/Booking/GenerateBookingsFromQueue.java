package com.banyan.FullLoadRequest.Services.Booking;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.controllers.BookingController;
import com.banyan.FullLoadRequest.controllers.PickupControlller;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;

@Service
public class GenerateBookingsFromQueue {

	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	BookingRepository bookRepo;
	@Autowired
	FullLoadBuildService fullLoadService;
	@Autowired
	BookingBuilderService bookingService;
	@Autowired
	BookingController bookController;
	@Autowired
	PickupControlller pickupController;

	private Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
	int start = 0;

	@Scheduled(cron = "0 5  * * * ?")
	public void getBookingsFromQueue() {

		// Get Last Timestamp from Booking Queue on Reboot
		if (start == 0) {
			timeStamp = bookRepo.getLastTimestamp();
			System.out.println("Get Timestamp from Booking_Queue on Reboot: " + timeStamp);
			start++;
		}

		// Populate Booking Queue
		bookRepo.insertIntoBookingQueue(timeStamp);

		// Retrieve IDs from Booking Queue and generate Bookings in system
		List<BigDecimal> rateIdList = new ArrayList<>();
		timeStamp = new Timestamp(System.currentTimeMillis());
		rateIdList = bookRepo.findAllFromQueue();
		System.out.println("Queue Size: " + rateIdList.size());
		rateIdList.forEach(a -> System.out.println(a));
		rateIdList.forEach(a -> handleBooking(a));
	}

	public void handleBooking(BigDecimal rateID) {

		int rateId = rateID.intValue();

		// If Booking with ID already exists in DB, skip and delete from queue
		if (bookRepo.existsById(rateId)) {
			System.out.println("Booking with ID " + rateId + " already exists!");
			bookRepo.deleteFromBookingQueue(rateId);
			return;
		}

		// Get FullLoad Object
		fullLoad = fullLoadService.buildFullLoad(rateId);
		if (fullLoad == null) {
			System.out.println("FullLoad Object could not be created using the given ID " + rateId);
			return;
		}

		// Create Booking in DB
		Booking book = new Booking();
		book = bookingService.buildBooking(fullLoad, rateId);
		if (!bookRepo.existsById(rateId)) {
			System.out.println("Booking could not be created for the given ID " + rateId);
			return;
		}

		// Delete Booking from booking Queue
		bookRepo.deleteFromBookingQueue(rateId);

		// Call Banyan to Book Shipment
		if (book.getPROVIDER_ID() == 0) {
			try {
				bookController.callBanyan(rateId);
			} catch (JSONException e) {
				System.err.println(e.getCause().getMessage());
			}
		}
		// Call XPO to schedule Pickup
		else if (book.getPROVIDER_ID() == 1) {
			pickupController.createXpoPickup(rateId);
		}
		// Call UPS to schedule Pickup
		else if (book.getPROVIDER_ID() == 2) {
			pickupController.postUPSPickup(rateId);
		}
	}
}
