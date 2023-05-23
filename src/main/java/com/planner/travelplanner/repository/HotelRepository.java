package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    List<Hotel> findAll();

    boolean existsByName(String name);
}
