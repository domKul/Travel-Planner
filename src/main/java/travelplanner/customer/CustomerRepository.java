package travelplanner.customer;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByFirstNameAndLastName(@NotNull String firstName, @NotNull String lastName);

    /*
    it's public for creating customers in tests
    it will change in future
     */
}
