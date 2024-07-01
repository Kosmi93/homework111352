package bip.online.homework111352.conroller;


import bip.online.homework111352.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private StudentController controller;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(controller).isNotNull();
    }

    @Test
    void save() {
        Student student = new Student();
        student.setAge(20);
        student.setName("Ivan");

        String str = this.restTemplate
                .postForObject("http://localhost:" + port + "/student", student, String.class);
        Assertions.assertThat(str).isNotNull();
        long id = Long.parseLong(str.substring(str.indexOf(':') + 1, str.indexOf(',')));
        this.restTemplate.delete("http://localhost:" + port + "/student?id=" + id);

    }

    @Test
    void get() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?id=2", String.class))
                .isNotNull();
    }

    @Test
    void findStudents() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/search?age=22", String.class))
                .isNotNull();
    }

    @Test
    void findAge() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/search-age?min=2&max=33", String.class))
                .isNotNull();
    }

    @Test
    void findStudentInFaculty() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/search-faculty?id=2", String.class))
                .isNotNull();
    }

    @Test
    void getFacultyForStudent() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/faculty?name=Orange", String.class))
                .isNotNull();
    }
}