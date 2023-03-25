package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@WebMvcTest(StudentController.class)
public class StudentControllerTest {


//    @MockBean
//    @Autowired
//    private StudentService studentService;
    @Autowired
    ObjectMapper objectMapper;

//    @MockBean
    @Autowired
    AvatarService avatarService;
    @Autowired
    StudentController studentController;

    @Autowired
    MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//
//    }

//    @Test
//    void whenGetAllStudentsAreCalL() throws Exception {
//
//        mockMvc.perform(get("/all"))
//            .andExpect(status().isOk())
//
////            .andExpect((ResultMatcher) jsonPath("$").isMap())
////            .andExpect(jsonPath("$").isEmpty())
//        ;
//        }
//    }

    @Test
    void givenNoUsersInDatabase_whenUserAdded_thenItExistsInList() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test_name");


        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("test_name"));

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("test_name"));
    }


