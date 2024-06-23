package bip.online.homework111352.conroller;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.repo.AvatarRepo;
import bip.online.homework111352.repo.FacultyRepo;
import bip.online.homework111352.repo.StudentRepo;
import bip.online.homework111352.service.AvatarService;
import bip.online.homework111352.service.FacultyService;
import bip.online.homework111352.service.ImageResize;
import bip.online.homework111352.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Optional;

import static bip.online.homework111352.TestConstant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class FacultyWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepo facultyRepo;

    private ObjectMapper mapper = new ObjectMapper();

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

        when(facultyRepo.save(any(Faculty.class))).thenReturn(MOCK_FACULTY);

        JSONObject createFaculty = new JSONObject();
        createFaculty.put("name", MOCK_FACULTY_NAME);
        createFaculty.put("color", MOCK_FACULTY_COLOR);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty") //send
                        .content(createFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(MOCK_FACULTY_ID))
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR));
    }

    @Test
    public void getTest() throws Exception {
        when(facultyRepo.findById(any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?id=" + MOCK_FACULTY_ID) //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR));

    }

    @Test
    public void deleteTest() throws Exception {
        when(facultyRepo.findById(any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty?id=" + MOCK_FACULTY_ID) //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllTest() throws Exception {
        when(facultyRepo.findAll()).thenReturn(MOCK_FACULTIES);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/all") //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;

                .andExpect(content().json(mapper.writeValueAsString(MOCK_FACULTIES)));

    }

    @Test
    public void updateTest() throws Exception {
        when(facultyRepo.save(any(Faculty.class))).thenReturn(MOCK_FACULTY);

        JSONObject createFaculty = new JSONObject();
        createFaculty.put("id", MOCK_FACULTY_ID);
        createFaculty.put("name", MOCK_FACULTY_NAME);
        createFaculty.put("color", MOCK_FACULTY_COLOR);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/update") //send
                        .content(createFaculty.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(MOCK_FACULTY_ID))
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR));

    }

    @Test
    public void searchNameTest() throws Exception {
        when(facultyRepo.findByNameIgnoreCase(MOCK_FACULTY_NAME)).thenReturn(MOCK_FACULTIES);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/search?name="+MOCK_FACULTY_NAME) //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void searchColorTest() throws Exception {
        when(facultyRepo.findByNameIgnoreCase(MOCK_FACULTY_COLOR)).thenReturn(MOCK_FACULTIES);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/search?name="+MOCK_FACULTY_COLOR) //send
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(MOCK_FACULTIES)));

    }

}
