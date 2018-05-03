package com.banyan.FullLoadRequest.Repos;

import java.util.Map;

public interface BookingReferencesRepositoryCustom {

	Map<String, String> getReferencesByID(int id);
}
