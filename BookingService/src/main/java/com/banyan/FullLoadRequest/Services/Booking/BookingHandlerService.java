package com.banyan.FullLoadRequest.Services.Booking;

import java.math.BigDecimal;

import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.controllers.BookingController;
import com.banyan.FullLoadRequest.controllers.PickupControlller;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;

@Service
public class BookingHandlerService {

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

	/*@Async("BookingExecutor")*/
	public void handleBooking(BigDecimal rateID) {

		int rateId = rateID.intValue();

		// ThreadInfo
		/*System.out.println("Execute method with configured executor - " + Thread.currentThread().getName());*/

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
			Logger.error("FullLoad Object could not be created using the given ID " + rateId);
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
			bookController.callBanyan(rateId);
		}
		// Call XPO to schedule Pickup
		else if (book.getPROVIDER_ID() == 1) {
			pickupController.createXpoPickup(rateId, true);
		}
		// Call UPS to schedule Pickup
		else if (book.getPROVIDER_ID() == 2) {
			pickupController.postUPSPickup(rateId);
		}
	}
}
