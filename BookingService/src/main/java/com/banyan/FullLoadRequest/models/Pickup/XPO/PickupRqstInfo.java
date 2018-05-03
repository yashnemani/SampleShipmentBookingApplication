package com.banyan.FullLoadRequest.models.Pickup.XPO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class PickupRqstInfo {

	private String pkupDate;
	private String readyTime;
	private String closeTime;
	private String specialEquipmentCd;
	private String driverNote;
	private boolean insidePkupInd;
	private boolean volumeInd;
	private boolean earlyCutOffTimeInd;
	@Autowired
	private XPO_Shipper xPO_Shipper;
	@Autowired
	private Requestor requestor;
	@Autowired
	private XPO_Contact xPO_Contact;
	private String remarks;
	@Autowired
	private List<PkupItem> pkupItem;

	public PickupRqstInfo() {

	}

	public PickupRqstInfo(Builder build) {
		this.pkupDate = build.pkupDate;
		this.readyTime = build.readyTime;
		this.closeTime = build.closeTime;
		this.specialEquipmentCd = build.specialEquipmentCd;
		this.insidePkupInd = build.insidePkupInd;
		this.earlyCutOffTimeInd = build.earlyCutOffTimeInd;
		this.volumeInd = build.volumeInd;
		this.driverNote = build.driverNote;
		this.xPO_Shipper = build.xPO_Shipper;
		this.requestor = build.requestor;
		this.xPO_Contact = build.xPO_Contact;
		this.pkupItem = build.pkupItem;
		this.remarks = build.remarks;
	}

	public static class Builder {

		private String pkupDate;
		private String readyTime;
		private String closeTime;
		private String specialEquipmentCd;
		private String driverNote;
		private boolean insidePkupInd;
		private boolean volumeInd;
		private boolean earlyCutOffTimeInd;
		private XPO_Shipper xPO_Shipper;
		private Requestor requestor;
		private XPO_Contact xPO_Contact;
		private String remarks;
		private List<PkupItem> pkupItem;

		public Builder setPkupDate(String pkupDate) {
			this.pkupDate = pkupDate;
			return this;
		}

		public Builder setReadyTime(String readyTime) {
			this.readyTime = readyTime;
			return this;
		}

		public Builder setCloseTime(String closeTime) {
			this.closeTime = closeTime;
			return this;
		}

		public Builder setSpecialEquipmentCd(String specialEquipmentCd) {
			this.specialEquipmentCd = specialEquipmentCd;
			return this;
		}

		public Builder setDriverNote(String driverNote) {
			this.driverNote = driverNote;
			return this;
		}

		public Builder setInsidePkupInd(boolean insidePkupInd) {
			this.insidePkupInd = insidePkupInd;
			return this;
		}

		public Builder setVolumeInd(boolean volumeInd) {
			this.volumeInd = volumeInd;
			return this;
		}

		public Builder setEarlyCutOffTimeInd(boolean earlyCutOffTimeInd) {
			this.earlyCutOffTimeInd = earlyCutOffTimeInd;
			return this;
		}

		public Builder setShipper(XPO_Shipper xPO_Shipper) {
			this.xPO_Shipper = xPO_Shipper;
			return this;
		}

		public Builder setRequestor(Requestor requestor) {
			this.requestor = requestor;
			return this;
		}

		public Builder setContact(XPO_Contact xPO_Contact) {
			this.xPO_Contact = xPO_Contact;
			return this;
		}

		public Builder setRemarks(String remarks) {
			this.remarks = remarks;
			return this;
		}

		public Builder setPkupItem(List<PkupItem> pkupItem) {
			this.pkupItem = pkupItem;
			return this;
		}

		public PickupRqstInfo build() {
			return new PickupRqstInfo(this);
		}
	}

	@JsonProperty("pkupDate")
	public String getPkupDate() {
		return pkupDate;
	}

	@JsonProperty("readyTime")
	public String getReadyTime() {
		return readyTime;
	}

	@JsonProperty("closeTime")
	public String getCloseTime() {
		return closeTime;
	}

	@JsonProperty("specialEquipmentCd")
	public String getSpecialEquipmentCd() {
		return specialEquipmentCd;
	}

	@JsonProperty("driverNote")
	public String getDriverNote() {
		return driverNote;
	}

	@JsonProperty("insidePkupInd")
	public boolean isInsidePkupInd() {
		return insidePkupInd;
	}

	@JsonProperty("volumeInd")
	public boolean isVolumeInd() {
		return volumeInd;
	}

/*	@JsonProperty("earlyCutOffTimeInd")
	public boolean isEarlyCutOffTimeInd() {
		return earlyCutOffTimeInd;
	}*/

	@JsonProperty("shipper")
	public XPO_Shipper getShipper() {
		return xPO_Shipper;
	}

	@JsonProperty("requestor")
	public Requestor getRequestor() {
		return requestor;
	}

	@JsonProperty("contact")
	public XPO_Contact getContact() {
		return xPO_Contact;
	}

	@JsonProperty("remarks")
	public String getRemarks() {
		return remarks;
	}

	@JsonProperty("pkupItem")
	public List<PkupItem> getPkupItem() {
		return pkupItem;
	}

}
