package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.banyan.FullLoadRequest.models.enums.ServiceCodes;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class RateServices implements Serializable{

	private static final long serialVersionUID = 1L;
	private ServiceCodes ServiceCode;
	private Integer ShippingQty;
	private Integer PackageType;
	private Integer EquipmentType;
	private BigDecimal AdditionalWeight;
	private String SpecialInstructions;
	private BigDecimal Length;
	private BigDecimal Width;
	private BigDecimal Height;
	private Integer SizeUom;
	private Integer WeightUom;

	private RateServices() {
	}

	private RateServices(Builder build) {
		this.ServiceCode = build.ServiceCode;
		this.ShippingQty = build.ShippingQty;
		this.PackageType = build.PackageType;
		this.EquipmentType = build.EquipmentType;
		this.AdditionalWeight = build.AdditionalWeight;
		this.SpecialInstructions = build.SpecialInstructions;
		this.Length = build.Length;
		this.WeightUom = build.WeightUom;
		this.Width = build.Width;
		this.Height = build.Height;
		this.SizeUom = build.SizeUom;
	}

	public static class Builder {

		private ServiceCodes ServiceCode;
		private Integer ShippingQty;
		private Integer PackageType;
		private Integer EquipmentType;
		private BigDecimal AdditionalWeight;
		private String SpecialInstructions;
		private BigDecimal Length;
		private BigDecimal Width;
		private BigDecimal Height;
		private Integer SizeUom;
		private Integer WeightUom;

		public Builder setServiceCode(ServiceCodes serviceCode) {
			ServiceCode = serviceCode;
			return this;
		}

		public Builder setShippingQty(Integer shippingQty) {
			ShippingQty = shippingQty;
			return this;
		}

		public Builder setPackageType(Integer packageType) {
			PackageType = packageType;
			return this;
		}

		public Builder setEquipmentType(Integer equipmentType) {
			EquipmentType = equipmentType;
			return this;
		}

		public Builder setAdditionalWeight(BigDecimal additionalWeight) {
			AdditionalWeight = additionalWeight;
			return this;
		}

		public Builder setSpecialInstructions(String specialInstructions) {
			SpecialInstructions = specialInstructions;
			return this;
		}

		public Builder setLength(BigDecimal length) {
			Length = length;
			return this;
		}

		public Builder setWidth(BigDecimal width) {
			Width = width;
			return this;
		}

		public Builder setHeight(BigDecimal height) {
			Height = height;
			return this;
		}

		public Builder setSizeUom(Integer sizeUom) {
			SizeUom = sizeUom;
			return this;
		}

		public Builder setWeightUom(Integer weightUom) {
			WeightUom = weightUom;
			return this;
		}

		public RateServices build() {
			return new RateServices(this);
		}

	}
	@JsonProperty("ServiceCode")
	public ServiceCodes getServiceCode() {
		return ServiceCode;
	}
	@JsonProperty("ShippingQty")
	public Integer getShippingQty() {
		return ShippingQty;
	}
	@JsonProperty("PackageType")
	public Integer getPackageType() {
		return PackageType;
	}
	@JsonProperty("EquipmentType")
	public Integer getEquipmentType() {
		return EquipmentType;
	}
	@JsonProperty("AdditionalWeight")
	public BigDecimal getAdditionalWeight() {
		return AdditionalWeight;
	}
	@JsonProperty("SpecialInstructions")
	public String getSpecialInstructions() {
		return SpecialInstructions;
	}
	@JsonProperty("Length")
	public BigDecimal getLength() {
		return Length;
	}
	@JsonProperty("Width")
	public BigDecimal getWidth() {
		return Width;
	}
	@JsonProperty("Height")
	public BigDecimal getHeight() {
		return Height;
	}
	@JsonProperty("SizeUom")
	public Integer getSizeUom() {
		return SizeUom;
	}
	@JsonProperty("WeightUom")
	public Integer getWeightUom() {
		return WeightUom;
	}
}
