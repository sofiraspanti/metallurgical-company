package projectcompany.metallurgy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectcompany.metallurgy.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    
}
