package com.banyan.FullLoadRequest.Services.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.models.Booking.Address;
import com.banyan.FullLoadRequest.models.Booking.Contact;
import com.banyan.FullLoadRequest.models.Booking.Dock;
import com.banyan.FullLoadRequest.models.Booking.Shipper;

@Service
public class ShipperBuildService {

	@Autowired
	Shipper shipper;
	@Autowired
	Contact contact;
	@Autowired
	Address address;
	@Autowired
	Dock dock;
	@Autowired
	ContactInfoBuilderService contactService;
	@Autowired
	AddressInfoBuilderService addressService;

	public Shipper buildShipper(RateQtAddress rtQtAddress) {

		contact = contactService.buildContact(rtQtAddress, 0);
		address = addressService.buildAddress(rtQtAddress, 0);
		dock = buildDock(rtQtAddress);

		Shipper ship = new Shipper.Builder().setContactInfo(contact).setAddressInfo(address).setDock(dock)
				.setCompanyName(rtQtAddress.getOriginName()).setCompanyId(rtQtAddress.getSiteClientCode().toString())
				.setDCRefNum(null).setLocationName(rtQtAddress.getOriginCity())
				.setNote(rtQtAddress.getSpecialRequirements()).setVendorId(null).build();

		return ship;
	}

	public Dock buildDock(RateQtAddress rtQtAddress) {

		Dock doc = new Dock.Builder().setNote(null).setName(null).setCloseTime(null)
				.setOpenTime(null).setShipmentDateTime(rtQtAddress.getShippedDt()).setConfirmationNumber(null)
				.setFcfs(null).build();

		return doc;
	}
}
