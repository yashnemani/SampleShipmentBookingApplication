package com.banyan.FullLoadRequest.models.enums;

public enum DocumentTypes {

	Banyan_BOL(0), Shippng_Label(1), Carrier_BOL(2), Carrier_POD(3), Carrier_Label(4), AP(5), AR(6);

	private final int value;

	private DocumentTypes(int docCode) {
		this.value = docCode;
	}
	
	public int getValue() {
		return this.value;
	}
}
