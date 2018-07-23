package com.banyan.FullLoadRequest.models.Pickup.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class UPS_PickupRequest {

	@Autowired
	private UPS_Security security;
	@Autowired
	private FreightPickupRequest pickupRequest;

	public UPS_PickupRequest() {

	}

	public UPS_PickupRequest(Builder build) {
		this.security = build.security;
		this.pickupRequest = build.pickupRequest;
	}

	@JsonProperty("Security")
	public UPS_Security getSecurity() {
		return security;
	}

	@JsonProperty("FreightPickupRequest")
	public FreightPickupRequest getPickupRequest() {
		return pickupRequest;
	}

	public static class Builder {
		@Autowired
		private UPS_Security security;
		@Autowired
		private FreightPickupRequest pickupRequest;

		public Builder setSecurity(UPS_Security security) {
			this.security = security;
			return this;
		}

		public Builder setPickupRequest(FreightPickupRequest pickupRequest) {
			this.pickupRequest = pickupRequest;
			return this;
		}

		public UPS_PickupRequest build() {
			return new UPS_PickupRequest(this);
		}

	}

}
