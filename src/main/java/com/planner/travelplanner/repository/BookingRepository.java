package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking,Long> {

    List<Booking>findAll();
}
