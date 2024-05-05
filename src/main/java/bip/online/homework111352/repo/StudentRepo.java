package bip.online.homework111352.repo;

import bip.online.homework111352.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);
    Collection<Student>findByAgeBetween(int min,int max);
}
