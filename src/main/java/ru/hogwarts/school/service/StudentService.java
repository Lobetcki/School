package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Service
public class StudentService {

    private Map<Long, Student> mapStudent = new HashMap<>();

    private Long generatedStudentID = 1L;

    public Student createdStudent(Student student) {
        mapStudent.put(generatedStudentID, student);
        generatedStudentID++;
        return student;
    }

    public Student getStudent(Long studentId) {
        return mapStudent.get(studentId);
    }

    public Student updeteStudent(Long studentId, Student student) {
        mapStudent.put(studentId, student);
        return student;
    }

    public Student deleteStudent(Long studentId) {
        return mapStudent.remove(studentId);
    }




}
