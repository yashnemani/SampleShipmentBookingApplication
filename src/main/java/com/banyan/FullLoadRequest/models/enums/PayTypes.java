package com.banyan.FullLoadRequest.models.enums;

public enum PayTypes {

	Collect(0), Prepaid(1);

	private final int value;

	private PayTypes(int payCode) {
		this.value = payCode;
	}
	
	public int getValue() {
		return this.value;
	}
	
}
