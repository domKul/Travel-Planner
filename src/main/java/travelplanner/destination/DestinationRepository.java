package travelplanner.destination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DestinationRepository extends JpaRepository<Destination, Long> {
    boolean existsByName(String name);
}
