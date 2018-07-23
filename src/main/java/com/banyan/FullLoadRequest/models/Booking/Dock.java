package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Dock implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String note;
	private java.sql.Timestamp openTime;
	private java.sql.Timestamp shipmentDateTime;
	private java.sql.Timestamp closeTime;
	private String confirmationNumber;
	//String or String ??
	private String fcfs;

	public Dock() {
	}

	public Dock(Builder build) {
		this.name = build.name;
		this.note = build.note;
		this.openTime = build.openTime;
		this.shipmentDateTime = build.shipmentDateTime;
		this.closeTime = build.closeTime;
		this.confirmationNumber = build.confirmationNumber;
		this.fcfs = build.fcfs;
	}

	public static class Builder {
		private String name;
		private String note;
		private java.sql.Timestamp openTime;
		private java.sql.Timestamp shipmentDateTime;
		private java.sql.Timestamp closeTime;
		private String confirmationNumber;
		private String fcfs;

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setNote(String note) {
			this.note = note;
			return this;
		}

		public Builder setOpenTime(java.sql.Timestamp openTime) {
			this.openTime = openTime;
			return this;
		}

		public Builder setShipmentDateTime(java.sql.Timestamp shipmentDateTime) {
			this.shipmentDateTime = shipmentDateTime;
			return this;
		}

		public Builder setCloseTime(java.sql.Timestamp closeTime) {
			this.closeTime = closeTime;
			return this;
		}

		public Builder setConfirmationNumber(String confirmationNumber) {
			this.confirmationNumber = confirmationNumber;
			return this;
		}

		public Builder setFcfs(String fcfs) {
			this.fcfs = fcfs;
			return this;
		}

		public Dock build() {
			return new Dock(this);
		}
	}
	@JsonProperty("Name")
	public String getName() {
		return name;
	}
	@JsonProperty("Note")
	public String getNote() {
		return note;
	}
	@JsonProperty("OpenTime")
	public java.sql.Timestamp isOpenTime() {
		return openTime;
	}
	@JsonProperty("ShipmentDateTime")
	public java.sql.Timestamp isShipmentDateTime() {
		return shipmentDateTime;
	}
	@JsonProperty("CloseTime")
	public java.sql.Timestamp isCloseTime() {
		return closeTime;
	}
	@JsonProperty("ConfirmationNumber")
	public String getConfirmationNumber() {
		return confirmationNumber;
	}
	@JsonProperty("FCFS")
	public String isFcfs() {
		return fcfs;
	}
}
