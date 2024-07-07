package bip.online.homework111352.conroller;

import bip.online.homework111352.model.Faculty;
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
public class FacultyWebMvcTest {
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
        String name = "Red";
        String color = "Red";
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        faculty.setId(id);

        when(facultyRepo.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepo.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty") //send
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void getTest() throws Exception {
        Long id = 555L;
        String name = "Red";
        String color = "Red";
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        faculty.setId(id);

        when(facultyRepo.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?id="+id) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void updateTest() throws Exception {
        Long id = 555L;
        String name = "Red";
        String color = "Red";
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        faculty.setId(id);

        when(facultyRepo.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepo.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty") //send
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//receive;
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void searchTest() throws Exception {
        Long id = 555L;
        String name = "Red";
        String color = "Red";
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        faculty.setId(id);

        when(facultyRepo.findByNameIgnoreCase(any(String.class))).thenReturn(List.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/search?name="+name) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());//receive;

        when(facultyRepo.findByColorIgnoreCase(any(String.class))).thenReturn(List.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/search?color="+color) //send
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());//receive;

    }
}
