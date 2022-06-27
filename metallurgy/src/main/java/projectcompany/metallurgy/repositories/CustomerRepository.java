package projectcompany.metallurgy.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projectcompany.metallurgy.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    
    @Query("SELECT a FROM Customer a WHERE a.status = true ORDER BY a.name")
    public List<Customer> findByActive();
    
}
