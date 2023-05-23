package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Complaint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, Long> {
    List<Complaint> findAll();


}
