package bip.online.homework111352.service;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.model.Student;
import bip.online.homework111352.repo.FacultyRepo;
import bip.online.homework111352.repo.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    private final FacultyRepo facultyRepo;
    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepo repo, FacultyRepo facultyRepo) {
        this.studentRepo = repo;
        this.facultyRepo = facultyRepo;
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Поиск студентов заданного возраста");
        return studentRepo.findByAge(age);
    }
    public Collection<Student> findByAll(){
        return studentRepo.findAll();
    }

    public Student update(Student student) {
        logger.info("Обновление информации о студенте");
        return studentRepo.save(student);
    }

    public void delete(Long id) {
        logger.info("Удаление студента по id");
        studentRepo.deleteById(id);
    }

    public Optional<Student> findById(Long id) {
        logger.info("Поиск студента по id");
        return studentRepo.findById(id);
    }

    public Student save(Student student) {
        logger.info("Сохранение информации о студенте");
        return studentRepo.save(student);
    }

    public Collection<Student> findByAge(int min, int max){
        logger.info("Поиск студентов а заданном диапазоне возрастов");
        return studentRepo.findByAgeBetween(min,max);
    }

    public Collection<Student> findByFaculty(String name){
        logger.info("Поиск студентов по факультету");
        List<Faculty> faculties =  new ArrayList<>(facultyRepo.findByNameIgnoreCase(name));
        if(!faculties.isEmpty()){
            return studentRepo.findStudentByFaculty(faculties.get(0));
        } else
            return List.of();
    }

    public int getCount(){
        logger.info("Получение количества студентов");
        return studentRepo.countStudents();
    }

    public double getAvgAge(){
        logger.info("Получение среднего возраста студентов");
        return studentRepo.avgAge();
    }

    public Collection<Student> getEndFive(){
        logger.info("Получение");
        return studentRepo.endFiveStudents();
    }
}
