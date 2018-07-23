package com.banyan.FullLoadRequest.Services.Project44;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.models.Project44.CarrierIdentifier;

@Service
public class CarrierIdentifierService {

	@Autowired
	BookingRepository bookRepo;

	public CarrierIdentifier buildCarrierId(Integer id) {
		if (!bookRepo.findById(id).isPresent())
			return null;
		Booking book = new Booking();
		book = bookRepo.findById(id).get();
		CarrierIdentifier carrierId = new CarrierIdentifier();

		String dot_Number = bookRepo.getUsDotNum(book.getCARRIER_CODE());
		if (dot_Number != null) {
			carrierId.setType("DOT_NUMBER");
			carrierId.setValue(dot_Number);
			return carrierId;
		}

		carrierId.setType("SCAC");
		carrierId.setValue(book.getCARRIER_CODE());
		return carrierId;
	}
}
