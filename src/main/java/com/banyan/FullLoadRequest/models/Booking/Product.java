package com.banyan.FullLoadRequest.models.Booking;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.banyan.FullLoadRequest.models.enums.FreightClasses;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer Quantity;
	private Integer PackageType;
	private BigDecimal Weight;
	public FreightClasses Class;
	private String NMFC;
	private String SKU;
	private boolean IsHazmat;
	private String HazmatPhoneNumber;
	private String HazmatPhoneExt;
	private String Description;
	private BigDecimal Length;
	private BigDecimal Width;
	private BigDecimal Height;
	private Integer UOM;
	private Integer SortOrder;
	private String ReferenceNumber;
	
	private ParcelOptions ParcelOptions;

	public Product() {

	}

	public Product(Builder build) {
		this.Quantity = build.Quantity;
		this.PackageType = build.PackageType;
		this.Weight = build.Weight;
		this.Class = build.Class;
		this.NMFC = build.NMFC;
		this.SKU = build.SKU;
		this.IsHazmat = build.IsHazmat;
		this.HazmatPhoneNumber = build.HazmatPhoneNumber;
		this.HazmatPhoneExt = build.HazmatPhoneExt;
		this.Description = build.Description;
		this.Length = build.Length;
		this.Width = build.Width;
		this.Height = build.Height;
		this.UOM = build.UOM;
		this.SortOrder = build.SortOrder;
		this.ReferenceNumber = build.ReferenceNumber;
		this.ParcelOptions = build.ParcelOptions;
	}

	public static class Builder {
		private Integer Quantity;
		private Integer PackageType;
		private BigDecimal Weight;
		private FreightClasses Class;
		private String NMFC;
		private String SKU;
		private boolean IsHazmat;
		private String HazmatPhoneNumber;
		private String HazmatPhoneExt;
		private String Description;
		private BigDecimal Length;
		private BigDecimal Width;
		private BigDecimal Height;
		private Integer UOM;
		private Integer SortOrder;
		private String ReferenceNumber;
		
		private ParcelOptions ParcelOptions;

		public Builder setQuantity(Integer quantity) {
			Quantity = quantity;
			return this;
		}

		public void setReferenceNumber(String referenceNumber) {
			ReferenceNumber = referenceNumber;
		}

		public Builder setPackageType(Integer packageType) {
			PackageType = packageType;
			return this;
		}

		public Builder setWeight(BigDecimal weight) {
			Weight = weight;
			return this;
		}

		public Builder setClass(FreightClasses class1) {
			Class = class1;
			return this;
		}

		public Builder setNMFC(String nMFC) {
			NMFC = nMFC;
			return this;
		}

		public Builder setSKU(String sKU) {
			SKU = sKU;
			return this;
		}

		public Builder setIsHazmat(boolean isHazmat) {
			IsHazmat = isHazmat;
			return this;
		}

		public Builder setHazmatPhoneNumber(String hazmatPhoneNumber) {
			HazmatPhoneNumber = hazmatPhoneNumber;
			return this;
		}

		public Builder setHazmatPhoneExt(String hazmatPhoneExt) {
			HazmatPhoneExt = hazmatPhoneExt;
			return this;
		}

		public Builder setDescription(String description) {
			Description = description;
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

		public Builder setUOM(Integer uOM) {
			UOM = uOM;
			return this;
		}

		public Builder setSortOrder(Integer sortOrder) {
			SortOrder = sortOrder;
			return this;
		}

		public Builder setParcelOptions(ParcelOptions parcelOptions) {
			ParcelOptions = parcelOptions;
			return this;
		}

		public Product build() {
			return new Product(this);
		}

	}
	@JsonProperty("Quantity")
	public Integer getQuantity() {
		return Quantity;
	}
	@JsonProperty("PackageType")
	public Integer getPackageType() {
		return PackageType;
	}
	@JsonProperty("Weight")
	public BigDecimal getWeight() {
		return Weight;
	}

/*	public Integer getClass() {
		return Class;
	}
*/
	@JsonProperty("NMFC")
	public String getNMFC() {
		return NMFC;
	}
@JsonProperty("SKU")
	public String getSKU() {
		return SKU;
	}
@JsonProperty("IsHazmat")
	public boolean isIsHazmat() {
		return IsHazmat;
	}
@JsonProperty("HazmatPhoneNumber")
	public String getHazmatPhoneNumber() {
		return HazmatPhoneNumber;
	}
@JsonProperty("HazmatPhoneExt")
	public String getHazmatPhoneExt() {
		return HazmatPhoneExt;
	}
@JsonProperty("Description")
	public String getDescription() {
		return Description;
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
@JsonProperty("UOM")
	public Integer getUOM() {
		return UOM;
	}
@JsonProperty("SortOrder")
	public Integer getSortOrder() {
		return SortOrder;
	}
@JsonProperty("ParcelOptions")
	public ParcelOptions getParcelOptions() {
		return ParcelOptions;
	}
@JsonProperty("ReferenceNumber")
public String getReferenceNumber() {
	return ReferenceNumber;
}

}
