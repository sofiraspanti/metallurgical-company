package projectcompany.metallurgy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectcompany.metallurgy.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String>{
    
}
