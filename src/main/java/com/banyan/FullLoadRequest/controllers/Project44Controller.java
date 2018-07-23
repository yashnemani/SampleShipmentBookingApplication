package com.banyan.FullLoadRequest.controllers;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.banyan.FullLoadRequest.Services.Project44.HandleInitializeResponse;
import com.banyan.FullLoadRequest.Services.Project44.ShipIdentifiersAndAttributesService;
import com.banyan.FullLoadRequest.Services.Project44.ShipmentStopService;
import com.banyan.FullLoadRequest.Services.Project44.TruckloadInitializingService;
import com.banyan.FullLoadRequest.models.Project44.Auth44;
import com.banyan.FullLoadRequest.models.Project44.InitializeTruckLoad;
import com.banyan.FullLoadRequest.models.Project44.InitializeTruckLoadResponse;

@RestController
public class Project44Controller {
	
	final org.slf4j.Logger logger = LoggerFactory.getLogger(Project44Controller.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	// Project 44 Testing
	@Autowired
	ShipmentStopService stopService;
	@Autowired
	ShipIdentifiersAndAttributesService shipIdService;
	@Autowired
	TruckloadInitializingService truckLoadService;
	@Autowired
	InitializeTruckLoad initTruckLoad;
	@Autowired
	HandleInitializeResponse truckLoadResponseHandler;

	int temp = 0;

	@GetMapping("/getTruckLoadInit/{id}")
	public Object getTruckLoadInit(@PathVariable Integer id) {
		return truckLoadService.buildTruckLoad(id);
	}

	@PostMapping("/postTruckLoadInit/{id}")
	public Object postTruckLoadInit(@PathVariable Integer id) {

		initTruckLoad = truckLoadService.buildTruckLoad(id);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		Auth44 auth = new Auth44();
		headers.add("Authorization", auth.getBasic());

		// TO Generate Unique Shipment Identifier for Testing
		initTruckLoad.getShipmentIdentifiers().get(0)
				.setValue(initTruckLoad.getShipmentIdentifiers().get(0).getValue() + temp + temp);
		temp++;

		HttpEntity<InitializeTruckLoad> entity = new HttpEntity<InitializeTruckLoad>(initTruckLoad, headers);
		String url = "https://test.p-44.com/api/v3/tl/shipments";
		try {
			ResponseEntity<InitializeTruckLoadResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity,
					InitializeTruckLoadResponse.class);
			truckLoadResponseHandler.handleResponse(id, response.getBody());
			return response.getBody();
		} catch (HttpClientErrorException e) {
			logger.info(e.getStatusCode().toString());
			logger.info(e.getResponseBodyAsString());
			nxtLogger.error("Project44 Initialize TruckLoad Failed! " + id + " Error: " + e.getMessage());
			return e.getResponseBodyAsString();
		}
	}
}
