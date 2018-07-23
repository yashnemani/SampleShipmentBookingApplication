package com.banyan.FullLoadRequest.Services.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.Address;
import com.banyan.FullLoadRequest.models.Pickup.UPS.Ups_Address;

@Service
public class Ups_AddressBuilderService {

	@Autowired
	Ups_Address ups_Address;

	public Ups_Address buildUpsAddress(Address address) {

		ups_Address = new Ups_Address.Builder().setAddressLine(address.getAddress1()).setCity(address.getCity())
				.setCountryCode(address.getCountryCode()).setPostalCode(address.getZipcode())
				.setStateCode(address.getState()).build();

		return ups_Address;
	}
}
