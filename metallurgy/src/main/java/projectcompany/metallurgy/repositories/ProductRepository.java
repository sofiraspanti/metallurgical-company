package projectcompany.metallurgy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectcompany.metallurgy.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    
}
