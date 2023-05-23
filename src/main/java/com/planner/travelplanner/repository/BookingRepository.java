package com.planner.travelplanner.repository;


import com.planner.travelplanner.domain.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findAll();

    @Query("SELECT b FROM Booking b WHERE b.endDate < :currentDate")
    List<Booking> findExpiredBookings(@Param("currentDate") Date currentDate);
}
