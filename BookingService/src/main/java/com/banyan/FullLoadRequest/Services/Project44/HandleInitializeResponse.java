package com.banyan.FullLoadRequest.Services.Project44;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Entities.BookingReferences;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookRefSaveService;
import com.banyan.FullLoadRequest.models.Project44.InitializeTruckLoadResponse;

@Service
public class HandleInitializeResponse {

	@Autowired
	BookingRepository bookRepo;
	@Autowired
	BookRefSaveService refSaveService;
	
	public void handleResponse( int bookingId, InitializeTruckLoadResponse response) {
		String refId = response.getShipment().getId().toString();
		Booking book = new Booking();
		book = bookRepo.findById(bookingId).get();
		
		Set<BookingReferences> bookRefs = new HashSet<>();
		BookingReferences bookRef1 = new BookingReferences();
		bookRef1 = refSaveService.saveOrUpdateReferences(book, refId, 3);
		bookRefs.add(bookRef1);
		book.setReferences(bookRefs);
		
		bookRepo.save(book);
	}
}
