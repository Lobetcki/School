package ru.hogwarts.school.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Student createdStudent(Student student) {

        studentRepository.save(student);
        return student;
    }

                                                                                            // Updete
    public Student updeteStudent(Student student) {
        return studentRepository.save(student);
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


}



