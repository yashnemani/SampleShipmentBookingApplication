package com.banyan.FullLoadRequest.models.Pickup.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class XPO_PkupRequest {

	@Autowired
	private PickupRqstInfo pickupRqstInfo;

	public XPO_PkupRequest() {

	}

	public XPO_PkupRequest(PickupRqstInfo pickupRqstInfo) {
		super();
		this.pickupRqstInfo = pickupRqstInfo;
	}

	@JsonProperty("pickupRqstInfo")
	public PickupRqstInfo getPickupRqstInfo() {
		return pickupRqstInfo;
	}

}
