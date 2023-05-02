package com.planner.travelplanner.repository;

import com.planner.travelplanner.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
