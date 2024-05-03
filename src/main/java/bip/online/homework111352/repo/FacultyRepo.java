package bip.online.homework111352.repo;

import bip.online.homework111352.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findByColor(String color);
}
