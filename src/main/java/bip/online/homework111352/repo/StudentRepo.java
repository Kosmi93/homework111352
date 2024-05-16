package bip.online.homework111352.repo;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);
    Collection<Student>findByAgeBetween(int min,int max);
    Collection<Student>findStudentByFaculty(Faculty faculty);

}
