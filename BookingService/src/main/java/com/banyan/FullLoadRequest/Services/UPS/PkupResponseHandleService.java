package com.banyan.FullLoadRequest.Services.UPS;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;
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
public class PkupResponseHandleService {

	@Autowired
	BookingRepository bookRepo;
	@Autowired
	Booking book;
	@Autowired
	BookRefSaveService refSaveService;

	public void handlePkupResponse(Object obj, int id) {

		Gson gson = new Gson();
		String json = gson.toJson(obj);
		String pkupCnfmNmbr = null;
		JSONObject jobj;

		try {
			jobj = new JSONObject(json);

			if (jobj.has("Fault")) {
				System.out.println("Fault");
				return;
			}
			pkupCnfmNmbr = jobj.getJSONObject("FreightPickupResponse").get("PickupRequestConfirmationNumber")
					.toString();
		} catch (JSONException e) {
			System.out.println(e.getCause() + " " + e.getMessage());
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

		bookRefs.forEach(a -> System.out.println(" Import References: " + a.getRef_type() + ","));
		
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

		book.setStatuses(statuses);
		book.setCurrentStatus(currentStatus);
		book.setReferences(bookRefs);
		book.setUpdate(true);
		try {
			bookRepo.save(book);
		} catch (RuntimeException ex) {
			System.err.println(ex.getCause().getMessage());
		}
	}
}
