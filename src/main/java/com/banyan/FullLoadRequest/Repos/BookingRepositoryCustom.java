package com.banyan.FullLoadRequest.Repos;

import java.sql.Timestamp;

import com.banyan.FullLoadRequest.Entities.Booking;

public interface BookingRepositoryCustom {
	
	void saveToTrackingQueue(int bookingId, int providerId);
	void refresh(Booking book);
	void deleteFromBookingQueue(int rateId);
	void insertIntoBookingQueue(Timestamp timestamp);
	void insertIntoUpdateQueue();
	void insertNewBookingReferences();
	void deleteFromUpdateQueue(String successList);
	void addToUpdateQueue(Integer rateId, String pro);
}
