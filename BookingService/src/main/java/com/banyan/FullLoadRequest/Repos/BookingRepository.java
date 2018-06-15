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

	@Query(nativeQuery = true, value = "select last_timestamp from booking_queue where id = 0")
	Timestamp getLastTimestamp();

	@Query(nativeQuery = true, value = "select rate_ID from booking_queue where id != 0")
	List<BigDecimal> findAllFromQueue();

	@Query(nativeQuery = true, value = "select booking_id from booking where provider_id=:provider order by booking_id  OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY ")
	List<BigDecimal> findBookingByProvider(@Param("provider") int provider);

	@Query(nativeQuery = true, value = "select bookingid from booking_currentstatus where shipment_status=:status  order by bookingid desc OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY ")
	List<BigDecimal> findBookingByStatus(@Param("status") String status);

	@Query(nativeQuery = true, value = "select bookingid from booking_currentstatus where shipment_state=:state  order by bookingid desc OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY ")
	List<BigDecimal> findBookingByState(@Param("state") String state);

	@Query(nativeQuery = true, value = "select cs.bookingid from booking_currentstatus cs join booking b on b.booking_id=cs.bookingid where b.provider_id=:provider and cs.shipment_status=:status  order by b.booking_id desc OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY ")
	List<BigDecimal> findBookingByStatusAndProvider(@Param("status") String status, @Param("provider") int provider);

	@Query(nativeQuery = true, value = "select cs.bookingid from booking_currentstatus cs join booking b on b.booking_id=cs.bookingid where b.provider_id=:provider and cs.shipment_state=:state  order by b.booking_id desc OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY ")
	List<BigDecimal> findBookingByStateAndProvider(@Param("state") String state, @Param("provider") int provider);

	@Query(nativeQuery = true, value = "select booking_id from banyan_update_queue")
	List<BigDecimal> getFromUpdateQueue();

	@Query(nativeQuery = true, value = "select booking_id from booking where carrier_code=:scac and booking_id in (select booking_id from booking_reference where reference=:ref)")
	List<BigDecimal> getBookingByScacAndReference(@Param("scac") String scac, @Param("ref") String ref);

}
