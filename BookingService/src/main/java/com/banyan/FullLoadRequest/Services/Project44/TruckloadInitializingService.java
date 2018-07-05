package com.banyan.FullLoadRequest.Services.Project44;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Project44.ApiConfiguration;
import com.banyan.FullLoadRequest.models.Project44.InitializeTruckLoad;
import com.banyan.FullLoadRequest.models.Project44.ShippingDetails;

@Service
public class TruckloadInitializingService {

	@Autowired
	ShipmentStopService stopService;
	@Autowired
	ShipIdentifiersAndAttributesService shipIdService;
	@Autowired
	CarrierIdentifierService carrierIdService;
	@Autowired
	EquipmentIdentifierService equipmentService;
	@Autowired
	HazmatService hazmatService;
	@Autowired
	ShippingDetails shipDetails;
	@Autowired
	InitializeTruckLoad truckLoad;
	@Autowired
	ApiConfiguration apiConfiguration;

	public InitializeTruckLoad buildTruckLoad(Integer id) {

		shipDetails.setHazmatDetails(hazmatService.buildHazmat(id));
		
		truckLoad = new InitializeTruckLoad.Builder().setCarrierIdentifier(carrierIdService.buildCarrierId(id))
				.setEquipmentIdentifiers(equipmentService.buildEquipmentId(id))
				.setShipmentIdentifiers(shipIdService.buildShipmentIdentifiers(id))
				.setAttributes(shipIdService.getAttributes()).setShipmentStops(stopService.buildShipmentStops(id))
				.setApiConfiguration(apiConfiguration)
				.setShippingDetails(shipDetails).build();

		return truckLoad;
	}
}
