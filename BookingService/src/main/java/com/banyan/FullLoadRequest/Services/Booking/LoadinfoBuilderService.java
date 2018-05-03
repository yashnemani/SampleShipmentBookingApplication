package com.banyan.FullLoadRequest.Services.Booking;

import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.Entities.RateQtAddress;
import com.banyan.FullLoadRequest.models.Booking.Loadinfo;

@Service
public class LoadinfoBuilderService {

	public Loadinfo buildLoad(RateQtAddress rtQtAdd) {
		Loadinfo load = new Loadinfo.Builder().setLoadID(null).setManifestID(rtQtAdd.getProNo()).setBOLNumber(rtQtAdd.getBillLadingNo())
				.setCustomerPO(rtQtAdd.getPoNumber()).setBillingID(rtQtAdd.getBilltoAccountNbr()).setInvoiceID(null).setIncoTermID(null).build();
		return load;
	}
}
