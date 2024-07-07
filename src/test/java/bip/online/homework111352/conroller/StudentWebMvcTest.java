package bip.online.homework111352.conroller;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.model.Student;
import bip.online.homework111352.repo.AvatarRepo;
import bip.online.homework111352.repo.FacultyRepo;
import bip.online.homework111352.repo.StudentRepo;
import bip.online.homework111352.service.AvatarService;
import bip.online.homework111352.service.FacultyService;
import bip.online.homework111352.service.ImageResize;
import bip.online.homework111352.service.StudentService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepo facultyRepo;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @MockBean
    private AvatarRepo avatarRepo;

    @SpyBean
    private AvatarService avatarService;

    @SpyBean
    private ImageResize imageResize;
    @InjectMocks
    private AvatarController avatarController;

    @MockBean
    private StudentRepo studentRepo;

    @SpyBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void saveTest() throws Exception {
        Long id = 555L;
        String name = "Инокентий";

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        Student student =new Student();
        student.setId(id);
        student.setName(name);

        when(studentRepo.save(any(Student.class))).thenReturn(student);
        when(studentRepo.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student") //send
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

    }

    @Test
    public void updateTest() throws Exception {
        Long id = 555L;
        String name = "Инокентий";

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        Student student =new Student();
        student.setId(id);
        student.setName(name);

        when(studentRepo.save(any(Student.class))).thenReturn(student);
        when(studentRepo.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/update") //send
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

    }

    @Test
    public void getTest() throws Exception {
        Long id = 555L;
        String name = "Инокентий";

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        Student student =new Student();
        student.setId(id);
        student.setName(name);


        when(studentRepo.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?id="+id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void searchTest() throws Exception {
        Long id = 555L;
        String name = "Инокентий";
        int age = 20;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        Student student =new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);


        when(studentRepo.findByAge(any(Integer.class))).thenReturn(List.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/search?age="+age) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());//receive;
    }

    @Test
    public void searchAgePeriodTest() throws Exception {
        Long id = 555L;
        String name = "Инокентий";
        int age = 20;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        Student student =new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        int min = age-1;
        int max = age+1;


        when(studentRepo.findByAge(any(Integer.class))).thenReturn(List.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student//search-age?min="+min+"&max="+max) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());//receive;
    }

    @Test
    public void searchStudentsInFacultyTest() throws Exception {

        Long facultyId = 555L;
        String facultyName = "Red";
        String facultyColor = "Red";
        Faculty faculty = new Faculty();
        faculty.setName(facultyName);
        faculty.setColor(facultyColor);
        faculty.setId(facultyId);

        Long id = 555L;
        String name = "Инокентий";
        int age = 20;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        Student student =new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setFaculty(faculty);

        when(studentRepo.findByAgeBetween(any(Integer.class),any(Integer.class))).thenReturn(List.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/search-faculty?name="+facultyName) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());//receive;
    }
}
