package com.banyan.FullLoadRequest.Services.Project44;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.Booking;
import com.banyan.FullLoadRequest.Repos.BookingRepository;
import com.banyan.FullLoadRequest.models.Project44.Attribute;
import com.banyan.FullLoadRequest.models.Project44.ShipmentIdentifier;

@Service
public class ShipIdentifiersAndAttributesService {
	@Autowired
	BookingRepository bookRepo;

	List<Attribute> attributes = new ArrayList<>();

	// Builds both Shipment Identifiers and Attributes
	// Method needs to be called before getAttributes
	public List<ShipmentIdentifier> buildShipmentIdentifiers(Integer id) {

		if (!bookRepo.findById(id).isPresent())
			return null;
		Booking book = new Booking();
		book = bookRepo.findById(id).get();
		if (book.getReferences() == null)
			return null;

		// Attributes
		if (book.getReferences().stream().filter(a -> a.getRef_type() == 0).findFirst().isPresent()) {
			Attribute at = new Attribute();
			at.setname("PRO");
			at.setValue(
					book.getReferences().stream().filter(a -> a.getRef_type() == 0).findFirst().get().getReference());
			attributes.add(at);
		}

/*		if (book.getReferences().stream().filter(a -> a.getRef_type() == 2).findFirst().isPresent()) {
			Attribute at = new Attribute();
			at.setname("PURCHASE_ORDER");
			at.setValue(
					book.getReferences().stream().filter(a -> a.getRef_type() == 2).findFirst().get().getReference());
			attributes.add(at);
		}*/

		if (book.getReferences().stream().filter(a -> a.getRef_type() == 11).findFirst().isPresent()) {
			Attribute at = new Attribute();
			at.setname("PICKUP");
			at.setValue(
					book.getReferences().stream().filter(a -> a.getRef_type() == 11).findFirst().get().getReference());
			attributes.add(at);
		}

		if (book.getReferences().stream().filter(a -> a.getRef_type() == 3).findFirst().isPresent()) {
			Attribute at = new Attribute();
			at.setname("SYSTEM_GENERATED");
			at.setValue(
					book.getReferences().stream().filter(a -> a.getRef_type() == 3).findFirst().get().getReference());
			attributes.add(at);
		}

/*		Attribute at = new Attribute();
		at.setname("CUSTOMER_REFERENCE");
		at.setValue(id.toString());
		attributes.add(at);*/

		// Shipment Identifiers
		List<ShipmentIdentifier> shipmentIdentifiers = new ArrayList<>();
		if (book.getReferences().stream().filter(a -> a.getRef_type() == 1).findFirst().isPresent()) {
			ShipmentIdentifier identifier = new ShipmentIdentifier();
			identifier.setType("BILL_OF_LADING");
			identifier.setValue(
					book.getReferences().stream().filter(a -> a.getRef_type() == 1).findFirst().get().getReference());
			shipmentIdentifiers.add(identifier);
		}
		return shipmentIdentifiers;
	}
	
	// Get List of Attributes
	public List<Attribute> getAttributes(){
		return attributes;
	}
}
