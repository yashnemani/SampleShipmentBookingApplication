package com.banyan.FullLoadRequest.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.banyan.FullLoadRequest.Entities.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer>,BookingRepositoryCustom {
}
