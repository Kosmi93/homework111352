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

import static bip.online.homework111352.TestConstant.*;
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

        when(studentRepo.save(any(Student.class))).thenReturn(MOCK_STUDENT);
        JSONObject createStudent = new JSONObject();
        createStudent.put("name", MOCK_STYDENT_NAME);
        createStudent.put("age", MOCK_STYDENT_AGE);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student") //send
                        .content(createStudent.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(MOCK_STYDENT_ID))
                .andExpect(jsonPath("$.age").value(MOCK_STYDENT_AGE))
                .andExpect(jsonPath("$.name").value(MOCK_STYDENT_NAME));

    }

    @Test
    public void updateTest() throws Exception {

        when(studentRepo.save(any(Student.class))).thenReturn(MOCK_STUDENT);
        JSONObject createStudent = new JSONObject();
        createStudent.put("id", MOCK_STYDENT_ID);
        createStudent.put("name", MOCK_STYDENT_NAME);
        createStudent.put("age", MOCK_STYDENT_AGE);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/update") //send
                        .content(createStudent.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(MOCK_STYDENT_ID))
                .andExpect(jsonPath("$.age").value(MOCK_STYDENT_AGE))
                .andExpect(jsonPath("$.name").value(MOCK_STYDENT_NAME));

    }

    @Test
    public void getTest() throws Exception {
        when(studentRepo.findById(any(Long.class))).thenReturn(Optional.of(MOCK_STUDENT));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?id=" + MOCK_STYDENT_ID) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(MOCK_STYDENT_ID))
                .andExpect(jsonPath("$.age").value(MOCK_STYDENT_AGE))
                .andExpect(jsonPath("$.name").value(MOCK_STYDENT_NAME));
    }

    @Test
    public void searchTest() throws Exception {
        when(studentRepo.findByAge(any(Integer.class))).thenReturn(MOCK_STUDENTS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/search?age=" + MOCK_STYDENT_AGE) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());//receive;
    }

    @Test
    public void searchAgePeriodTest() throws Exception {

        int min = MOCK_STYDENT_AGE - 1;
        int max = MOCK_STYDENT_AGE + 1;

        when(studentRepo.findByAge(any(Integer.class))).thenReturn(MOCK_STUDENTS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student//search-age?min=" + min + "&max=" + max) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());//receive;
    }

    @Test
    public void searchStudentsInFacultyTest() throws Exception {
        when(studentRepo.findByAgeBetween(any(Integer.class), any(Integer.class))).thenReturn(MOCK_STUDENTS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/search-faculty?name=" + MOCK_FACULTY_NAME) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());//receive;
    }

    @Test
    public void searchFacultyForStudentTest() throws Exception {
        when(studentRepo.findById(any(Long.class))).thenReturn(Optional.of(MOCK_STUDENT));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/faculty?id=" + MOCK_FACULTY_ID) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());//receive;
    }
}
