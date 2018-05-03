package com.banyan.FullLoadRequest.models.Pickup.Banyan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banyan.FullLoadRequest.models.Booking.AuthenticationData;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class DispatchLoad_Request {

	private AuthenticationData authData;
	private String LoadID;
	private boolean submitPickup;
	private boolean dispatchOverride;
	@Autowired
	private LoadData loadData;

	public DispatchLoad_Request() {

	}

	public DispatchLoad_Request(Builder build) {

		this.authData = build.authData;
		this.LoadID = build.LoadID;
		this.submitPickup = build.submitPickup;
		this.dispatchOverride = build.dispatchOverride;
		this.loadData = build.loadData;
	}

	public static class Builder {
	
		private AuthenticationData authData;
		private String LoadID;
		private boolean submitPickup;
		private boolean dispatchOverride;
		@Autowired
		private LoadData loadData;

		public Builder setAuthData(AuthenticationData authData) {
			this.authData = authData;
			return this;
		}

		public Builder setLoadID(String loadID) {
			LoadID = loadID;
			return this;
		}

		public Builder setSubmitPickup(boolean submitPickup) {
			this.submitPickup = submitPickup;
			return this;
		}

		public Builder setDispatchOverride(boolean dispatchOverride) {
			this.dispatchOverride = dispatchOverride;
			return this;
		}

		public Builder setLoadData(LoadData loadData) {
			this.loadData = loadData;
			return this;
		}

		public DispatchLoad_Request build() {
			return new DispatchLoad_Request(this);
		}
	}

	@JsonProperty("AuthenticationData")
	public AuthenticationData getAuthData() {
		return authData;
	}

	@JsonProperty("LoadID")
	public String getLoadID() {
		return LoadID;
	}

	@JsonProperty("SubmitPickup")
	public boolean isSubmitPickup() {
		return submitPickup;
	}

	@JsonProperty("DispatchOverride")
	public boolean isDispatchOverride() {
		return dispatchOverride;
	}

	@JsonProperty("LoadData")
	public LoadData getLoadData() {
		return loadData;
	}
}
