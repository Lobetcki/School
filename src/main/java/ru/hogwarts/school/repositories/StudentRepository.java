package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

   // @Query("SELECT age FROM Student age WHERE age.ageStudent IS NOT NULL")
    List<Student> findStudentByAgeStudent(Integer age);

//    @Query("SELECT age FROM Student age WHERE age.ageStudent > minAge AND age.ageStudent < maxAge ")
    List<Student> findByAgeStudentBetween(Integer min, Integer max);

}
