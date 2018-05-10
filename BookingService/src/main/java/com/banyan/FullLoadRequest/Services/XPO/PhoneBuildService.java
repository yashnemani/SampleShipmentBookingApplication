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
			String phNo = con.getPhone().replaceAll("-", "");
			String ph1 = phNo.substring(0, 3);
			String ph2 = phNo.substring(3, 10);
			ph1 = ph1 + "-";

			phNo = ph1 + ph2;
			phone.setPhoneNum(phNo);
		}
		phone.setExt(con.getPhoneExt());
		return phone;
	}
}
