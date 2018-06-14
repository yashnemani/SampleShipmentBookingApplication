package com.banyan.FullLoadRequest.graphQL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Entities.BookingStatus;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookingBuilderService;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;

@Component
public class Query {

	@Autowired
	BookingRepository bookRepo;
	@Autowired
	ShipmentFilteringService filteringService;
	@Autowired
	BookingBuilderService bookService;

	public Query() {
		super();
	}

	@GraphQLQuery(name = "getOrderedStatuses")
	public List<BookingStatus> getStatuses(@GraphQLArgument(name = "bookingId") int bookingId) {
		List<BookingStatus> statuses = new ArrayList<>();
		if (bookRepo.findById(bookingId).isPresent())
			bookRepo.findById(bookingId).get().getStatuses().forEach(a -> statuses.add(a));
		Collections.sort(statuses);
		statuses.forEach(s -> s.setDate_graph());
		return statuses;
	}

	@GraphQLQuery(name = "getShipmentDetails")
	public Booking getShipmentDetails(@GraphQLArgument(name = "bookingId") int bookingId) {
		if (bookRepo.findById(bookingId).isPresent()) {
			Booking booking = new Booking();
			booking = bookRepo.findById(bookingId).get();
			booking.setGraph_FullLoad(bookService.getFullLoad(booking));
			booking.getCurrentStatus().setDate_graph();
			return booking;
		} else
			return null;
	}

	@GraphQLQuery(name = "getFilteredShipments")
	public List<Booking> getFilteredShipments(@GraphQLArgument(name = "filter") BookingFilter filter) {
		List<Booking> bookingList = new ArrayList<>();
		bookingList = filteringService.filterShipments(filter);
		return bookingList;
	}
}
