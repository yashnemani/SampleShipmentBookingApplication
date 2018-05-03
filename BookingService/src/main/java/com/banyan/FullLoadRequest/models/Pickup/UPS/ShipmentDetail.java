package com.banyan.FullLoadRequest.models.Pickup.UPS;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class ShipmentDetail {

	private String hazmatIndicator;
	private PackagingType pkgType;
	private String nmbrOfPieces;
	private String description;
	private Weight weight;
	
	public ShipmentDetail() {
	}
	
	public ShipmentDetail(Builder build) {
		
		this.hazmatIndicator = build.hazmatIndicator;
		this.pkgType = build.pkgType;
		this.nmbrOfPieces = build.nmbrOfPieces;
		this.description =build.description;
		this.weight = build.weight;
	}
	
	@JsonProperty("HazMatIndicator")
	public String getHazmatIndicator() {
		return hazmatIndicator;
	}
	@JsonProperty("PackagingType")
	public PackagingType getPkgType() {
		return pkgType;
	}
	@JsonProperty("NumberOfPieces")
	public String getNmbrOfPieces() {
		return nmbrOfPieces;
	}
	@JsonProperty("DescriptionOfCommodity")
	public String getDescription() {
		return description;
	}
	@JsonProperty("Weight")
	public Weight getWeight() {
		return weight;
	}


	public static class Builder {
		
		private String hazmatIndicator;
		private PackagingType pkgType;
		private String nmbrOfPieces;
		private String description;
		private Weight weight;
		
		public Builder setHazmatIndicator(String hazmatIndicator) {
			this.hazmatIndicator = hazmatIndicator;
			return this;
		}
		public Builder setPkgType(PackagingType pkgType) {
			this.pkgType = pkgType;
			return this;
		}
		public Builder setNmbrOfPieces(String nmbrOfPieces) {
			this.nmbrOfPieces = nmbrOfPieces;
			return this;
		}
		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}
		public Builder setWeight(Weight weight) {
			this.weight = weight;
			return this;
		}
		
		public ShipmentDetail build() {
			return new ShipmentDetail(this);
		}
	}
}
