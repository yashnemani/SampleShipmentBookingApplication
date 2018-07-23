package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.banyan.FullLoadRequest.models.enums.IncoTermTypes;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Loadinfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer LoadID;
	private String ManifestID;
	private String BOLNumber;
	private String CustomerPO;
	private String InvoiceID;
	private String BillingID;
	private IncoTermTypes IncoTermID;

	public Loadinfo() {
	}

	public Loadinfo(Builder build) {
		this.LoadID = build.LoadID;
		this.ManifestID = build.ManifestID;
		this.BOLNumber = build.BOLNumber;
		this.CustomerPO = build.CustomerPO;
		this.InvoiceID = build.InvoiceID;
		this.BillingID = build.BillingID;
		this.IncoTermID = build.IncoTermID;
	}

	public static class Builder {

		private Integer LoadID;
		private String ManifestID;
		private String BOLNumber;
		private String CustomerPO;
		private String InvoiceID;
		private String BillingID;
		private IncoTermTypes IncoTermID;

		public Builder setLoadID(Integer loadID) {
			this.LoadID = loadID;
			return this;
		}

		public Builder setManifestID(String manifestID) {
			this.ManifestID = manifestID;
			return this;
		}

		public Builder setBOLNumber(String bOLNumber) {
			this.BOLNumber = bOLNumber;
			return this;
		}

		public Builder setCustomerPO(String customerPO) {
			this.CustomerPO = customerPO;
			return this;
		}

		public Builder setInvoiceID(String invoiceID) {
			this.InvoiceID = invoiceID;
			return this;
		}

		public Builder setBillingID(String billingID) {
			this.BillingID = billingID;
			return this;
		}

		public Builder setIncoTermID(IncoTermTypes incoTermID) {
			this.IncoTermID = incoTermID;
			return this;
		}

		public Loadinfo build() {
			return new Loadinfo(this);
		}
	}
	@JsonProperty("LoadID")
	public Integer getLoadID() {
		return LoadID;
	}
	@JsonProperty("ManifestID")
	public String getManifestID() {
		return ManifestID;
	}
	@JsonProperty("BOLNumber")
	public String getBOLNumber() {
		return BOLNumber;
	}
	@JsonProperty("CustomerPO")
	public String getCustomerPO() {
		return CustomerPO;
	}
	@JsonProperty("InvoiceID")
	public String getInvoiceID() {
		return InvoiceID;
	}
	@JsonProperty("BillingID")
	public String getBillingID() {
		return BillingID;
	}
	@JsonProperty("IncoTermID")
	public IncoTermTypes getIncoTermID() {
		return IncoTermID;
	}

}
