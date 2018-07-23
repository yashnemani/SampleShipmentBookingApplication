package com.banyan.FullLoadRequest.Services.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.Repos.RateQtAddressRepository;
import com.banyan.FullLoadRequest.Repos.RateQtRepository;
import com.banyan.FullLoadRequest.models.Booking.Address;
import com.banyan.FullLoadRequest.models.Booking.BillTo;
import com.banyan.FullLoadRequest.models.Booking.Contact;
import com.banyan.FullLoadRequest.models.enums.PayTypes;
import com.banyan.FullLoadRequest.models.enums.ShipTypes;

@Service
public class BillToBuildService {

	@Autowired
	RateQtRepository qtRep;
	@Autowired
	RateQtAddressRepository addressRep;
	@Autowired
	ContactInfoBuilderService contactService;
	@Autowired
	AddressInfoBuilderService addressService;
	@Autowired
	Address address;
	@Autowired
	Contact contact;
	@Autowired
	BillTo billto;

	public BillTo buildBillTo(RateQtAddress rtQtAddress, Integer siteLocNumber) {

		String freightDir = rtQtAddress.getFreightDirection();
		String prepaid = rtQtAddress.getPrepaid();

		ShipTypes shipType;
		PayTypes payType;
		if (freightDir.equals("OB"))
			shipType = ShipTypes.Shipper;
		else if (freightDir.equals("IB"))
			shipType = ShipTypes.Consignee;
		else if (freightDir.equals("DS"))
			shipType = ShipTypes.ThirdParty;
		else
			shipType = ShipTypes.Shipper;

		if (prepaid.equals("Y"))
			payType = PayTypes.Prepaid;
		else if (prepaid.equals("N"))
			payType = PayTypes.Collect;
		else
			payType = PayTypes.Prepaid;

		Integer clientCode = rtQtAddress.getSiteClientCode();
		String showClientBOL = qtRep.findClientBOL(clientCode, siteLocNumber, rtQtAddress.getCarrierCode());
		if (showClientBOL.equals("C")) {
			address = addressRep.findSiteAddress(siteLocNumber, clientCode);
			contact = addressRep.findSiteContact(clientCode, siteLocNumber);
			String name = addressRep.findClientName(clientCode);

			BillTo bill = new BillTo.Builder().setName(name).setNote(null).setShipType(shipType.getValue())
					.setPayType(payType.getValue()).setAddressInfo(address).setContactInfo(contact)
					.setUseDefaultBillTo(false).build();

			return bill;
		} else if (showClientBOL.equals("Y") || showClientBOL.equals("N")) {
			// ReConsider
			String name = "Nexterus";
			Address add = new Address.Builder().setAddress1("802 Far Hills Dr").setAddress2(null).setCity("New Freedom")
					.setState("PA").setCountryCode("US").setCountryName("USA").setLocationName("New Freedom")
					.setZipcode("17349").build();
			Contact con = new Contact.Builder().firstNameIs("Yash").lastNameIs("Nemani").contactNameIs("Yash Nemani")
					.emailIs("ynemani@nexterus.com").phoneIs("5134108284").phoneExtIs("110").faxIs(null).build();

			BillTo bill = new BillTo.Builder().setName(name).setNote(null).setShipType(shipType.getValue())
					.setPayType(payType.getValue()).setAddressInfo(add).setContactInfo(con).setUseDefaultBillTo(false)
					.build();
			return bill;
		} else
			return null;
	}
}
