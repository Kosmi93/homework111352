package bip.online.homework111352.conroller;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.model.Student;
import bip.online.homework111352.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
@Tag(name = "Контроллер по работе со студентами", description = "Контроллер выполняет операции со студентами в университете")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @Operation(summary = "Регистрация студентов", description = "Позволяет добавлять студентов в систему")
    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        if (student != null) return ResponseEntity.ok(service.save(student));
        else return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получение студента", description = "Позволяет получить студента из системы по его id")
    @GetMapping
    public ResponseEntity<Student> get(@RequestParam Long id) {
        return ResponseEntity.ok(service.findById(id).orElseThrow());
    }

    @Operation(summary = "Удаление студента", description = "Позволяет удалить студента из системы по его id")
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Редактирование студента", description = "Позволяет отредактировать студента в системе")
    @PutMapping("/update")
    public ResponseEntity<Student> update(@RequestBody Student student) {
        return ResponseEntity.ok(service.update(student));
    }

    @Operation(summary = "Поиск студентов конкретного возраста", description = "Позволяет найти студентов с заданным возрастом")
    @GetMapping("/search")
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam int age) {
        if (age > 0) {
            return ResponseEntity.ok(service.findByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Operation(summary = "Поиск студентов по заданному диапазону возрастов", description = "Позволяет найти студентов по заданному диапазону возрастов")
    @GetMapping("/search-age")
    public ResponseEntity<Collection<Student>> findAge(@RequestParam int min, @RequestParam int max) {
        if (max > min) {
            return ResponseEntity.ok(service.findByAge(min, max));
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Поиск студентов факультета", description = "Позволяет найти всех студентов заданном факультете")
    @GetMapping("/search-faculty")
    public ResponseEntity<Collection<Student>> findStudentInFaculty(@RequestParam String name) {
        if (!name.isBlank()) {
            return ResponseEntity.ok(service.findByFaculty(name));
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получение факультета студента", description = "Позволяет получить факультет студента")
    @GetMapping("/faculty")
    public ResponseEntity<Faculty> getFacultyForStudent(@RequestParam Long id) {
        return ResponseEntity.ok(service.findById(id).orElseThrow().getFaculty());

    }
}
