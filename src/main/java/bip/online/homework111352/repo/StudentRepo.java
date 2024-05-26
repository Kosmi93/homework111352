package bip.online.homework111352.repo;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);
    Collection<Student>findByAgeBetween(int min,int max);
    Collection<Student>findStudentByFaculty(Faculty faculty);
    @Query(value = "SELECT count (*) FROM Student",nativeQuery = true)
    Integer countStudents();
    @Query(value = "SELECT AVG (age) FROM Student",nativeQuery = true)
    Double avgAge();
    @Query(value = "SELECT * FROM Student",nativeQuery = true)
    Collection<Student> endFiveStudents();


}
