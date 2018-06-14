package com.banyan.FullLoadRequest.graphQL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingRepository;

@Service
public class ShipmentFilteringService {

	@Autowired
	BookingRepository bookRepo;

	public List<Booking> filterShipments(BookingFilter filter) {
		List<Booking> bookingList = new ArrayList<>();
		List<BigDecimal> idList = new ArrayList<>();

		if (filter != null) {

			if (filter.getId() != null) {
				if (bookRepo.existsById(filter.getId())) {
					Booking booking = new Booking();
					booking = bookRepo.findById(filter.getId()).get();
					bookingList.add(booking);
				}
			}
			if (filter.getProvider() != null && filter.getStatus() != null) {
				idList = bookRepo.findBookingByStatusAndProvider(filter.getStatus(), filter.getProvider());
				for (int i = 0; i < idList.size(); i++) {
					int bId = idList.get(i).intValue();
					if (bookRepo.existsById(bId)) {
						Booking booking = new Booking();
						booking = bookRepo.findById(bId).get();
						bookingList.add(booking);
					}
				}
			} else if (filter.getProvider() != null && filter.getState() != null) {

				idList = bookRepo.findBookingByStateAndProvider(filter.getState(), filter.getProvider());
				for (int i = 0; i < idList.size(); i++) {
					int bId = idList.get(i).intValue();
					if (bookRepo.existsById(bId)) {
						Booking booking = new Booking();
						booking = bookRepo.findById(bId).get();
						bookingList.add(booking);
					}
				}
			} else if (filter.getProvider() != null) {

				idList = bookRepo.findBookingByProvider(filter.getProvider());
				for (int i = 0; i < idList.size(); i++) {
					int bId = idList.get(i).intValue();
					if (bookRepo.existsById(bId)) {
						Booking booking = new Booking();
						booking = bookRepo.findById(bId).get();
						bookingList.add(booking);
					}
				}
			} else if (filter.getStatus() != null) {
				idList = bookRepo.findBookingByStatus(filter.getStatus());
				for (int i = 0; i < idList.size(); i++) {
					int bId = idList.get(i).intValue();
					if (bookRepo.existsById(bId)) {
						Booking booking = new Booking();
						booking = bookRepo.findById(bId).get();
						bookingList.add(booking);
					}
				}
			} else if (filter.getState() != null) {
				idList = bookRepo.findBookingByState(filter.getState());
				for (int i = 0; i < idList.size(); i++) {
					int bId = idList.get(i).intValue();
					if (bookRepo.existsById(bId)) {
						Booking booking = new Booking();
						booking = bookRepo.findById(bId).get();
						bookingList.add(booking);
					}
				}
			}
			return bookingList;
		} else
			return bookingList;
	}
}
