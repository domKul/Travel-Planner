package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Tour;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TourRepository extends CrudRepository<Tour,Long> {
    List<Tour>findAll();
}
