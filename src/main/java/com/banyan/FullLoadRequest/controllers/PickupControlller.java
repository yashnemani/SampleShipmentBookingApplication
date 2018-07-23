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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.banyan.FullLoadRequest.Services.Banyan.DispatchLoadBuilderService;
import com.banyan.FullLoadRequest.Services.Banyan.DispatchResponseHandlerService;
import com.banyan.FullLoadRequest.Services.UPS.FreightPickup_ReqBuilderService;
import com.banyan.FullLoadRequest.Services.UPS.PkupResponseHandleService;
import com.banyan.FullLoadRequest.Services.UPS.UPS_PickupBuilderService;
import com.banyan.FullLoadRequest.Services.XPO.PkupRequestBuildService;
import com.banyan.FullLoadRequest.Services.XPO.XPO_PickupBuildService;
import com.banyan.FullLoadRequest.Services.XPO.XPO_ResponseHandleService;
import com.banyan.FullLoadRequest.models.Pickup.Banyan.DispatchLoad_Request;
import com.banyan.FullLoadRequest.models.Pickup.UPS.UPS_PickupRequest;
import com.banyan.FullLoadRequest.models.Pickup.XPO.OAuth2Token;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPOAccess;
import com.banyan.FullLoadRequest.models.Pickup.XPO.XPO_PkupRequest;

@RestController
public class PickupControlller {
	
	final org.slf4j.Logger logger = LoggerFactory.getLogger(PickupControlller.class);
	Logger nxtLogger = LoggerFactory.getLogger("com.nexterus");
	
	// Models
	@Autowired
	DispatchLoad_Request dispatchLoad;
	@Autowired
	UPS_PickupRequest upsPickup;
	@Autowired
	OAuth2Token authToken;
	@Autowired
	XPO_PkupRequest xpoPickup;

	// Services
	@Autowired
	DispatchResponseHandlerService dispatchResponseService;
	@Autowired
	DispatchLoadBuilderService dispatchBuildService;
	@Autowired
	UPS_PickupBuilderService upsPickupService;
	@Autowired
	PkupResponseHandleService pkupResponseService;
	@Autowired
	XPO_PickupBuildService xpopickupService;
	@Autowired
	XPO_ResponseHandleService xpoResponseService;
	@Autowired
	PkupRequestBuildService xpoPkupReqService;
	@Autowired
	FreightPickup_ReqBuilderService upsPickupReqService;

	// Call Banyan DispatchLoad to place Pickup Request
	@GetMapping("/Banyan/DispatchLoad/{bookingId}")
	public Object dispatchLoad(@PathVariable Integer bookingId) {
		
		getDispatchLoad(bookingId);
		if (dispatchLoad == null)
			return "booking with ID " + bookingId + " does not exist in the DB! Cannot place DispatchLoad Request.";
		final String uri = "http://ws.beta.banyantechnology.com/services/api/rest/DispatchLoad";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<DispatchLoad_Request> entity = new HttpEntity<>(dispatchLoad, headers);
		ResponseEntity<Object> result = null;
		try {
			result = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
			dispatchResponseService.handleDispatchResponse(result.getBody(), bookingId);
		} catch (HttpClientErrorException e) {
			logger.info(e.getStatusCode().toString());
			logger.info(e.getResponseBodyAsString());
			nxtLogger.error("Banyan DispatchLoad request Failed for ID " + bookingId + " Error: " + e.getMessage());
			return e.getResponseBodyAsString();
		}
		return result.getBody();
	}

	// Get the Banyan DispatchLoad_Request Object from given BookingID
	@GetMapping("/Banyan/getDispatchLoad/{bookingId}")
	public DispatchLoad_Request getDispatchLoad(@PathVariable Integer bookingId) {
		dispatchLoad = dispatchBuildService.buildDispatchLoad(bookingId);
		if (dispatchLoad == null)
			return null;
		return dispatchLoad;
	}

	// Call UPS Freight Pickup to place Pickup Request
	@PostMapping("/UPS/UPS_PickupReq/{id}")
	public Object postUPSPickup(@PathVariable int id) {

		upsPickup = getUpsPickup(id);
		if (upsPickup == null)
			return null;
		final String uri = "https://wwwcie.ups.com/rest/FreightPickup";
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<UPS_PickupRequest> entity = new HttpEntity<>(upsPickup, headers);

		try {
			ResponseEntity<Object> result = restTemplate.exchange(uri, HttpMethod.POST, entity, Object.class);
			pkupResponseService.handlePkupResponse(result.getBody(), id, upsPickupReqService.getPkupDate());
			logger.info(result.getStatusCode().toString());
			return result.getBody();
		} catch (HttpClientErrorException e) {
			logger.info(e.getStatusCode().toString());
			logger.info(e.getResponseBodyAsString());
			nxtLogger.error("UPS Pickup request Failed for ID " + id + " Error: " + e.getMessage());
			return e.getResponseBodyAsString();
		}
	}

	// Get UPS PickupRequest Object for given BookingID
	@GetMapping("/UPS/getUpsPickupReq/{id}")
	public UPS_PickupRequest getUpsPickup(@PathVariable int id) {
		upsPickup = upsPickupService.buildFreightPickup(id);
		return upsPickup;
	}

	// Get XPO Bearer Token
	@PostMapping("/XPO/getXPOBearerToken")
	public OAuth2Token getXPOBearerToken() {

		XPOAccess access = new XPOAccess();
		String tokenUrl = access.getTokenUrl();

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", access.getUserToken());

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("grant_type", access.getGrant_type());
		map.add("username", access.getUsername());
		map.add("password", access.getPassword());

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		ResponseEntity<OAuth2Token> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, entity,
				OAuth2Token.class);

		authToken = response.getBody();

		return authToken;
	}

	int xpoCount = 0;

	// Post XPO_Pickup Request to create Pickup request with XPO
	@PostMapping("/XPO/createXpoPickup/{id}/{test}")
	public Object createXpoPickup(@PathVariable int id, @PathVariable boolean test) {

		if (authToken.getAccessToken() == null) {
			authToken = getXPOBearerToken();
		}

		String accessToken = "Bearer " + authToken.getAccessToken();
		RestTemplate restTemplate = new RestTemplate();
		xpoPickup = getXpoPickupRequest(id, test);
		if (xpoPickup == null)
			return null;
		String url = "https://api.ltl.xpo.com/pickuprequest/1.0/cust-pickup-requests?testMode=Y";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", accessToken);
		/* headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); */
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<XPO_PkupRequest> entity = new HttpEntity<>(xpoPickup, headers);
		try {
			ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
			xpoResponseService.handlePkupResponse(response.getBody(), id, xpoPkupReqService.getPkupDate());
			return response.getBody();
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().value() == 401) {
				authToken = getXPOBearerToken();
				createXpoPickup(id, true);
			}
			logger.info(e.getStatusCode().toString());
			logger.info(e.getResponseBodyAsString());
			logger.info(e.getResponseHeaders().toString());
			nxtLogger.error("XPO Pickup request Failed for ID " + id + " Error: " + e.getMessage());
			return e.getResponseBodyAsString();
		} catch (HttpServerErrorException e) {
			if (xpoCount == 0) {
				logger.warn("500 Error! Retrying....");
				xpoCount++;
				createXpoPickup(id, true);
			} else
				xpoCount = 0;
			logger.info(e.getStatusCode().toString());
			logger.info(e.getResponseBodyAsString());
			logger.info(e.getResponseHeaders().toString());
			nxtLogger.error("XPO Pickup request Failed for ID " + id + " Error: " + e.getMessage());
			return e.getResponseBodyAsString();
		}
	}

	// Get XPO_Pickup Request
	@GetMapping("/XPO/getXpoPickupReq/{id}/{test}")
	public XPO_PkupRequest getXpoPickupRequest(@PathVariable int id, @PathVariable boolean test) {
		return xpopickupService.buildXpoPkupReq(id, test);
	}
}
