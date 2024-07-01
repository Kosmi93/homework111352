package bip.online.homework111352.service;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.model.Student;
import bip.online.homework111352.repo.FacultyRepo;
import bip.online.homework111352.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final FacultyRepo facultyRepo;

    public StudentService(StudentRepo repo, FacultyRepo facultyRepo) {
        this.studentRepo = repo;
        this.facultyRepo = facultyRepo;
    }

    public Collection<Student> findByAge(int age) {
        return studentRepo.findByAge(age);
    }

    public Student update(Student student) {
        return studentRepo.save(student);
    }

    public void delete(Long id) {
        studentRepo.deleteById(id);
    }

    public Optional<Student> findById(Long id) {
        return studentRepo.findById(id);
    }

    public Student save(Student student) {
        return studentRepo.save(student);
    }

    public Collection<Student> findByAge(int min, int max){
        return studentRepo.findByAgeBetween(min,max);
    }

    public Collection<Student> findByFaculty(String name){
        List<Faculty> faculties =  new ArrayList<>(facultyRepo.findByNameIgnoreCase(name));
        if(!faculties.isEmpty()){
            return studentRepo.findStudentByFaculty(faculties.get(0));
        } else
            return List.of();
    }

    public int getCount(){
        return studentRepo.countStudents();
    }

    public double getAvgAge(){
        return studentRepo.avgAge();
    }

    public Collection<Student> getEndFive(){
        return studentRepo.endFiveStudents();
    }
}
