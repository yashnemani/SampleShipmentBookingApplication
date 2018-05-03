package com.banyan.FullLoadRequest.Services.UPS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Booking.Product;
import com.banyan.FullLoadRequest.models.Pickup.UPS.PackagingType;
import com.banyan.FullLoadRequest.models.Pickup.UPS.ShipmentDetail;
import com.banyan.FullLoadRequest.models.Pickup.UPS.UnitOfMeasurement;
import com.banyan.FullLoadRequest.models.Pickup.UPS.Weight;

@Service
public class ShipDetailBuilderService {
	@Autowired
	ShipmentDetail shipDetail;
	@Autowired
	PackagingType pkgType;
	@Autowired
	Weight weight;
	@Autowired
	UnitOfMeasurement UOM;

	public ShipmentDetail buildShipDetail(Product product) {

		/*pkgType.setCode(product.getPackageType().toString());*/
		pkgType.setCode("BAG");
		pkgType.setDesc(product.getDescription());

		UOM.setCode("LBS");
		UOM.setDesc("POUNDS");

		weight.setUom(UOM);
		weight.setValue(product.getWeight().toString());

		shipDetail = new ShipmentDetail.Builder().setDescription(product.getDescription())
				.setHazmatIndicator(String.valueOf(product.isIsHazmat()))
				.setNmbrOfPieces(product.getQuantity().toString()).setPkgType(pkgType).setWeight(weight).build();
		return shipDetail;
	}
}
