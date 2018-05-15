package com.banyan.FullLoadRequest.Services.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.Shipper;
import com.banyan.FullLoadRequest.models.Pickup.XPO.Email;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPO_Contact;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPO_Phone;

@Service
public class ContactBuilderService {

	@Autowired
	XPO_Contact contact;
	@Autowired
	Email email;
	@Autowired
	XPO_Phone phone;
	@Autowired
	EmailBuildService emailService;
	@Autowired
	PhoneBuildService phoneService;

	public XPO_Contact buildContact(Shipper shipper) {

		email = emailService.buildEmail(shipper.getContactInfo().getEmail());
		phone = phoneService.buildPhone(shipper.getContactInfo());
		String companyName = shipper.getCompanyName();
		if (companyName.length() > 30)
			companyName = companyName.substring(0, 30);
		contact = new XPO_Contact.Builder().setCompanyName(companyName)
				.setFullName(shipper.getContactInfo().getContactName()).setEmail(email).setPhone(phone).build();

		return contact;
	}
}
