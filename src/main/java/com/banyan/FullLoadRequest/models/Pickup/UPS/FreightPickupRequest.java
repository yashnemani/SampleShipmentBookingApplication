package com.banyan.FullLoadRequest.models.Pickup.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class FreightPickupRequest {

	@Autowired
	private Request request;
	private String additionalComments;
	private String destinationPostalCode;
	private String destinationCountryCode;
	@Autowired
	private Requester requester;
	@Autowired
	private ShipFrom shipFrom;
	@Autowired
	private ShipTo shipTo;
	@Autowired
	private ShipmentDetail shipDetail;
	private String pickupDate;
	private String earliestTimeReady;
	private String latestTimeReady;
	private String pickupInstructions;
	private String handlingInstructions;
	private String specialInstructions;
	private String deliveryInstructions;

	public FreightPickupRequest() {
	}

	public FreightPickupRequest(Builder build) {
		this.request = build.request;
		this.additionalComments = build.additionalComments;
		this.destinationPostalCode = build.destinationPostalCode;
		this.destinationCountryCode = build.destinationCountryCode;
		this.requester = build.requester;
		this.shipFrom = build.shipFrom;
		this.shipTo = build.shipTo;
		this.shipDetail = build.shipDetail;
		this.pickupDate = build.pickupDate;
		this.earliestTimeReady = build.earliestTimeReady;
		this.latestTimeReady = build.latestTimeReady;
		this.pickupInstructions = build.pickupInstructions;
		this.handlingInstructions = build.handlingInstructions;
		this.specialInstructions = build.specialInstructions;
		this.deliveryInstructions = build.deliveryInstructions;
	}

	public static class Builder {
		@Autowired
		private Request request;
		private String additionalComments;
		private String destinationPostalCode;
		private String destinationCountryCode;
		@Autowired
		private Requester requester;
		@Autowired
		private ShipFrom shipFrom;
		@Autowired
		private ShipTo shipTo;
		@Autowired
		private ShipmentDetail shipDetail;
		private String pickupDate;
		private String earliestTimeReady;
		private String latestTimeReady;
		private String pickupInstructions;
		private String handlingInstructions;
		private String specialInstructions;
		private String deliveryInstructions;

		public Builder setRequest(Request request) {
			this.request = request;
			return this;
		}

		public Builder setAdditionalComments(String additionalComments) {
			this.additionalComments = additionalComments;
			return this;
		}

		public Builder setDestinationPostalCode(String destinationPostalCode) {
			this.destinationPostalCode = destinationPostalCode;
			return this;
		}

		public Builder setDestinationCountryCode(String destinationCountryCode) {
			this.destinationCountryCode = destinationCountryCode;
			return this;
		}

		public Builder setRequester(Requester requester) {
			this.requester = requester;
			return this;
		}

		public Builder setShipFrom(ShipFrom shipFrom) {
			this.shipFrom = shipFrom;
			return this;
		}

		public Builder setShipTo(ShipTo shipTo) {
			this.shipTo = shipTo;
			return this;
		}

		public Builder setShipDetail(ShipmentDetail shipDetail) {
			this.shipDetail = shipDetail;
			return this;
		}

		public Builder setPickupDate(String pickupDate) {
			this.pickupDate = pickupDate;
			return this;
		}

		public Builder setEarliestTimeReady(String earliestTimeReady) {
			this.earliestTimeReady = earliestTimeReady;
			return this;
		}

		public Builder setLatestTimeReady(String latestTimeReady) {
			this.latestTimeReady = latestTimeReady;
			return this;
		}

		public Builder setPickupInstructions(String pickupInstructions) {
			this.pickupInstructions = pickupInstructions;
			return this;
		}

		public Builder setHandlingInstructions(String handlingInstructions) {
			this.handlingInstructions = handlingInstructions;
			return this;
		}

		public Builder setSpecialInstructions(String specialInstructions) {
			this.specialInstructions = specialInstructions;
			return this;
		}

		public Builder setDeliveryInstructions(String deliveryInstructions) {
			this.deliveryInstructions = deliveryInstructions;
			return this;
		}

		public FreightPickupRequest build() {
			return new FreightPickupRequest(this);
		}
	}

	@JsonProperty("Request")
	public Request getRequest() {
		return request;
	}

	@JsonProperty("AdditionalComments")
	public String getAdditionalComments() {
		return additionalComments;
	}

	@JsonProperty("DestinationPostalCode")
	public String getDestinationPostalCode() {
		return destinationPostalCode;
	}

	@JsonProperty("DestinationCountryCode")
	public String getDestinationCountryCode() {
		return destinationCountryCode;
	}

	@JsonProperty("Requester")
	public Requester getRequester() {
		return requester;
	}

	@JsonProperty("ShipFrom")
	public ShipFrom getShipFrom() {
		return shipFrom;
	}

	@JsonProperty("ShipTo")
	public ShipTo getShipTo() {
		return shipTo;
	}

	@JsonProperty("ShipmentDetail")
	public ShipmentDetail getShipDetail() {
		return shipDetail;
	}

	@JsonProperty("PickupDate")
	public String getPickupDate() {
		return pickupDate;
	}

	@JsonProperty("EarliestTimeReady")
	public String getEarliestTimeReady() {
		return earliestTimeReady;
	}

	@JsonProperty("LatestTimeReady")
	public String getLatestTimeReady() {
		return latestTimeReady;
	}

	@JsonProperty("PickupInstructions")
	public String getPickupInstructions() {
		return pickupInstructions;
	}

	@JsonProperty("HandlingInstructions")
	public String getHandlingInstructions() {
		return handlingInstructions;
	}

	@JsonProperty("SpecialInstructions")
	public String getSpecialInstructions() {
		return specialInstructions;
	}

	@JsonProperty("DeliveryInstructions")
	public String getDeliveryInstructions() {
		return deliveryInstructions;
	}

}
