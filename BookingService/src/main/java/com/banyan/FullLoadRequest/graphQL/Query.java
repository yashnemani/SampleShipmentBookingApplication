package com.banyan.FullLoadRequest.graphQL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Entities.BookingCurrentStatus;
import com.banyan.FullLoadRequest.Entities.BookingStatus;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.models.Booking.AuthenticationData;

import io.leangen.graphql.annotations.GraphQLQuery;

@Component
public class Query {

	@Autowired
	BookingRepository bookRepo;

	public Query() {
		super();
	}

	@GraphQLQuery(name = "getAuthData")
	public AuthenticationData getAuthData() {
		AuthenticationData authData = new AuthenticationData();
		return authData;
	}

	@GraphQLQuery(name = "getCurrentStatus")
	public BookingCurrentStatus getCurrentStatus(int bookingId) {
		if (bookRepo.findById(bookingId).isPresent())
			return bookRepo.findById(bookingId).get().getCurrentStatus();
		else
			return null;
	}

	@GraphQLQuery(name = "getOrderedStatuses")
	public List<BookingStatus> getStatuses(int bookingId) {
		List<BookingStatus> statuses = new ArrayList<>();
		if (bookRepo.findById(bookingId).isPresent())
			bookRepo.findById(bookingId).get().getStatuses().forEach(a -> statuses.add(a));
		Collections.sort(statuses);
		return statuses;
	}

	@GraphQLQuery(name = "getShipmentDetails")
	public Booking getShipmentDetails(int bookingId) {
		if (bookRepo.findById(bookingId).isPresent())
			return bookRepo.findById(bookingId).get();
		else
			return null;
	}
}
