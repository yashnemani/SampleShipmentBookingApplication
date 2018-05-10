package com.banyan.FullLoadRequest.Repos;

import com.banyan.FullLoadRequest.Entities.Booking;

public interface BookingRepositoryCustom {
	
	void saveToTrackingQueue(int bookingId, int providerId);
	void refresh(Booking book);
	void deleteFromBookingQueue(int rateId);
}
