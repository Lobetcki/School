package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

@RequestMapping("students")
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
                                                                        // Get
    @GetMapping("{studentId}")
    public ResponseEntity getStudent(@PathVariable Long studentId) {
        Student getStudent = studentService.getStudent(studentId);
        if (getStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getStudent);
    }
                                                                        // Updete
    @PutMapping()
    public ResponseEntity updeteStudent(@RequestBody Student student) {
        Student updeteStudent = studentService.updeteStudent(student.getIdStudent(), student);
        return ResponseEntity.ok(updeteStudent);
    }

                                                                         // Delete
    @DeleteMapping("{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Long studentId) {
        Student deleteStudent = studentService.deleteStudent(studentId);
        if (deleteStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleteStudent);
    }

                                                                        // Filter by age
    @GetMapping("{studentAge}")
    public ResponseEntity getStudent(@PathVariable Long studentAge) {



    }


}
