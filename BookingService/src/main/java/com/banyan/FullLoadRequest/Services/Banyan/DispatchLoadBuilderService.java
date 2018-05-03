package com.banyan.FullLoadRequest.Services.Banyan;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingReferencesRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookingBuilderService;
import com.banyan.FullLoadRequest.models.Booking.AuthenticationData;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Pickup.Banyan.DispatchLoad_Request;
import com.banyan.FullLoadRequest.models.Pickup.Banyan.LoadData;

@Service
public class DispatchLoadBuilderService {

	@Autowired
	BookingReferencesRepository bookingRefRepo;
	@Autowired
	DispatchLoad_Request dispatchRequest;
	@Autowired
	Booking book;
	@Autowired
	BookingBuilderService bookService;
	@Autowired
	FullLoad_Request fullLoad;

	public DispatchLoad_Request buildDispatchLoad(int bookingId) {

		book = bookService.getBooking(bookingId);
		if (book == null) {
			System.out.println("booking with ID " + bookingId + " does not exist in the DB! Try a valid ID");
			return null;
		}
		fullLoad = bookService.getFullLoad(book);
		if (fullLoad == null) {
			System.out.println("Error generating FullLoad from given Booking!");
			return null;
		}

		AuthenticationData authData = fullLoad.getAuthenticationData();
		Map<String, String> refMap = new HashMap<String, String>();
		refMap = bookingRefRepo.getReferencesByID(bookingId);

		if (refMap.get("3") == null || refMap.get("12") == null || refMap.get("1") == null) {
			System.out.println("The Booking does not have one or more required reference numbers to place DispatchLoad Request!");
			return null;
		}
			

		String loadId = refMap.get("3");
		String quoteId = refMap.get("12");
		String bolNumber = refMap.get("1");
		String proNumber = null;
		if (refMap.get("0") != null)
			proNumber = refMap.get("0");
		String carrier = null;
		if(book.getCARRIER_CODE()!=null)
		 carrier = bookingRefRepo.findCarrierNameByCode(book.getCARRIER_CODE());
		else
			return null;

		LoadData loadData = new LoadData.Builder().setProNumber(proNumber).setBolNumber(bolNumber).setQuoteID(quoteId)
				.setActualCarrierName(carrier).build();
		dispatchRequest = new DispatchLoad_Request.Builder().setAuthData(authData).setLoadID(loadId)
				.setLoadData(loadData)
				// How to set these flags?
				.setSubmitPickup(true).setDispatchOverride(true).build();
		return dispatchRequest;
	}
}
