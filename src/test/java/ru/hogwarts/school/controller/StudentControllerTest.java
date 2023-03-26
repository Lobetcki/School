package ru.hogwarts.school.controller;

//import static org.springframework.http.RequestEntity.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import ru.hogwarts.school.service.AvatarService;
//import ru.hogwarts.school.dto.StudentDTO;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    StudentController studentController;
    @Autowired
    MockMvc mockMvc;

    //    @MockBean
//    @Autowired
//    AvatarService avatarService;

//    @Autowired
//    StudentDTO studentDTO;

//    @Autowired
//    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        Faculty faculty = new Faculty();
        faculty.setNameFaculty("Dwarf");
        faculty.setColor("Black");
        facultyRepository.save(faculty);
    }

//    @Test
//    void whenGetAllStudentsAreCalL() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("nameStudent", "test_name");
//        jsonObject.put("ageStudent", 20);
//        jsonObject.put("facultyID", 1);
//
//        mockMvc.perform(get("/students/all"))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$").isMap())
//            .andExpect(jsonPath("$").isEmpty());
//    }

    @Test
    void givenNoStudentsInDatabase_whenUserAdded_thenItExistsInList() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nameStudent", "test_name");
        jsonObject.put("ageStudent", 20);
        jsonObject.put("facultyID", 1L);

        this.mockMvc.perform(post("/students/created")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").isNotEmpty())
                .andExpect(jsonPath("$.idStudent").isNumber())
                .andExpect(jsonPath("$.nameStudent").value("test_name"));
//                .andExpect(jsonPath("$.ageStudent").value(20))
//                .andExpect(jsonPath("$.facultyID").value(1L));

        mockMvc.perform(get("/students/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("test_name"));
//                .andExpect(jsonPath("$[0].ageStudent").value(20))
//                .andExpect(jsonPath("$[0].facultyID").value(1));
    }
}


