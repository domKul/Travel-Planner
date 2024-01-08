package com.planner.travelplanner.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LocationRepository extends JpaRepository<Location, Long> {
}
