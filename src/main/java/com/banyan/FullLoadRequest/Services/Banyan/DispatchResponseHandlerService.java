package com.banyan.FullLoadRequest.Services.Banyan;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Entities.BookingReferences;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookRefSaveService;
import com.google.gson.Gson;

@Service
public class DispatchResponseHandlerService {

	@Autowired
	BookingRepository bookRepo;
	@Autowired
	Booking book;
	@Autowired
	BookRefSaveService refSaveService;

	public void handleDispatchResponse(Object obj, int id) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		JSONObject json2;
		String EstimatedDeliveryDate = null;
		String pickupNum = null;
		try {
			json2 = new JSONObject(json);
			if (json2.getBoolean("Success") == true) {
				EstimatedDeliveryDate = json2.get("EstimatedDeliveryDate").toString();
				pickupNum = json2.getString("PickupNumber");
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

		if (pickupNum != null) {
			BookingReferences bookRef1 = new BookingReferences();
			bookRef1 = refSaveService.saveOrUpdateReferences(book, pickupNum, 11);
			bookRefs.add(bookRef1);
		}
		book.setReferences(bookRefs);
		try {
			bookRepo.save(book);
		} catch (RuntimeException ex) {
			System.err.println(ex.getCause().getMessage());
		}
	}
}
