package bip.online.homework111352.conroller;


import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.model.Student;
import bip.online.homework111352.service.FacultyService;
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

import java.util.List;

import static bip.online.homework111352.TestConstant.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTest {
    @LocalServerPort
    int port;
    @Autowired
    private FacultyController controller;

    @Autowired
    FacultyService facultyService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void save() {
        ResponseEntity<Faculty> newFacultyRes = this.restTemplate.postForEntity("http://localhost:" + port + "/faculty",
                MOCK_FACULTY,
                Faculty.class);

        Assertions.assertThat(newFacultyRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(newFacultyRes.getBody().getName()).isEqualTo(MOCK_FACULTY_NAME);
        Assertions.assertThat(newFacultyRes.getBody().getColor()).isEqualTo(MOCK_FACULTY_COLOR);
        Assertions.assertThat(newFacultyRes.getBody().getId()).isNotNull();

    }

    @Test
    void getTest() {
        Faculty faculty = facultyService.save(MOCK_FACULTY);
        ResponseEntity<Faculty> getFacultyRes = this.restTemplate.getForEntity("http://localhost:"
                        + port + "/faculty?id="+faculty.getId(),
               Faculty.class);

        Assertions.assertThat(getFacultyRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(getFacultyRes.getBody().getName()).isEqualTo(MOCK_FACULTY_NAME);
        Assertions.assertThat(getFacultyRes.getBody().getColor()).isEqualTo(MOCK_FACULTY_COLOR);
        Assertions.assertThat(getFacultyRes.getBody().getId()).isNotNull();
    }

    @Test
    void deleteTest() {
        Faculty faculty = facultyService.save(MOCK_FACULTY);

        this.restTemplate.delete("http://localhost:"+ port + "/faculty?id="+faculty.getId());
        ResponseEntity<Faculty> getFacultyRes = this.restTemplate.getForEntity("http://localhost:"
                        + port + "/faculty?id="+faculty.getId(),
                Faculty.class);

        Assertions.assertThat(getFacultyRes.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void updateTest() {
        Faculty faculty = facultyService.save(MOCK_FACULTY);
        faculty.setName(MOCK_FACULTY_NEW_NAME);
        this.restTemplate.put(
                "http://localhost:" + port + "/faculty/update",
                faculty,
                Student.class);

        ResponseEntity<Faculty> getFacultyRes = this.restTemplate.getForEntity(
                "http://localhost:" + port + "//faculty?id=" + faculty.getId(),
                Faculty.class);

        Assertions.assertThat(getFacultyRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(getFacultyRes.getBody().getName()).isEqualTo(MOCK_FACULTY_NEW_NAME);
    }

    @Test
    void findFacultiesColorTest() {
        Faculty faculty = facultyService.save(MOCK_FACULTY);

        List<Faculty> faculties = this.restTemplate.exchange(
                "http://localhost:" + port + "/faculty/search?color="+MOCK_FACULTY_COLOR,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Faculty>>() {
                }
        ).getBody();

        assertFalse(faculties.isEmpty());
    }

    @Test
    void getAllFaculty() {
        Faculty faculty = facultyService.save(MOCK_FACULTY);

        List<Faculty> faculties = this.restTemplate.exchange(
                "http://localhost:" + port + "/faculty/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Faculty>>() {
                }
        ).getBody();

        assertFalse(faculties.isEmpty());
    }


}