package com.banyan.FullLoadRequest.Repos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.banyan.FullLoadRequest.Entities.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer>, BookingRepositoryCustom {

	@Query(nativeQuery = true, value = "delete from booking_queue where rate_ID=:rateId")
	void deleteFromBookingQueue(@Param("rateId") Integer rateId);
	
	@Query(nativeQuery=true, value="select last_timestamp from booking_queue where id = 0")
	Timestamp getLastTimestamp();
	
	@Query(nativeQuery = true, value = "select rate_ID from booking_queue where id != 0")
	List<BigDecimal> findAllFromQueue();
}
