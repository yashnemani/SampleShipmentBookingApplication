package com.banyan.FullLoadRequest.models.enums;

public enum CurrencyTypes {
	US_Dollar(0), Canadian_Dollar(1), Mexican_Peso(2);

	private final int value;

	private CurrencyTypes(int currencyCode) {
		this.value = currencyCode;
	}
	
	public int getValue() {
		return this.value;
	}
}
