package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class InsuranceInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String vendorCode;
	private String commodityTypeCode;
	private String commodityConditionCode;
	private BigDecimal insuredValue;
	private boolean insuranceWanted;
	
	public InsuranceInfo() {
	
	}
	
	public InsuranceInfo(Builder build) {
		this.vendorCode = build.vendorCode;
		this.commodityConditionCode = build.commodityConditionCode;
		this.commodityTypeCode = build.commodityTypeCode;
		this.insuranceWanted = build.insuranceWanted;
		this.insuredValue = build.insuredValue;
	}
	
	private static class Builder{
		
		private String vendorCode;
		private String commodityTypeCode;
		private String commodityConditionCode;
		private BigDecimal insuredValue;
		private boolean insuranceWanted;
		
		@SuppressWarnings("unused")
		public Builder setVendorCode(String vendorCode) {
			this.vendorCode = vendorCode;
			return this;
		}
		@SuppressWarnings("unused")
		public Builder setCommodityTypeCode(String commodityTypeCode) {
			this.commodityTypeCode = commodityTypeCode;
			return this;
		}
		@SuppressWarnings("unused")
		public Builder setCommodityConditionCode(String commodityConditionCode) {
			this.commodityConditionCode = commodityConditionCode;
			return this;
		}
		@SuppressWarnings("unused")
		public Builder setInsuredValue(BigDecimal insuredValue) {
			this.insuredValue = insuredValue;
			return this;
		}
		@SuppressWarnings("unused")
		public Builder setInsuranceWanted(boolean insuranceWanted) {
			this.insuranceWanted = insuranceWanted;
			return this;
		}
		
		@SuppressWarnings("unused")
		public InsuranceInfo build() {
			return new InsuranceInfo(this);
		}
		
		
	}

	@JsonProperty("VendorCode")
	public String getVendorCode() {
		return vendorCode;
	}
	@JsonProperty("CommodityTypeCode")
	public String getCommodityTypeCode() {
		return commodityTypeCode;
	}
	@JsonProperty("CommodityConditionCode")
	public String getCommodityConditionCode() {
		return commodityConditionCode;
	}
	@JsonProperty("InsuredValue")
	public BigDecimal getInsuredValue() {
		return insuredValue;
	}
	@JsonProperty("InsuranceWanted")
	public boolean isInsuranceWanted() {
		return insuranceWanted;
	}
	
	
	
}
