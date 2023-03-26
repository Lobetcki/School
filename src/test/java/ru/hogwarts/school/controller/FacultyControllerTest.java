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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        facultyRepository.deleteAll();
    }

    @Test
    void whenCreatedFaculty() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nameFaculty", "test_name2");
        jsonObject.put("color", "Red");

        mockMvc.perform(post("/faculty/created")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").isNotEmpty())
                .andExpect(jsonPath("$.facultyId").isNumber())
                .andExpect(jsonPath("$.nameFaculty").value("test_name2"))
                .andExpect(jsonPath("$.color").value("Red"));

        mockMvc.perform(get("/faculty/get/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(2))
                .andExpect(jsonPath("$.nameFaculty").value("test_name2"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void whenFacultyUpdate() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("facultyId", 1);
        jsonObject.put("nameFaculty", "test_name2");
        jsonObject.put("color", "Red");

        this.mockMvc.perform(put("/faculty/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(1))
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
        studentRepository.deleteAll();
        this.mockMvc.perform(delete("/faculty/delete/" + faculty.getFacultyId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

//        mockMvc.perform(get("/faculty/get/1")) //+ faculty.getFacultyId()))
//                .andExpect(status().isNotFound());
    }

    @Test
    void whenGetFacultyById() throws Exception {

        mockMvc.perform(get("/faculty/get/" + faculty.getFacultyId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$.nameFaculty").value(faculty.getNameFaculty()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));

//        studentRepository.deleteAll();
//        facultyRepository.delete(faculty);

//        mockMvc.perform(get("/faculty/get/" + faculty.getFacultyId()))
//                .andExpect(status().is4xxClientError());

    }

    @Test
    void whenFindStudents() throws Exception {

        mockMvc.perform(get("/faculty?color=" + faculty.getColor()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(1))
                .andExpect(jsonPath("$.nameFaculty").value(faculty.getNameFaculty())) // "Dwarf"
                .andExpect(jsonPath("$.color").value(faculty.getColor())); //"Black"

        mockMvc.perform(get("/faculty?nameFaculty=string" + faculty.getNameFaculty()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$.nameFaculty").value(faculty.getNameFaculty()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));

        mockMvc.perform(get("/faculty"))
                .andExpect(status().isNotFound());

    }

    @Test
    void whenFindStudentsByFacultyId() throws Exception {

        mockMvc.perform(get("/faculty/students/" + faculty.getFacultyId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nameStudent").value("test_name"))
                .andExpect(jsonPath("$[0].ageStudent").value(20))
                .andExpect(jsonPath("$[0].facultyID").value(1));
    }

}
