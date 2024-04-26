package bip.online.homework111352.service;

import bip.online.homework111352.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService implements CRUDRepository<Student, Long> {

    private final Map<Long, Student> students;
    private Long count;

    public StudentService() {
        this.students = new HashMap<>();
        count = 0L;
    }

    @Override
    public Student save(Student data) {
        if (data != null) {
            data.setId(count);
            students.put(count, data);
            count++;
        } else
            throw new RuntimeException("В системе нельзя хранить null");
        return data;
    }

    @Override
    public void delete(Long id) {
        students.remove(id);
    }

    @Override
    public Student update(Student data) {
        if (data != null) {
            students.replace(data.getId(), data);
        } else
            throw new RuntimeException("В системе нельзя хранить null");
        return data;
    }

    @Override
    public Student findById(Long id) {
        return students.get(id);
    }

    public Collection<Student> findByAge(int age){
        List<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}
