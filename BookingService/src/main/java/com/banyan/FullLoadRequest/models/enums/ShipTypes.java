package com.banyan.FullLoadRequest.models.enums;

public enum ShipTypes {
	Shipper(0), Consignee(1), ThirdParty(2);

	private final int value;

	ShipTypes(int shipCode) {
		this.value = shipCode;
	}
	
	public int getValue() {
		return this.value;
	}
}
