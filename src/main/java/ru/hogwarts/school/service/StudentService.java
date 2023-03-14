package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final FacultyRepository facultyRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

                                                                // Get all Student
    public List<StudentDTO> getAllStudent(Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        return studentRepository.findAll(pageRequest)
                .stream().map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
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
        return studentRepository.findStudentByAgeStudent(age)
                .stream().map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }
                                                         // Filter by age between min and max
    public List<StudentDTO> findByAgeBetween(Integer min, Integer max) {
        return studentRepository.findByAgeStudentBetween(min, max)
                .stream().map(StudentDTO::fromStudent)
                .collect(Collectors.toList());

    }
                                                              // student's  Faculty by studentId
    public FacultyDTO getFacultyByStudentId(Long studentId) {
        StudentDTO studentDTO = StudentDTO.fromStudent(studentRepository.findById(studentId).get());
        return FacultyDTO.fromFaculty(facultyRepository.findById(studentDTO.getFacultyID()).orElse(null));
    }

                                               //- Возможность получить количество всех студентов в школе. Эндпоинт должен вернуть число.
    public Long getStudentsCountByIdStudent() {
        return studentRepository.getStudentsCountByIdStudent();
    }

                                                // - Возможность получить средний возраст студентов. Эндпоинт должен вернуть число.
    public Integer getStudentsAverageByAgeStudent() {
        return studentRepository.getStudentsAverageByAgeStudent();
    }

                                                 //- Возможность получить пять самых молодых студентов.
     public List<StudentDTO> getStudentsYangByAgeStudent() {
        return studentRepository.getStudentsYangByAgeStudent()
                .stream().map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
     }



}



