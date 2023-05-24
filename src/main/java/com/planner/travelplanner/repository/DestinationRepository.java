package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Destination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends CrudRepository<Destination, Long> {
    List<Destination> findAll();

    boolean existsByName(String name);
}
