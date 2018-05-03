package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class ParcelOptions implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer DeliveryConfirmation;
	
	private Integer COD;
	private BigDecimal CODAmount;
	//Required??
	private BigDecimal InsuranceAmount;
	private BigDecimal DeclaredValue;
	private boolean AdditionalHandling;
	private boolean LargePackage;

	private ParcelOptions() {

	}

	private ParcelOptions(Builder build) {
		this.DeliveryConfirmation = build.DeliveryConfirmation;
		this.COD = build.COD;
		this.CODAmount = build.CODAmount;
		this.InsuranceAmount = build.InsuranceAmount;
		this.DeclaredValue = build.DeclaredValue;
		this.AdditionalHandling = build.AdditionalHandling;
		this.LargePackage = build.LargePackage;
	}

	public static class Builder {
		
		private Integer DeliveryConfirmation;
		
		private Integer COD;
		private BigDecimal CODAmount;
		private BigDecimal InsuranceAmount;
		private BigDecimal DeclaredValue;
		private boolean AdditionalHandling;
		private boolean LargePackage;

		public Builder setDeliveryConfirmation(Integer deliveryConfirmation) {
			DeliveryConfirmation = deliveryConfirmation;
			return this;
		}

		public Builder setCOD(Integer cOD) {
			COD = cOD;
			return this;
		}

		public Builder setCODAmount(BigDecimal cODAmount) {
			CODAmount = cODAmount;
			return this;
		}

		public Builder setInsuranceAmount(BigDecimal insuranceAmount) {
			InsuranceAmount = insuranceAmount;
			return this;
		}

		public Builder setDeclaredValue(BigDecimal declaredValue) {
			DeclaredValue = declaredValue;
			return this;
		}

		public Builder setAdditionalHandling(boolean additionalHandling) {
			AdditionalHandling = additionalHandling;
			return this;
		}

		public Builder setLargePackage(boolean largePackage) {
			LargePackage = largePackage;
			return this;
		}

		public ParcelOptions build() {
			return new ParcelOptions(this);
		}
	}
	@JsonProperty("DeliveryConfirmation")
	public Integer getDeliveryConfirmation() {
		return DeliveryConfirmation;
	}
	@JsonProperty("COD")
	public Integer getCOD() {
		return COD;
	}
	@JsonProperty("CODAmount")
	public BigDecimal getCODAmount() {
		return CODAmount;
	}
/*	@JsonProperty("DeclaredValue")
	public BigDecimal getDeclaredValue() {
		return DeclaredValue;
	}
	@JsonProperty("AdditionalHandling")
	public boolean isAdditionalHandling() {
		return AdditionalHandling;
	}
	@JsonProperty("LargePackage")
	public boolean isLargePackage() {
		return LargePackage;
	}*/
	@JsonProperty("InsuranceAmount")
	public BigDecimal getInsuranceAmount() {
		return InsuranceAmount;
	}
}
