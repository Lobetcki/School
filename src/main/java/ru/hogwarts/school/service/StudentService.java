package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
}

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }


    public static PageRequest pageRequest(Integer pageNumber, Integer pageSize) {
        if (pageSize == null || pageSize > 50 || pageSize <= 0) {
            pageSize = 50;
        }
        if (pageNumber == null) {
            pageNumber = 1;
        }
        return PageRequest.of(pageNumber - 1, pageSize);
    }
                                                                        // Get all Student
    public List<StudentDTO> getAllStudent(Pageable pageable) {

        logger.info("Was invoked method for get all the students");

        return studentRepository.findAll(pageable)
                .stream().map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }

                                                            // Created
    public StudentDTO createdStudent(StudentDTO studentDTO) {
        logger.info("Was invoked method for create student");

        studentDTO.setIdStudent(null);
        Student student = studentDTO.toStudent();
        student.setFaculty(facultyRepository.findById(studentDTO.getFacultyID()).get());
        return StudentDTO.fromStudent(studentRepository.save(student));
    }

                                                               // Updete
    public StudentDTO updeteStudent(StudentDTO studentDTO) {
        logger.info("Was invoked method for update student");

        Student student = studentDTO.toStudent();
        student.setFaculty(facultyRepository.findById(studentDTO.getFacultyID()).get());
        return StudentDTO.fromStudent(studentRepository.save(student));
    }
                                                                  // Delete
    public void deleteStudent(Long studentId) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(studentId);
    }

                                                                // Get
    public StudentDTO getStudent(Long studentId) {
        logger.info("Was invoked method for get student");
        return StudentDTO.fromStudent(studentRepository.findById(studentId).get());
    }
                                                                // Filter by age
    public List<StudentDTO> filterByAgeStudents(Integer age, Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for get students by age ");
        PageRequest pageRequest = pageRequest(pageNumber, pageSize);

        return studentRepository.findStudentByAgeStudent(age, pageRequest)
                .stream().map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }
                                                         // Filter by age between min and max
    public List<StudentDTO> findByAgeBetween(Integer min, Integer max, Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for get students by age between min and max");
        PageRequest pageRequest = pageRequest(pageNumber, pageSize);

        return studentRepository.findByAgeStudentBetween(min, max, pageRequest)
                .stream().map(StudentDTO::fromStudent)
                .collect(Collectors.toList());

    }
                                                              // student's  Faculty by studentId
    public FacultyDTO getFacultyByStudentId(Long studentId) {
        logger.info("Was invoked method for get student's Faculty by student's Id");
        StudentDTO studentDTO = StudentDTO.fromStudent(studentRepository.findById(studentId).get());
        return FacultyDTO.fromFaculty(facultyRepository.findById(studentDTO.getFacultyID()).orElse(null));
    }

                                               //- Возможность получить количество всех студентов в школе. Эндпоинт должен вернуть число.
    public Long getStudentsCountByIdStudent() {
        logger.info("Was invoked method for get the number of all students");
        return studentRepository.getStudentsCountByIdStudent();
    }

                                                // - Возможность получить средний возраст студентов. Эндпоинт должен вернуть число.
    public Integer getStudentsAverageByAgeStudent() {
        logger.info("Was invoked method for get the average age of students");
        return studentRepository.getStudentsAverageByAgeStudent();
    }

                                                 //- Возможность получить пять самых молодых студентов.
     public List<StudentDTO> getStudentsYangByAgeStudent() {
         logger.info("Was invoked method for get the five youngest students");
        return studentRepository.getStudentsYangByAgeStudent()
                .stream().map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
     }



}



