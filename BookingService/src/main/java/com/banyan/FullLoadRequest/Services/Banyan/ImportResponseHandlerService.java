package com.banyan.FullLoadRequest.Services.Banyan;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
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

	@Autowired
	BookingRepository bookRepo;
	@Autowired
	Booking book;
	@Autowired
	BookRefSaveService refSaveService;

	public void handleResponseObject(Object obj, int id) {

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
					pickupNum = json2.getString("PickupNumber");
				} catch (JSONException e) {
					System.err.println(e);
				}
			}
		} catch (JSONException e) {
			System.err.println(e);
		}

		if (!bookRepo.existsById(id)) {
			System.out.println("Unable to find Booking in DB with ID: " + id);
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

		bookRefs.forEach(a -> System.out.println(" Import References: " + a.getRef_type() + ","));
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

		if (pickupNum != null) {
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
				System.out.println("Update Current Status");
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

			book.setStatuses(statuses);
			book.setCurrentStatus(currentStatus);
		}

		try {
			bookRepo.save(book);
		} catch (RuntimeException ex) {
			System.err.println(ex.getCause().getMessage());
		}
	}
}
