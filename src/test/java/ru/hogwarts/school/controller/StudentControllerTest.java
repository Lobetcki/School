package ru.hogwarts.school.controller;

//import static org.springframework.http.RequestEntity.post;

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
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;
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
    Student student;

    @BeforeEach
    public void setUp() {
        Faculty faculty = new Faculty();
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
    void whenGetAllStudentsAreCalL() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("nameStudent", "test_name");
//        jsonObject.put("ageStudent", 20);
//        jsonObject.put("facultyID", 1);

        mockMvc.perform(get("/students/all"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void givenNoStudentsInDatabase_whenStudentAdded_thenItExistsInList() throws Exception {

        studentRepository.deleteAll();

        mockMvc.perform(get("/students/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nameStudent", "test_name2");
        jsonObject.put("ageStudent", 25);
        jsonObject.put("facultyID", 2L);

        this.mockMvc.perform(post("/students/created")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").isNotEmpty())
                .andExpect(jsonPath("$.idStudent").isNumber())
                .andExpect(jsonPath("$.nameStudent").value("test_name2"))
                .andExpect(jsonPath("$.ageStudent").value(25))
                .andExpect(jsonPath("$.facultyID").value(2L));

        mockMvc.perform(get("/students/all" ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nameStudent").value("test_name2"))
                .andExpect(jsonPath("$[0].ageStudent").value(25))
                .andExpect(jsonPath("$[0].facultyID").value(2));
    }

    @Test
    void givenNoStudentsInDatabase_whenStudentUpdate_thenItExistsInList() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("nameStudent", "test_name");
//        jsonObject.put("ageStudent", 20);
//        jsonObject.put("facultyID", 1L);

//        this.mockMvc.perform(post("/students/created")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonObject.toString()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.idStudent").isNotEmpty())
//                .andExpect(jsonPath("$.idStudent").isNumber())
//                .andExpect(jsonPath("$.nameStudent").value("test_name"))
//                .andExpect(jsonPath("$.ageStudent").value(20))
//                .andExpect(jsonPath("$.facultyID").value(1L));

        JSONObject jsonObject1 = new JSONObject();

        jsonObject1.put("idStudent", 1);
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

        mockMvc.perform(get("/students/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].nameStudent").value("test_name2"))
                .andExpect(jsonPath("$[0].ageStudent").value(25))
                .andExpect(jsonPath("$[0].facultyID").value(1));
    }

    @Test
    void whenStudentDelete() throws Exception {

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("nameStudent", "test_name");
//        jsonObject.put("ageStudent", 20);
//        jsonObject.put("facultyID", 1L);
//
//        this.mockMvc.perform(post("/students/created")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonObject.toString()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.idStudent").isNotEmpty())
//                .andExpect(jsonPath("$.idStudent").isNumber())
//                .andExpect(jsonPath("$.nameStudent").value("test_name"))
//                .andExpect(jsonPath("$.ageStudent").value(20))
//                .andExpect(jsonPath("$.facultyID").value(1L));

        this.mockMvc.perform(delete("/students/delete/1") //+ student.getIdStudent())
                        .contentType(MediaType.APPLICATION_JSON)
                        //.content(jsonObject.toString()))
            ).andExpect(status().isOk());

        mockMvc.perform(get("/students/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void whenGetStudent() throws Exception {

        mockMvc.perform(get("/" + student.getIdStudent()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idStudent").value(student.getIdStudent()))
                .andExpect(jsonPath("$.nameStudent").value(student.getNameStudent()))
                .andExpect(jsonPath("$.ageStudent").value(student.getAgeStudent()))
                .andExpect(jsonPath("$.faculty").value(student.getFaculty()));
    }


}


