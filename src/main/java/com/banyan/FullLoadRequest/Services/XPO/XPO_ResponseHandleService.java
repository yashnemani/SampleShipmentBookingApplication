package com.banyan.FullLoadRequest.Services.XPO;

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
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookRefSaveService;
import com.google.gson.Gson;

@Service
public class XPO_ResponseHandleService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(XPO_ResponseHandleService.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	
	@Autowired
	BookingRepository bookRepo;
	@Autowired
	Booking book;
	@Autowired
	BookRefSaveService refSaveService;

	public void handlePkupResponse(Object obj, int id, Timestamp pkupDt) {

		Gson gson = new Gson();
		String json = gson.toJson(obj);
		String pkupCnfmNmbr = null;
		JSONObject jobj;

		try {
			jobj = new JSONObject(json);
			pkupCnfmNmbr = jobj.getJSONObject("data").get("confirmationNbr").toString();
		} catch (JSONException e) {
			log.error("JSON Exception " + e.getMessage());
			return;
		}

		Set<BookingReferences> bookRefs = new HashSet<>();
		Optional<Booking> book1 = bookRepo.findById(id);
		book = book1.get();

		if (pkupCnfmNmbr != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, pkupCnfmNmbr, 11);
			bookRefs.add(bookRef1);
		}

		// Set Booking Status and Current Status
		Set<BookingStatus> statuses = new HashSet<>();
		BookingStatus bookingStatus = new BookingStatus();
		bookingStatus.setStatus("AA");
		bookingStatus.setLocation(null);
		bookingStatus.setMessage("Pickup Appointment Arranged ");
		bookingStatus.setDate(new Timestamp(System.currentTimeMillis()));
		bookingStatus.setBooking(book);
		statuses.add(bookingStatus);

		BookingCurrentStatus currentStatus = new BookingCurrentStatus();
		if (book.getCurrentStatus() != null) {
			currentStatus = book.getCurrentStatus();
		}
		currentStatus.setBooking(book);
		currentStatus.setLocation(bookingStatus.getLocation());
		currentStatus.setMessage(bookingStatus.getMessage());
		currentStatus.setStatus(bookingStatus);
		currentStatus.setShipStatus(bookingStatus.getStatus());
		currentStatus.setShipState("AP");
		currentStatus.setDate(bookingStatus.getDate());
		currentStatus.setLastUpdatedDt();
		currentStatus.setEstPickupDt(pkupDt);

		book.setStatuses(statuses);
		book.setCurrentStatus(currentStatus);
		book.setReferences(bookRefs);
		book.setUpdate(true);
		try {
			bookRepo.save(book);
		} catch (RuntimeException ex) {
			nxtLogger.error("RunTime Exception " + ex.getMessage());
		}
	}
}
