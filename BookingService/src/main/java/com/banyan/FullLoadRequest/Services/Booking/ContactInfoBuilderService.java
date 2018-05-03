package com.banyan.FullLoadRequest.Services.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.models.Booking.Contact;

@Service
public class ContactInfoBuilderService {
	@Autowired
	Contact con;

	public Contact buildContact(RateQtAddress rtQtAddress, int i) {

		if (i == 1) {
			 con = new Contact.Builder().firstNameIs(null).lastNameIs(null)
					.contactNameIs(rtQtAddress.getDestContactName()).phoneIs(rtQtAddress.getDestContactPhone())
					.phoneExtIs(rtQtAddress.getDestContactExt()).faxIs(null).emailIs(rtQtAddress.getDestContactEmail())
					.build();
		} else if (i == 0) {
			con = new Contact.Builder().firstNameIs(null).lastNameIs(null)
					.contactNameIs(rtQtAddress.getOriginContactName()).phoneIs(rtQtAddress.getOriginContactPhone())
					.phoneExtIs(rtQtAddress.getOriginContactExt()).faxIs(null)
					.emailIs(rtQtAddress.getOriginContactEmail()).build();
		}

		return con;
	}
}
