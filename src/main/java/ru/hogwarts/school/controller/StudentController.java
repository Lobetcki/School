package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import org.springframework.data.domain.Pageable;


import java.util.Collection;
import java.util.List;

@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    @Autowired
    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

                                                                        // Get All Students
    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAllStudent(@PageableDefault(size = 50) Pageable pageable) {
        return ResponseEntity.ok(studentService.getAllStudent(pageable));
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
                                                              @RequestParam(required = false) Integer max,
                                                               @RequestParam("page") Integer pageNumber,
                                                               @RequestParam("size") Integer pageSize ) {

        if (studentAge == null && max == null && min == null) {
            return ResponseEntity.ok(studentService.getStudentsYangByAgeStudent());
        }
        if (studentAge != null && (studentAge >= 0)) {
            return ResponseEntity.ok(studentService.filterByAgeStudents(studentAge, pageNumber, pageSize));
        }
        if ((min != null && (min >= 0) ) && (max != null && (max >= 0))) {
            return ResponseEntity.ok(studentService.findByAgeBetween( min, max, pageNumber, pageSize));
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
                                                                            // Count students, Average students, 5 yang students
    @GetMapping("/count-average-students")
    public ResponseEntity<String> getAvgAgeAndCountStudent () {
        return ResponseEntity.ok("Count students: " + studentService.getStudentsCountByIdStudent() + ", "
                + " Average students: " + studentService.getStudentsAverageByAgeStudent());
    }
}
