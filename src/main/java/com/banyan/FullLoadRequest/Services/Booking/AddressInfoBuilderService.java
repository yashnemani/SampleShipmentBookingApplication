package com.banyan.FullLoadRequest.Services.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.models.Booking.Address;

@Service
public class AddressInfoBuilderService {

	@Autowired
	Address add;
	public Address buildAddress(RateQtAddress rtQtAddress, int i) {
if(i==0) {
		 add = new Address.Builder().setAddress1(rtQtAddress.getOriginAddress()).setAddress2(null)
				.setCity(rtQtAddress.getOriginCity()).setCountryCode(rtQtAddress.getOriginCountryCode())
				.setCountryName(null).setLocationName(rtQtAddress.getOriginName())
				.setZipcode(rtQtAddress.getOriginZipCode()).setState(rtQtAddress.getOriginStateCode()).build();
}
else if (i==1) {
	 add = new Address.Builder().setAddress1(rtQtAddress.getDestAddress()).setAddress2(null)
			.setCity(rtQtAddress.getDestCity()).setCountryCode(rtQtAddress.getDestCountryCode())
			.setCountryName(null).setLocationName(rtQtAddress.getDestName())
			.setZipcode(rtQtAddress.getDestZipCode()).setState(rtQtAddress.getDestStateCode()).build();
}

		return add;
	}
}