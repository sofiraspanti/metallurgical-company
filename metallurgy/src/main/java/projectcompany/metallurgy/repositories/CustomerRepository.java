package projectcompany.metallurgy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectcompany.metallurgy.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    
}
