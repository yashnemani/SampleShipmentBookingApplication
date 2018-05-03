package com.banyan.FullLoadRequest.Repos;

import java.util.List;
import java.util.Map;

import com.banyan.FullLoadRequest.models.Booking.Loadinfo;

public interface RateQtRepositoryCustom {

	List<String> findAllAccCodes(Integer addId);

	List<String> findBanyanAccCodes(List<String> accCodes);

	String findClientBOL(Integer siteClientCode, Integer siteLocNumber, String carrierCode);
	
	Map<String, Object> getSiteCodes(); 
}
