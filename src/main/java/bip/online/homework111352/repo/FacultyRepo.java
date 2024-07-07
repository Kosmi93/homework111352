package bip.online.homework111352.repo;

import bip.online.homework111352.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface FacultyRepo extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findByColorIgnoreCase(String color);
    Collection<Faculty> findByNameIgnoreCase(String name);

}
