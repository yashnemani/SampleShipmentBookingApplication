package com.banyan.FullLoadRequest.models.Pickup.Banyan;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class LoadData {

	private String proNumber;
	private String bolNumber;
	private String actualCarrierName;
	private String quoteID;

	public LoadData() {

	}

	public LoadData(Builder build) {

		this.proNumber = build.proNumber;
		this.bolNumber = build.bolNumber;
		this.actualCarrierName = build.actualCarrierName;
		this.quoteID = build.quoteID;
	}

	public static class Builder {

		private String proNumber;
		private String bolNumber;
		private String actualCarrierName;
		private String quoteID;

		public Builder setProNumber(String proNumber) {
			this.proNumber = proNumber;
			return this;
		}

		public Builder setBolNumber(String bolNumber) {
			this.bolNumber = bolNumber;
			return this;
		}

		public Builder setActualCarrierName(String actualCarrierName) {
			this.actualCarrierName = actualCarrierName;
			return this;
		}

		public Builder setQuoteID(String quoteID) {
			this.quoteID = quoteID;
			return this;
		}

		public LoadData build() {
			return new LoadData(this);
		}
	}

	@JsonProperty("ProNumber")
	public String getProNumber() {
		return proNumber;
	}

	@JsonProperty("BOLNumber")
	public String getBolNumber() {
		return bolNumber;
	}

	@JsonProperty("ActualCarrierName")
	public String getActualCarrierName() {
		return actualCarrierName;
	}

	@JsonProperty("QuoteID")
	public String getQuoteID() {
		return quoteID;
	}
}
