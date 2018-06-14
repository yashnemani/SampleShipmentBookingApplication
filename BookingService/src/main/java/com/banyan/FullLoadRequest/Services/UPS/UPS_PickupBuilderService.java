package com.banyan.FullLoadRequest.Services.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Pickup.UPS.FreightPickupRequest;
import com.banyan.FullLoadRequest.models.Pickup.UPS.UPS_PickupRequest;
import com.banyan.FullLoadRequest.models.Pickup.UPS.UPS_Security;

@Service
public class UPS_PickupBuilderService {

	@Autowired
	FullLoad_Request fullLoad;
	@Autowired
	UPS_PickupRequest upsPickup;
	@Autowired
	FreightPickupRequest freightPickup;
	@Autowired
	private UPS_Security security;

	@Autowired
	FreightPickup_ReqBuilderService freightPickupService;

	public UPS_PickupRequest buildFreightPickup(int bookingId) {

		freightPickup = freightPickupService.buildfreightPickup(bookingId);

		if (freightPickup == null)
			return null;
		upsPickup = new UPS_PickupRequest.Builder().setSecurity(security).setPickupRequest(freightPickup).build();
		return upsPickup;
	}
}
