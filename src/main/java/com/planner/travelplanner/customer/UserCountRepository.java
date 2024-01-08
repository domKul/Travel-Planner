package com.planner.travelplanner.customer;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserCountRepository extends JpaRepository<UserCount, Long> {
}
