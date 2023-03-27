package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.hogwarts.school.config.ConfigContainers;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FacultyControllerTest extends ConfigContainers {

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
        facultyRepository.deleteAll();
    }

    @Test
    void whenCreatedFaculty() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nameFaculty", "test_name2");
        jsonObject.put("color", "Red");

        MvcResult result = mockMvc.perform(post("/faculty/created")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").isNotEmpty())
                .andExpect(jsonPath("$.facultyId").isNumber())
                .andExpect(jsonPath("$.nameFaculty").value("test_name2"))
                .andExpect(jsonPath("$.color").value("Red")).andReturn();

        int id = JsonPath.read(result.getResponse().getContentAsString(), "$.facultyId");

        mockMvc.perform(get("/faculty/get/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(id))
                .andExpect(jsonPath("$.nameFaculty").value("test_name2"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void whenFacultyUpdate() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("facultyId", faculty.getFacultyId());
        jsonObject.put("nameFaculty", "test_name2");
        jsonObject.put("color", "Red");

        this.mockMvc.perform(put("/faculty/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$.nameFaculty").value("test_name2"))
                .andExpect(jsonPath("$.color").value("Red"));

        mockMvc.perform(get("/faculty/get/" + faculty.getFacultyId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$.nameFaculty").value("test_name2"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void whenDeleteFaculty() throws Exception {
        studentRepository.delete(student);
        this.mockMvc.perform(delete("/faculty/delete/" + faculty.getFacultyId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        mockMvc.perform(get("/faculty/get/" + faculty.getFacultyId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGetFacultyById() throws Exception {

        mockMvc.perform(get("/faculty/get/" + faculty.getFacultyId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$.nameFaculty").value(faculty.getNameFaculty()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));

    }

    @Test
    void whenGetFacultyById_IsNotFound() throws Exception {
        studentRepository.delete(student);
        facultyRepository.delete(faculty);

        this.mockMvc.perform(get("/faculty/get/" + faculty.getFacultyId()))
                .andExpect(status().isNotFound());

    }

    @Test
    void whenFindFacultyByColor() throws Exception {
        int id = (int) (faculty.getFacultyId()-1);
        mockMvc.perform(get("/faculty/color?color=Black")) //+ faculty.getColor()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[" + id + "].facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$[" + id + "].nameFaculty").value(faculty.getNameFaculty()))// "Dwarf"
                .andExpect(jsonPath("$[" + id + "].color").value(faculty.getColor())); //"Black"

        mockMvc.perform(get("/faculty/color"))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenFindByNameFaculty() throws Exception {

        mockMvc.perform(get("/faculty/name?nameFaculty=Dwarf")) // + faculty.getNameFaculty()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$.nameFaculty").value(faculty.getNameFaculty()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));

        mockMvc.perform(get("/faculty/name"))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenFindStudentsByFacultyId() throws Exception {
        int id = (int) (student.getIdStudent()-1);
        mockMvc.perform(get("/faculty/students/" + faculty.getFacultyId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[" + id + "].nameStudent").value("test_name"))
                .andExpect(jsonPath("$[" + id + "].ageStudent").value(20))
                .andExpect(jsonPath("$[" + id + "].facultyID").value(1));
    }
}
