package com.banyan.FullLoadRequest.Services.Project44;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banyan.FullLoadRequest.models.Project44.EquipmentIdentifiers;

@Service
public class EquipmentIdentifierService {

	public List<EquipmentIdentifiers> buildEquipmentId(Integer id) {
		
		EquipmentIdentifiers quip = new EquipmentIdentifiers("MOBILE_PHONE_NUMBER","513-410-8284");
		List<EquipmentIdentifiers> identifiers = new ArrayList<>();
		identifiers.add(quip);
	return identifiers;
	}
}
