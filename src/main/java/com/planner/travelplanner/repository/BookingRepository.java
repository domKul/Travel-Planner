package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking,Long> {

    List<Booking>findAll();
}
