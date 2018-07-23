package com.banyan.FullLoadRequest.Services.Project44;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.Services.Booking.BookingBuilderService;
import com.banyan.FullLoadRequest.Services.Booking.FullLoadBuildService;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Project44.HazmatDetails;

@Service
public class HazmatService {

	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	BookingRepository bookRepo;
	@Autowired
	FullLoadBuildService fullLoadService;
	@Autowired
	BookingBuilderService bookingService;

	public HazmatDetails buildHazmat(Integer id) {

		if (!bookRepo.findById(id).isPresent())
			return null;
		Booking book = new Booking();
		book = bookRepo.findById(id).get();

		fullLoad = bookingService.getFullLoad(book);
		if (fullLoad == null)
			return null;
		if (fullLoad.getProducts() == null)
			return null;

		List<String> hazardClasses = new ArrayList<>();
		for (int i = 0; i < fullLoad.getProducts().size(); i++) {
			if (fullLoad.getProducts().get(i).isIsHazmat())
				hazardClasses.add(fullLoad.getProducts().get(i).getDescription());
		}

		HazmatDetails hazmatDetails = new HazmatDetails(hazardClasses);
		return hazmatDetails;
	}
}
