package com.banyan.FullLoadRequest.Services.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.models.Booking.Address;
import com.banyan.FullLoadRequest.models.Booking.Consignee;
import com.banyan.FullLoadRequest.models.Booking.Contact;
import com.banyan.FullLoadRequest.models.Booking.Dock;

@Service
public class ConsigneeBuildService {

	@Autowired
	Consignee consignee;
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

	public Consignee buildConsignee(RateQtAddress rtQtAddress) {

		contact = contactService.buildContact(rtQtAddress, 1);
		address = addressService.buildAddress(rtQtAddress, 1);
		dock = buildDock(rtQtAddress);

		Consignee con = new Consignee.Builder().setContactInfo(contact).setAddressInfo(address).setDock(dock)
				.setCompanyName(rtQtAddress.getDestName()).setCompanyId(null)
				.setDCRefNum(null).setLocationName(rtQtAddress.getDestCity())
				.setNote(rtQtAddress.getSpecialRequirements()).setVendorId(null).build();

		return con;
	}

	public Dock buildDock(RateQtAddress rtQtAddress) {

		Dock doc = new Dock.Builder().setNote(null).setName(null).setCloseTime(null)
				.setOpenTime(null).setShipmentDateTime(rtQtAddress.getDtEstimatedDelivery()).setConfirmationNumber(null)
				.setFcfs(null).build();

		return doc;
	}
}
