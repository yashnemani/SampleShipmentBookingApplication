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

import com.banyan.FullLoadRequest.Services.Banyan.UpdateLoadService;
import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;

@RestController
public class UpdateController {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(UpdateController.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	@Autowired
	UpdateLoadService updateLoadService;

	// POST Updated Booking to Banyan UpdateLoad API
	@PostMapping("/updateLoadBanyan/{id}")
	public Object updateLoad(@PathVariable Integer id) {
		log.info("Booking to be updated " + id);
		FullLoad_Request futureFullLoad = new FullLoad_Request();
		futureFullLoad = getUpdateLoad(id);
		if (futureFullLoad == null)
			return "Booking with ID " + id + " failed to generate updateLoad Request Object";

		String uri = "https://ws.logistics.banyantechnology.com/services/api/rest/UpdateLoad";
/*		if (type == 0)
			uri = "https://ws.beta.banyantechnology.com/services/api/rest/UpdateLoad";
		else if (type == 1)
			uri = "https://ws.logistics.banyantechnology.com/services/api/rest/UpdateLoad";*/

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<FullLoad_Request> entity = new HttpEntity<>(futureFullLoad, headers);
		ResponseEntity<Object> result = null;
		try {
			result = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		} catch (HttpClientErrorException e) {
			log.info(e.getStatusCode().toString());
			log.info(e.getResponseBodyAsString());
			nxtLogger.error("Banyan Update Load Failed for ID " + id + " Error: " + e.getMessage());
				return e.getStatusCode().toString();
		}
		return result.getStatusCode().toString();
	}

	@GetMapping("/getUpdateLoad/{id}")
	public FullLoad_Request getUpdateLoad(@PathVariable Integer id) {
		return updateLoadService.updateLoad(id);
	}
}
