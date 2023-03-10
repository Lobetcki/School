package ru.hogwarts.school.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;


import java.util.ArrayList;
import java.util.List;

@Service
//@NoArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final FacultyRepository facultyRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

                                                            // Created
    public StudentDTO createdStudent(StudentDTO studentDTO) {
        studentDTO.setIdStudent(null);
        Student student = studentDTO.toStudent();
        student.setFaculty(facultyRepository.findById(studentDTO.getFacultyID()).get());
        return StudentDTO.fromStudent(studentRepository.save(student));
    }

                                                               // Updete
    public StudentDTO updeteStudent(StudentDTO studentDTO) {
        Student student = studentDTO.toStudent();
        student.setFaculty(facultyRepository.findById(studentDTO.getFacultyID()).get());
        return StudentDTO.fromStudent(studentRepository.save(student));
    }
                                                                  // Delete
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
                                                                // Get
    public StudentDTO getStudent(Long studentId) {
        return StudentDTO.fromStudent(studentRepository.findById(studentId).get());
    }
                                                                // Filter by age
    public List<StudentDTO> filterByAgeStudents(Integer age) {
        List<Student> students = studentRepository.findStudentByAgeStudent(age);
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student s : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(s);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }
                                                         // Filter by age between min and max
    public List<StudentDTO> findByAgeBetween(Integer min, Integer max) {
        List<Student> students = studentRepository.findByAgeStudentBetween(min, max);
        List<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student s : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(s);
            studentDTOs.add(studentDTO);
        }
        return studentDTOs;
    }
                                                              // Faculty's student  by id
    public FacultyDTO getFacultyByStudentId(Long studentId) {
        StudentDTO studentDTO = StudentDTO.fromStudent(studentRepository.findById(studentId).get());
        return FacultyDTO.fromFaculty(facultyRepository.findById(studentDTO.getFacultyID()).orElse(null));
    }

}



