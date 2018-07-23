package com.banyan.FullLoadRequest.Services.XPO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.FullLoad_Request;
import com.banyan.FullLoadRequest.models.Pickup.XPO.PkupItem;
import com.banyan.FullLoadRequest.models.Pickup.XPO.TotWeight;

@Service
public class PkupItemBuildService {

	@Autowired
	PkupItem pkupItem;
	@Autowired
	TotWeight weight;
	@Autowired
	WeightBuilderService weightService;

	public PkupItem buildPkupItem(FullLoad_Request fullLoad, boolean test) {

		String remarks = fullLoad.getProducts().get(0).getDescription();
		if (remarks != null)
			if (remarks.length() > 100)
				remarks = remarks.substring(0, 99);
		int qty = fullLoad.getProducts().get(0).getQuantity();
		if (qty == 0)
			qty = 1;
		weight = weightService.buildWeight(fullLoad.getProducts().get(0));
		pkupItem = new PkupItem.Builder().setDestZip(fullLoad.getConsignee().getAddressInfo().getZipcode())
				.setLoosePcsCount(fullLoad.getProducts().get(0).getQuantity()).setPalletCnt(qty).setRemarks(remarks)
				.setHazmatInd(fullLoad.getProducts().get(0).isIsHazmat()).setTotweight(weight).build();

		return pkupItem;
	}
}
