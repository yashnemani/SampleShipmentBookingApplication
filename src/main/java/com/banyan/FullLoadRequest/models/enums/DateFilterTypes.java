package com.banyan.FullLoadRequest.models.enums;

public enum DateFilterTypes {
	Created(0), Pickup(1), Delivery(2), Billing(3);

	private final int value;

	private DateFilterTypes(int dateCode) {
		this.value = dateCode;
	}
	
	public int getValue() {
		return this.value;
	}
}
