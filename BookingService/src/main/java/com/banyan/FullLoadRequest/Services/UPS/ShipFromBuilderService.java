package com.banyan.FullLoadRequest.Services.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.Shipper;
import com.banyan.FullLoadRequest.models.Pickup.UPS.Phone;
import com.banyan.FullLoadRequest.models.Pickup.UPS.ShipFrom;
import com.banyan.FullLoadRequest.models.Pickup.UPS.Ups_Address;

@Service
public class ShipFromBuilderService {

	@Autowired
	ShipFrom shipFrom;
	@Autowired
	Ups_Address ups_Address;
	@Autowired
	Phone phone;

	@Autowired
	Ups_AddressBuilderService addressBuildService;

	public ShipFrom buildShipFrom(Shipper shipper) {

		phone = new Phone(shipper.getContactInfo().getPhone());
		phone.setExt(shipper.getContactInfo().getPhoneExt());

		String companyName = shipper.getCompanyName();
		/*companyName = "AMERICAN LUBE WEST";*/
		ups_Address = addressBuildService.buildUpsAddress(shipper.getAddressInfo());

		shipFrom = new ShipFrom.Builder().setAttnName(shipper.getContactInfo().getContactName()).setName(companyName)
				.setEmail(shipper.getContactInfo().getEmail()).setPhone(phone).setAddress(ups_Address).build();

		return shipFrom;
	}
}
