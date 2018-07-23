package com.banyan.FullLoadRequest.models.enums;

public enum SizeUnitsofMeasure {
	IN(0), FT(1), CM(2);

	private final int value;

	private SizeUnitsofMeasure(int sizeCode) {
		this.value = sizeCode;
	}
	
	public int getValue() {
		return this.value;
	}
}
