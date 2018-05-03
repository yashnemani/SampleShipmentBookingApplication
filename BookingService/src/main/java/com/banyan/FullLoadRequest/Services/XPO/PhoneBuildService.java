package com.banyan.FullLoadRequest.Services.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.Contact;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPO_Phone;

@Service
public class PhoneBuildService {

	@Autowired
	XPO_Phone phone;
	@Autowired
	Contact contact;

	public XPO_Phone buildPhone(Contact con) {

		if (con.getPhone() != null) {
			String ph1 = con.getPhone().substring(0, 4);
			String ph2 = con.getPhone().substring(4, 12);
			ph2 = ph2.replaceAll("-", "");
			String phNo = ph1 + ph2;
			phone.setPhoneNum(phNo);
		}
		phone.setExt(con.getPhoneExt());
		return phone;
	}
}
