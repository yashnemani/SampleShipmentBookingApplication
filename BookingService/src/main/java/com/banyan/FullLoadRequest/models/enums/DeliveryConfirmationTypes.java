package com.banyan.FullLoadRequest.models.enums;

public enum DeliveryConfirmationTypes {
	None(0), CertifiedMail(1), AdultSignatureRequired(2), DirectSignatureRequired(3), IndirectSignatureRequired(4);

	private final int value;

	private DeliveryConfirmationTypes(int deliveryCode) {
		this.value = deliveryCode;
	}
	
	public int getValue() {
		return this.value;
	}
}
