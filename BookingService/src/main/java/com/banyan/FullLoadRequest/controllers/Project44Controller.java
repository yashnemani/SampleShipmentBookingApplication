package com.banyan.FullLoadRequest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.banyan.FullLoadRequest.Services.Project44.ShipIdentifiersAndAttributesService;
import com.banyan.FullLoadRequest.Services.Project44.ShipmentStopService;
import com.banyan.FullLoadRequest.Services.Project44.TruckloadInitializingService;

@RestController
public class Project44Controller {
	// Project 44 Testing
	@Autowired
	ShipmentStopService stopService;
	@Autowired
	ShipIdentifiersAndAttributesService shipIdService;
	@Autowired
	TruckloadInitializingService truckLoadService;

	@GetMapping("/getShipmentStops/{id}")
	public Object getShipStops(@PathVariable Integer id) {
		return stopService.buildShipmentStops(id);
	}

	@GetMapping("/getShipmentIdentifiers/{id}")
	public Object getShipmentIdentifiers(@PathVariable Integer id) {
		return shipIdService.buildShipmentIdentifiers(id);
	}
	
	@GetMapping("/getTruckLoadInit/{id}")
	public Object getTruckLoadInit(@PathVariable Integer id) {
		return truckLoadService.buildTruckLoad(id);
	}
}
