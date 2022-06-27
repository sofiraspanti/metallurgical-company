package projectcompany.metallurgy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectcompany.metallurgy.entities.Quotation;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, String> {
    
}
