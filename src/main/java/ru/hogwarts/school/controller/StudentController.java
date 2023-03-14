package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Map;

@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

                                                                        // Created
    @PostMapping
    public ResponseEntity createdStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createdStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

                                                                        // Updete
    @PutMapping()
    public ResponseEntity<Student> updeteStudent(@RequestBody Student student) {
        Student updeteStudent = studentService.updeteStudent(student);
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
    public ResponseEntity getStudent(@PathVariable Long studentId) {
        Student getStudent = studentService.getStudent(studentId);
        if (getStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getStudent);
    }
                                                                            // Filter by age
    @GetMapping
    public ResponseEntity<Object> filterByAgeStudents(@RequestParam Integer studentAge) {
        List<Student> filterByAgeStudents = studentService.filterByAgeStudents(studentAge);
        if (filterByAgeStudents == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(filterByAgeStudents);
    }


}
