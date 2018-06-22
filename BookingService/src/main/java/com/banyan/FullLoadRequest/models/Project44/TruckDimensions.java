package com.banyan.FullLoadRequest.models.Project44;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class TruckDimensions {

	private Double length;
	private Double width;
	private Double height;
	private String unitOfMeasure;

	public TruckDimensions() {
		super();
		this.length=0.0;
		this.width=0.0;
		this.height=0.0;
		this.unitOfMeasure="IN";
	}

	public TruckDimensions(Builder build) {
		this.length = build.length;
		this.width = build.width;
		this.height = build.height;
		this.unitOfMeasure = build.unitOfMeasure;
	}

	public static class Builder {

		private Double length;
		private Double width;
		private Double height;
		private String unitOfMeasure;

		public Builder setLength(Double length) {
			this.length = length;
			return this;
		}

		public Builder setWidth(Double width) {
			this.width = width;
			return this;
		}

		public Builder setHeight(Double height) {
			this.height = height;
			return this;
		}

		public Builder setUnitOfMeasure(String unitOfMeasure) {
			this.unitOfMeasure = unitOfMeasure;
			return this;
		}

		public TruckDimensions build() {
			return new TruckDimensions(this);
		}
	}

	@JsonProperty("length")
	public Double getLength() {
		return length;
	}

	@JsonProperty("width")
	public Double getWidth() {
		return width;
	}

	@JsonProperty("height")
	public Double getHeight() {
		return height;
	}

	@JsonProperty("unitOfMeasure")
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
}
