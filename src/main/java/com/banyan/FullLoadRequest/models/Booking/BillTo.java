package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class BillTo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Name;
	private String Note;
	private Integer ShipType;
	private Integer PayType;
	private boolean UseDefaultBillTo;
	private Address AddressInfo;
	private Contact ContactInfo;
	@JsonProperty("Name")
	public String getName() {
		return Name;
	}
	@JsonProperty("Note")
	public String getNote() {
		return Note;
	}
	@JsonProperty("ShipType")
	public Integer getShipType() {
		return ShipType;
	}
	@JsonProperty("PayType")
	public Integer getPayType() {
		return PayType;
	}
	@JsonProperty("UseDefaultBillTo")
	public boolean isUseDefaultBillTo() {
		return UseDefaultBillTo;
	}
	@JsonProperty("AddressInfo")
	public Address getAddressInfo() {
		return AddressInfo;
	}
	@JsonProperty("ContactInfo")
	public Contact getContactInfo() {
		return ContactInfo;
	}

	private BillTo() {
	}

	private BillTo(Builder build) {
		this.Name = build.Name;
		this.Note = build.Note;
		this.ShipType = build.ShipType;
		this.PayType = build.PayType;
		this.UseDefaultBillTo = build.UseDefaultBillTo;
		this.AddressInfo = build.AddressInfo;
		this.ContactInfo = build.ContactInfo;
	}

	public static class Builder {
		private String Name;
		private String Note;
		private Integer ShipType;
		private Integer PayType;
		private boolean UseDefaultBillTo;
		private Address AddressInfo;
		private Contact ContactInfo;

		public Builder setName(String name) {
			Name = name;
			return this;
		}

		public Builder setNote(String note) {
			Note = note;
			return this;
		}

		public Builder setShipType(Integer shipType) {
			ShipType = shipType;
			return this;
		}

		public Builder setPayType(Integer payType) {
			PayType = payType;
			return this;
		}

		public Builder setUseDefaultBillTo(boolean useDefaultBillTo) {
			UseDefaultBillTo = useDefaultBillTo;
			return this;
		}

		public Builder setAddressInfo(Address addressInfo) {
			AddressInfo = addressInfo;
			return this;
		}

		public Builder setContactInfo(Contact contactInfo) {
			ContactInfo = contactInfo;
			return this;
		}

		public BillTo build() {
			return new BillTo(this);
		}
	}
}
