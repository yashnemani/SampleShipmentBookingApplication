package com.banyan.FullLoadRequest.Services.Banyan;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Entities.BookingCurrentStatus;
import com.banyan.FullLoadRequest.Entities.BookingReferences;
import com.banyan.FullLoadRequest.Entities.BookingStatus;
import com.banyan.FullLoadRequest.Entities.NxtStatusDates;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookRefSaveService;
import com.google.gson.Gson;

@Service
public class ImportResponseHandlerService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(ImportResponseHandlerService.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	
	@Autowired
	BookingRepository bookRepo;
	@Autowired
	Booking book;
	@Autowired
	BookRefSaveService refSaveService;

	public void handleResponseObject(Object obj, int id, Timestamp pkupDt) {

		Gson gson = new Gson();
		String json = gson.toJson(obj);

		JSONObject jobj;
		String EstimatedDeliveryDate = null;
		String pickupNum = null;
		String LoadID = null;
		String QuoteID = null;
		try {
			jobj = new JSONObject(json);
			LoadID = jobj.get("LoadID").toString();
			JSONObject json1 = jobj.getJSONObject("QuoteInformation");
			QuoteID = json1.get("QuoteID").toString();
			JSONObject json2 = jobj.getJSONObject("BookResponse").getJSONObject("DispatchResponse");

			if (json2.getBoolean("Success") == true) {
				try {
					EstimatedDeliveryDate = json2.get("EstimatedDeliveryDate").toString();
					log.info("EST Delivery: " + EstimatedDeliveryDate);
					pickupNum = json2.getString("PickupNumber");
				} catch (JSONException e) {
					log.error("JSON Exception " + e.getMessage());
				}
			}
		} catch (JSONException e) {
			log.error("JSON Exception " + e.getMessage());
		}

		if (!bookRepo.existsById(id)) {
			log.info("Unable to find Booking in DB with ID: " + id);
			return;
		}
		Set<BookingReferences> bookRefs = new HashSet<>();
		Optional<Booking> book1 = bookRepo.findById(id);
		book = book1.get();
		book.setUpdate(true);

		if (LoadID != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, LoadID, 3);
			bookRefs.add(bookRef1);
		}

		if (QuoteID != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, QuoteID, 12);
			bookRefs.add(bookRef1);
		}

		if (pickupNum != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, pickupNum, 11);
			bookRefs.add(bookRef1);
		}

		bookRefs.forEach(a -> log.info(" Import References: " + a.getRef_type() + ","));
		book.setReferences(bookRefs);

		// Status Dates - Booking Date with Banyan
		NxtStatusDates statusDates = new NxtStatusDates();
		if (book.getStatusDates() != null)
			statusDates = book.getStatusDates();
		Timestamp dtBooked = new Timestamp(System.currentTimeMillis());
		if (statusDates.getBooking() == null) {
			statusDates.setBooking(book);
		}
		statusDates.setDt_entered(dtBooked);
		book.setStatusDates(statusDates);

		if (LoadID != null) {
			// Set Booking Status and Current Status
			Set<BookingStatus> statuses = new HashSet<>();
			BookingCurrentStatus currentStatus = new BookingCurrentStatus();
			if (book.getCurrentStatus() != null) {
				currentStatus = book.getCurrentStatus();
			}
			BookingStatus bookingStatus = new BookingStatus();
			bookingStatus.setStatus("XB");
			bookingStatus.setLocation(null);
			bookingStatus.setMessage("Shipment Booked in Banyan");
			bookingStatus.setDate(new Timestamp(System.currentTimeMillis()));
			bookingStatus.setBooking(book);
			statuses.add(bookingStatus);
			currentStatus.setBooking(book);
			currentStatus.setLocation(bookingStatus.getLocation());
			currentStatus.setMessage(bookingStatus.getMessage());
			currentStatus.setStatus(bookingStatus);
			currentStatus.setShipStatus(bookingStatus.getStatus());
			currentStatus.setShipState("AP");
			currentStatus.setDate(bookingStatus.getDate());
			currentStatus.setLastUpdatedDt();
			if (pickupNum != null) {
				// Set Booking Status and Current Status
				BookingStatus bookingStatus1 = new BookingStatus();
				bookingStatus1.setStatus("AA");
				bookingStatus1.setLocation(null);
				bookingStatus1.setMessage("Pickup Appointment Arranged ");
				bookingStatus1.setDate(new Timestamp(System.currentTimeMillis()));
				bookingStatus1.setBooking(book);
				statuses.add(bookingStatus1);

				currentStatus.setBooking(book);
				currentStatus.setLocation(bookingStatus1.getLocation());
				currentStatus.setMessage(bookingStatus1.getMessage());
				currentStatus.setStatus(bookingStatus1);
				currentStatus.setShipStatus(bookingStatus1.getStatus());
				currentStatus.setShipState("AP");
				currentStatus.setDate(bookingStatus1.getDate());
				currentStatus.setLastUpdatedDt();
				currentStatus.setEstPickupDt(pkupDt);
			}
			book.setStatuses(statuses);
			book.setCurrentStatus(currentStatus);
		}
		try {
			bookRepo.save(book);
		} catch (RuntimeException ex) {
			nxtLogger.error("RunTime Exception " + ex.getMessage());
		}
	}
}
