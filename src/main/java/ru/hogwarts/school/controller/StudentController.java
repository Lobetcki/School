package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.util.Collection;

@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

                                                                        // Created
    @PostMapping
    public ResponseEntity<StudentDTO> createdStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createdStudent(studentDTO);
        return ResponseEntity.ok(createdStudent);
    }

                                                                        // Updete
    @PutMapping()
    public ResponseEntity<StudentDTO> updeteStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO updeteStudent = studentService.updeteStudent(studentDTO);
        return ResponseEntity.ok(updeteStudent);
    }

                                                                         // Delete
    @DeleteMapping("/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

                                                                       // Get
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long studentId) {

        StudentDTO getStudent = studentService.getStudent(studentId);
        if (getStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getStudent);
    }

                                                                            // Filter by age between min and max
    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> findStudents(@RequestParam(required = false) Integer studentAge,
                                                              @RequestParam(required = false) Integer min,
                                                              @RequestParam(required = false) Integer max) {
        if (studentAge != null && (studentAge >= 0)) {
            return ResponseEntity.ok(studentService.filterByAgeStudents(studentAge));
        }

        if ((min != null && (min >= 0) ) && (max != null && (max >= 0))) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        return ResponseEntity.notFound().build();
    }
                                                                               // Student's Faculty by student's id
    @GetMapping("/faculty/{studentId}")
    public ResponseEntity<FacultyDTO> getFacultyByStudentId(@PathVariable Long studentId) {
        FacultyDTO getFaculty = studentService.getFacultyByStudentId(studentId);
        if (getFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getFaculty);
    }

}
