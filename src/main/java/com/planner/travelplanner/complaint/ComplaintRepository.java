package com.planner.travelplanner.complaint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
