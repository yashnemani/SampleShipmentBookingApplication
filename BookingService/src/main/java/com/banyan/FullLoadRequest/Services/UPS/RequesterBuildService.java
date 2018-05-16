package com.banyan.FullLoadRequest.Services.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.Consignee;
import com.banyan.FullLoadRequest.models.Pickup.UPS.Phone;
import com.banyan.FullLoadRequest.models.Pickup.UPS.Requester;

@Service
public class RequesterBuildService {

	@Autowired
	Requester requester;
	@Autowired
	Phone phone;

	public Requester buildRequester(Consignee consignee) {

		String nmbr = consignee.getContactInfo().getPhone();
		if (nmbr == null)
			nmbr = "717-227-5000";
		phone = new Phone(nmbr);
		phone.setExt(consignee.getContactInfo().getPhoneExt());
		String email = consignee.getContactInfo().getEmail();
		if (email == null)
			email = "ynemani@nexterus.com";
		String name = consignee.getContactInfo().getContactName();
		if (name == null)
			name = "Yash Nemani";
		requester = new Requester.Builder().setAttnName(name).setName(consignee.getCompanyName()).setEmail(email)
				.setPhone(phone).build();
		return requester;
	}
}
