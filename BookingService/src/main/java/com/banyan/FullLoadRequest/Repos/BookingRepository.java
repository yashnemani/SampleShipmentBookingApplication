package com.banyan.FullLoadRequest.Repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.banyan.FullLoadRequest.Entities.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer>, BookingRepositoryCustom {

	@Query(nativeQuery = true, value = "delete from booking_queue where rate_ID=:rateId")
	void deleteFromBookingQueue(@Param("rateId") Integer rateId);
}
