package com.banyan.FullLoadRequest.Services.Booking;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.controllers.BookingController;
import com.banyan.FullLoadRequest.controllers.PickupControlller;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;

@Service
public class BookingHandlerService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(BookingHandlerService.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	
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

	/* @Async("BookingExecutor") */
	public void handleBooking(BigDecimal rateID) {

		int rateId = rateID.intValue();
		boolean dispatch = true;

		// If Booking with ID already exists in DB, skip and delete from queue
		if (bookRepo.existsById(rateId)) {
			dispatch = false;
		}
			

		// Get FullLoad Object
		fullLoad = fullLoadService.buildFullLoad(rateId);
		if (fullLoad == null) {
			nxtLogger.error("FullLoad Object could not be created using the given ID " + rateId);
			return;
		}

		// Create Booking in DB
		Booking book = new Booking();
		book = bookingService.buildBooking(fullLoad, rateId);
		if (book==null||!bookRepo.existsById(rateId)) {
			log.warn("Booking could not be created for the given ID " + rateId);
			return;
		}

		// Delete Booking from booking Queue
		bookRepo.deleteFromBookingQueue(rateId);

		if(book.getCARRIER_CODE().equals("RISF"))
			dispatch = true;
				
		// Add to Banyan Update Queue if Booking already exists
		if (!dispatch) {
			if (book.getPROVIDER_ID() == 0) {
				log.info("Pro Number: "+fullLoad.getLoadinfo().getManifestID());
				try {
					bookRepo.addToUpdateQueue(rateId, fullLoad.getLoadinfo().getManifestID());}
				catch(Exception e) {
					nxtLogger.error("Update Queue Exception "+e.getMessage());
				}
			}
		}

		else {
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
}
