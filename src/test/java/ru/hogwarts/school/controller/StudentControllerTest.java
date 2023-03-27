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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
public class StudentControllerTest {

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
    void whenGetAllStudentsAreCalL() throws Exception {

        mockMvc.perform(get("/students/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void whenCreatedStudent() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nameStudent", "test_name2");
        jsonObject.put("ageStudent", 25);
        jsonObject.put("facultyID", 1L);

        MvcResult result = mockMvc.perform(post("/students/created")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").isNotEmpty())
                .andExpect(jsonPath("$.idStudent").isNumber())
                .andExpect(jsonPath("$.nameStudent").value("test_name2"))
                .andExpect(jsonPath("$.ageStudent").value(25))
                .andExpect(jsonPath("$.facultyID").value(1L)).andReturn();

        int id = JsonPath.read(result.getResponse().getContentAsString(), "$.idStudent");

        mockMvc.perform(get("/students/" +  id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").value(id))
                .andExpect(jsonPath("$.nameStudent").value("test_name2"))
                .andExpect(jsonPath("$.ageStudent").value(25))
                .andExpect(jsonPath("$.facultyID").value(1));
    }

    @Test
    void whenStudentUpdate() throws Exception {

        JSONObject jsonObject1 = new JSONObject();

        jsonObject1.put("idStudent", student.getIdStudent());
        jsonObject1.put("nameStudent", "test_name2");
        jsonObject1.put("ageStudent", 25);
        jsonObject1.put("facultyID", 1L);

        this.mockMvc.perform(put("/students/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject1.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").isNotEmpty())
                .andExpect(jsonPath("$.idStudent").isNumber())
                .andExpect(jsonPath("$.nameStudent").value("test_name2"))
                .andExpect(jsonPath("$.ageStudent").value(25))
                .andExpect(jsonPath("$.facultyID").value(1L));

        mockMvc.perform(get("/students/" + student.getIdStudent())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").value(student.getIdStudent()))
                .andExpect(jsonPath("$.nameStudent").value("test_name2"))
                .andExpect(jsonPath("$.ageStudent").value(25))
                .andExpect(jsonPath("$.facultyID").value(1));
    }

    @Test
    void whenStudentDelete() throws Exception {

        this.mockMvc.perform(delete("/students/delete/" + student.getIdStudent())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/students/" + student.getIdStudent())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void whenGetStudentById() throws Exception {

        mockMvc.perform(get("/students/" + student.getIdStudent()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").value(student.getIdStudent()))
                .andExpect(jsonPath("$.nameStudent").value(student.getNameStudent()))
                .andExpect(jsonPath("$.ageStudent").value(student.getAgeStudent()))
                .andExpect(jsonPath("$.facultyID").value(student.getFaculty().getFacultyId()));
    }

    @Test
    void whenFindStudents() throws Exception {
        int id = (int) (student.getIdStudent()-1);
        mockMvc.perform(get("/students?studentAge=20&page=1&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[" + id + "].nameStudent").value("test_name"))
                .andExpect(jsonPath("$[" + id + "].ageStudent").value(20))
                .andExpect(jsonPath("$[" + id + "].facultyID").value(1));

        mockMvc.perform(get("/students?min=2&max=30&page=1&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[" + id + "].nameStudent").value("test_name"))
                .andExpect(jsonPath("$[" + id + "].ageStudent").value(20))
                .andExpect(jsonPath("$[" + id + "].facultyID").value(1));

        mockMvc.perform(get("/students?page=1&size=1" + id + ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[" + id + "].nameStudent").value("test_name"))
                .andExpect(jsonPath("$[" + id + "].ageStudent").value(20))
                .andExpect(jsonPath("$[" + id + "].facultyID").value(1));
    }

    @Test
    void whenGetFacultyByStudentId() throws Exception {
        mockMvc.perform(get("/students/faculty/" + student.getIdStudent()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.facultyId").value(faculty.getFacultyId()))
                .andExpect(jsonPath("$.nameFaculty").value(faculty.getNameFaculty()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));
    }

    @Test
    void whenGetAvgAgeAndCountStudent() throws Exception  {

        mockMvc.perform(get("/students/count-average-students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$")
                        .value("Count students: " + 1 + ", "
                                + " Average students: " + 20));
    }


}


