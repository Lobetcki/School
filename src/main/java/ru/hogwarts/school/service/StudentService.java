package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

                                                        // Created
    public Student createdStudent(Student student) {
//        student.setIdStudent(null);
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
    public Student getStudent(Long studentId) {
        return studentRepository.getById(studentId);
    }
                                                        // Filter by age
    public List<Student> filterByAgeStudents(Integer ageStudent) {
       return studentRepository.findAll().stream()
                .filter(longStudentEntry -> longStudentEntry.getAgeStudent() == ageStudent)
                .collect(Collectors.toList());
    }


}
