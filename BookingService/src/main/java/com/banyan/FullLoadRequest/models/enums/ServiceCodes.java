package com.banyan.FullLoadRequest.models.enums;

public enum ServiceCodes {

	Parcel(0), LTL(1), Volume(2), Air(3), Ocean(4), GFP(5), LocalCarrier(6);

	private final int value;

	private ServiceCodes(int service) {
		this.value = service;
	}
	
	public int getValue() {
		return this.value;
	}
}
