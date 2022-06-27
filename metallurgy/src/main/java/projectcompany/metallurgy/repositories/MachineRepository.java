package projectcompany.metallurgy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectcompany.metallurgy.entities.Machine;

@Repository
public interface MachineRepository extends JpaRepository<Machine, String>{
    
}
