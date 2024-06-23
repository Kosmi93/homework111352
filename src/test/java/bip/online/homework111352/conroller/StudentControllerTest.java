package bip.online.homework111352.conroller;


import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.model.Student;
import bip.online.homework111352.repo.FacultyRepo;
import bip.online.homework111352.repo.StudentRepo;
import bip.online.homework111352.service.AvatarService;
import bip.online.homework111352.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static bip.online.homework111352.TestConstant.*;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private StudentController controller;

    @Autowired
    private StudentService studentService;
    @Autowired
    private FacultyRepo facultyRepo;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(controller).isNotNull();
    }

    @Test
    void save() {
        ResponseEntity<Student> newStudentRes = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/student",
                MOCK_STUDENT,
                Student.class);
        Assertions.assertThat(newStudentRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(newStudentRes.getBody().getName()).isEqualTo(MOCK_STUDENT.getName());
        Assertions.assertThat(newStudentRes.getBody().getAge()).isEqualTo(MOCK_STUDENT.getAge());
        Assertions.assertThat(newStudentRes.getBody().getId()).isNotNull();
    }

    @Test
    void getTest() {
        Student student = studentService.save(MOCK_STUDENT);
        ResponseEntity<Student> getStudentRes = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/student?id=" + student.getId(),
                Student.class);
        Assertions.assertThat(getStudentRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(getStudentRes.getBody().getName()).isEqualTo(student.getName());
        Assertions.assertThat(getStudentRes.getBody().getAge()).isEqualTo(student.getAge());
        Assertions.assertThat(getStudentRes.getBody().getId()).isNotNull();
    }

    @Test
    void deleteTest() {
        Student student = studentService.save(MOCK_STUDENT);
        this.restTemplate.delete("http://localhost:" + port + "/student?id=" + student.getId());
        ResponseEntity<Student> getStudentRes = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/student?id=" + student.getId(),
                Student.class);
        Assertions.assertThat(getStudentRes.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void updateTest() {
        Student student = studentService.save(MOCK_STUDENT);
        student.setName(MOCK_STUDENT_NEW_NAME);
        this.restTemplate.put(
                "http://localhost:" + port + "/student/update",
                student,
                Student.class);

        ResponseEntity<Student> getStudentRes = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/student?id=" + student.getId(),
                Student.class);

        Assertions.assertThat(getStudentRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(getStudentRes.getBody().getName()).isEqualTo(MOCK_STUDENT_NEW_NAME);
    }

    @Test
    void findAge() {
        Student student = studentService.save(MOCK_STUDENT);
        List<Student> students = this.restTemplate.exchange(
                "http://localhost:" + port + "/student/search?age=" + MOCK_STUDENT_AGE,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {
                }
        ).getBody();

        assertFalse(students.isEmpty());

    }

    @Test
    void findAgeBetween() {
        Student student = studentService.save(MOCK_STUDENT);
        int min = MOCK_STUDENT_AGE - 1;
        int max = MOCK_STUDENT_AGE + 1;
        List<Student> students = this.restTemplate.exchange(
                "http://localhost:" + port + "/student/search-age?min=" + min + "&max=" + max,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {
                }
        ).getBody();

        assertFalse(students.isEmpty());

    }

    @Test
    void findStudentInFaculty() {
        Faculty faculty = facultyRepo.save(MOCK_FACULTY);

        Student student = MOCK_STUDENT;
        student.setFaculty(faculty);
        student = studentService.save(student);
        List<Student> students = this.restTemplate.exchange(
                "http://localhost:" + port + "/student/search-faculty?name=" + MOCK_FACULTY_NAME,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {
                }
        ).getBody();
        assertFalse(students.isEmpty());
    }

    @Test
    void getFacultyForStudent() {
        Faculty faculty = facultyRepo.save(MOCK_FACULTY);
        Student student = MOCK_STUDENT;
        student.setFaculty(faculty);
        student = studentService.save(student);

        ResponseEntity<Faculty> getFacultyRes = this.restTemplate.getForEntity(
                "http://localhost:" + port + "/student/faculty?id=" + student.getId(),
                Faculty.class);
        Assertions.assertThat(getFacultyRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(getFacultyRes.getBody().getName()).isEqualTo(MOCK_FACULTY_NAME);
        Assertions.assertThat(getFacultyRes.getBody().getColor()).isEqualTo(MOCK_FACULTY_COLOR);
    }
}