package bip.online.homework111352.service;

import bip.online.homework111352.model.Student;
import bip.online.homework111352.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo repo;

    public StudentService(StudentRepo repo) {
        this.repo = repo;
    }

    public Collection<Student> findByAge(int age) {
        return repo.findByAge(age);
    }

    public Student update(Student student) {
        return repo.save(student);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Optional<Student> findById(Long id) {
        return repo.findById(id);
    }

    public Student save(Student student) {
        return repo.save(student);
    }

    public Collection<Student> findByAge(int min, int max){
        return repo.findByAgeBetween(min,max);
    }
}
