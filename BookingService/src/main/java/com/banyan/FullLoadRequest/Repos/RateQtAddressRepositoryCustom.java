package com.banyan.FullLoadRequest.Repos;

import java.util.List;

import com.banyan.FullLoadRequest.models.Booking.Address;
import com.banyan.FullLoadRequest.models.Booking.Contact;

public interface RateQtAddressRepositoryCustom {

	// Find Client Site Location Address for Banyan BillTo
	Address findSiteAddress(int locNum, int siteCode);

	//Find Client Site Location Contact for Banyan BillTo
	Contact findSiteContact(int siteCode, int locNum);
}
