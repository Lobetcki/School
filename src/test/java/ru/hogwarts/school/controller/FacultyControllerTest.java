package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FacultyControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    StudentController studentController;
    @Autowired
    MockMvc mockMvc;
    Student student;
    Faculty faculty;

    @BeforeEach
    public void setUp() {
        faculty = new Faculty();
        faculty.setNameFaculty("Dwarf");
        faculty.setColor("Black");
        facultyRepository.save(faculty);

        student = new Student();
        student.setNameStudent("test_name");
        student.setAgeStudent(20);
        student.setFaculty(faculty);
        studentRepository.save(student);
    }

    @AfterEach
    public void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void whenCreatedFaculty() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nameStudent", "test_name2");
        jsonObject.put("ageStudent", 25);
        jsonObject.put("facultyID", 1L);

        mockMvc.perform(post("/faculty/created")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").isNotEmpty())
                .andExpect(jsonPath("$.idStudent").isNumber())
                .andExpect(jsonPath("$.nameStudent").value("test_name2"))
                .andExpect(jsonPath("$.ageStudent").value(25))
                .andExpect(jsonPath("$.facultyID").value(1L));

        mockMvc.perform(get("/students/all" ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[1].nameStudent").value("test_name2"))
                .andExpect(jsonPath("$[1].ageStudent").value(25))
                .andExpect(jsonPath("$[1].facultyID").value(1));
    }
}
