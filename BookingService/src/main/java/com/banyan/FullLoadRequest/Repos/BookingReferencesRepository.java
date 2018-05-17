package com.banyan.FullLoadRequest.Repos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.banyan.FullLoadRequest.Entities.BookingReferences;

public interface BookingReferencesRepository
		extends CrudRepository<BookingReferences, Integer>, BookingReferencesRepositoryCustom {

	@Query(nativeQuery = true, value = "select name from carrier where code=:code")
	String findCarrierNameByCode(@Param("code") String carrierCode);
}
