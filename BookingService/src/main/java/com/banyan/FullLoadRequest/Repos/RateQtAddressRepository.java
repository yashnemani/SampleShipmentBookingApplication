package com.banyan.FullLoadRequest.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.banyan.FullLoadRequest.Entities.RateQtAddress;

public interface RateQtAddressRepository extends CrudRepository<RateQtAddress, Integer>, RateQtAddressRepositoryCustom {

	@Query(nativeQuery = true, value = "select * from Rate_Quote_Address_VW where rt_qte_id=:qt_id")
	List<RateQtAddress> FindAllByRtQteId(@Param("qt_id") Integer rtQteId);

	@Query(nativeQuery = true, value = "select name from client where code=:code")
	String findClientName(@Param("code") int clientCode);

	@Query(nativeQuery=true, value="select name from carrier where code=:code")
	String findCarrierNameByCode(@Param("code") String carrierCode);
}
