package com.banyan.FullLoadRequest.controllers;

import java.util.Arrays;

import org.pmw.tinylog.Logger;
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

	@Autowired
	UpdateLoadService updateLoadService;

	// POST Updated Booking to Banyan UpdateLoad API
	@PostMapping("/updateLoadBanyan/{id}")
	public Object updateLoad(@PathVariable Integer id) {

		System.out.println("Booking to be updated " + id);
		FullLoad_Request futureFullLoad = new FullLoad_Request();
		futureFullLoad = getUpdateLoad(id);
		if (futureFullLoad == null)
			return "Booking with ID " + id + " failed to generate updateLoad Request Object";
		final String uri = "https://ws.beta.banyantechnology.com/services/api/rest/UpdateLoad ";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<FullLoad_Request> entity = new HttpEntity<>(futureFullLoad, headers);
		ResponseEntity<Object> result = null;
		try {
			result = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
		} catch (HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			Logger.error("Banyan Update Load Failed for ID " + id + " Error: " + e.getMessage());
			return e.getResponseBodyAsString();
		}
		System.out.println(result.getStatusCode() + " " + result.getStatusCodeValue());
		return result.getBody();
	}

	@GetMapping("/getUpdateLoad/{id}")
	public FullLoad_Request getUpdateLoad(@PathVariable Integer id) {
		return updateLoadService.updateLoad(id);
	}
}
