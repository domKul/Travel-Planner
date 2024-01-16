package com.planner.travelplanner.booking;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.endDate < :currentDate")
    List<Booking> findExpiredBookings(@Param("currentDate") Date currentDate);

    boolean existsByDestination_DestinationIdAndCustomer_CustomerId(long destinationName,long customerId);
}
