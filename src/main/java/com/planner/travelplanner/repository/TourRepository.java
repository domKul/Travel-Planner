package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Tour;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends CrudRepository<Tour,Long> {
    List<Tour>findAll();
}
