package com.banyan.FullLoadRequest.Services.Booking;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Entities.BookingReferences;

@Service
public class BookRefSaveService {

	// Generate Booking_Reference to be saved or updated for given Booking and
	// Reference details
	public BookingReferences saveOrUpdateReferences(Booking booking, String ref, int refType) {

		Stream<BookingReferences> bookRef = null;
		BookingReferences bookRef1 = new BookingReferences();

		if (booking.getReferences() != null) {
			if (booking.getReferences().stream().anyMatch(a -> a.getRef_type() == refType)) {
				System.out.println("RefType " + refType + " " + ref + " exists!");
				bookRef = booking.getReferences().stream().filter(a -> a.getRef_type() == refType).limit(1);
				Optional<BookingReferences> bookRef2 = bookRef.findFirst();
				bookRef1 = bookRef2.get();
			} else {
				bookRef1.setBooking(booking);
				bookRef1.setRef_type(refType);
			}
		} else {
			bookRef1.setBooking(booking);
			bookRef1.setRef_type(refType);
		}
		bookRef1.setReference(ref);

		return bookRef1;
	}
}
