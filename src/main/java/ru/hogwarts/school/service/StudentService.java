package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final Map<Long, Student> mapStudent = new HashMap<>();

    private Long generatedStudentID = 1L;
                                                    // Created
    public Student createdStudent(Student student) {
        mapStudent.put(generatedStudentID, student);
        generatedStudentID++;
        return student;
    }
                                                    // Get
    public Student getStudent(Long studentId) {
        return mapStudent.get(studentId);
    }


                                                    // Updete
    public Student updeteStudent(Long studentId, Student student) {
        mapStudent.put(studentId, student);
        return student;
    }
                                                     // Delete
    public Student deleteStudent(Long studentId) {
        return mapStudent.remove(studentId);
    }

                                                        // Filter by age
    public List<Student> filterByAgeStudents(Integer ageStudent) throws Exception {
       if (ageStudent <= 0) {
            throw new Exception("Возраст задан неверно");
        }
        return mapStudent.values().stream()
                .filter(longStudentEntry -> longStudentEntry.getAgeStudent() == ageStudent)
                .collect(Collectors.toList());
    }


}
