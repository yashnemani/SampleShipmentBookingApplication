package com.banyan.FullLoadRequest.Services.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.Consignee;
import com.banyan.FullLoadRequest.models.Pickup.UPS.Phone;
import com.banyan.FullLoadRequest.models.Pickup.UPS.ShipTo;
import com.banyan.FullLoadRequest.models.Pickup.UPS.Ups_Address;

@Service
public class ShipToBuilderService {

	@Autowired
	ShipTo shipTo;
	@Autowired
	Ups_Address ups_Address;
	@Autowired
	Phone phone;

	@Autowired
	Ups_AddressBuilderService addressBuildService;
	
	public ShipTo buildShipTo(Consignee consignee) {
		
		phone = new Phone(consignee.getContactInfo().getPhone());
		phone.setExt(consignee.getContactInfo().getPhoneExt());

		ups_Address = addressBuildService.buildUpsAddress(consignee.getAddressInfo());

		shipTo = new ShipTo.Builder().setAttnName(consignee.getContactInfo().getContactName())
				.setName(consignee.getCompanyName()).setEmail(consignee.getContactInfo().getEmail()).setPhone(phone)
				.setAddress(ups_Address).build();

		return shipTo;
	}
}
