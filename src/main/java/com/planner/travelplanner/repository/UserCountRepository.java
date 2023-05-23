package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.UserCount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCountRepository extends CrudRepository<UserCount, Long> {

    List<UserCount> findAll();
}
