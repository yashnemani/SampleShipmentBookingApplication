package com.banyan.FullLoadRequest.Services.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.Product;
import com.banyan.FullLoadRequest.models.Pickup.XPO.TotWeight;

@Service
public class WeightBuilderService {

	@Autowired
	TotWeight weight;

	public TotWeight buildWeight(Product product) {

		weight.setWeight(product.getWeight().toString());
		weight.setWeightUom("LBS");
		return weight;
	}
}
