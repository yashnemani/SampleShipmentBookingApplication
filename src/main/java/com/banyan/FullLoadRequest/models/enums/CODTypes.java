package com.banyan.FullLoadRequest.models.enums;

public enum CODTypes {
	None(0), StandardCOD(1), SecuredFunds(2);

	private final int value;

	private CODTypes(int code) {
		this.value = code;
	}
	
	public int getValue() {
		return this.value;
	}
}
