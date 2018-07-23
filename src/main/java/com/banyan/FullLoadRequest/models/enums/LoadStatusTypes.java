package com.banyan.FullLoadRequest.models.enums;

public enum LoadStatusTypes {
	Pending(0), Open(1), Quoted(2), Booked(3), Dispatched(4), InTransit(5), Delivered(6), Canceled(7);

	private final int value;

	private LoadStatusTypes(int loadCode) {
		this.value = loadCode;
	}
	public int getValue() {
		return this.value;
	}
}
