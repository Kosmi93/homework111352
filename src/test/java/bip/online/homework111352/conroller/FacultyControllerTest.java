package bip.online.homework111352.conroller;



import bip.online.homework111352.model.Faculty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTest {
    @LocalServerPort
    int port;
    @Autowired
    private FacultyController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void findFacultiesColorTest() {
        Assertions.assertThat(this
                .restTemplate
                .getForObject("http://localhost:"+port+"/faculty/search?color=red",String.class))
                .isNotNull();
    }
    @Test
    void findFacultiesNameTest() {
        Assertions.assertThat(this
                        .restTemplate
                        .getForObject("http://localhost:"+port+"/faculty/search?name=red",String.class))
                .isNotNull();
    }


    @Test
    void save() {
        Faculty faculty = new Faculty();
        faculty.setColor("Black");
        faculty.setName("Black");
        String result = this
                .restTemplate
                .postForObject("http://localhost:"+port+"/faculty",faculty,String.class);
        Assertions.assertThat(result).isNotNull();
        long id = Long.parseLong(result.substring(result.indexOf(':')+1,result.indexOf(',')));
        this.restTemplate.delete("http://localhost:"+port+"/faculty?id="+id);

    }

    @Test
    void get() {
        Assertions.assertThat(this
                .restTemplate
                .getForObject("http://localhost:"+port+"/faculty?=2", String.class)).isNotNull();
    }
}