package com.banyan.FullLoadRequest.Services.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.Shipper;
import com.banyan.FullLoadRequest.models.Pickup.XPO.Requestor;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPO_Contact;

@Service
public class RequestorBuildService {

	@Autowired
	Requestor requestor;
	@Autowired
	XPO_Contact contact;
	@Autowired
	ContactBuilderService contactService;

	public Requestor buildRequestor(Shipper shipper) {

		contact = contactService.buildContact(shipper);
		requestor.setContact(contact);
		requestor.setRoleCd("S");
		return requestor;
	}
}
