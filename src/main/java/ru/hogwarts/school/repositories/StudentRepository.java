package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentByAgeStudent(Integer age);

    List<Student> findByAgeStudentBetween(Integer min, Integer max);

                    //- Возможность получить количество всех студентов в школе. Эндпоинт должен вернуть число.
    @Query(value = "SELECT COUNT(id_student) AS count FROM hogwarts.public.student", nativeQuery = true)
    Long getStudentsCountByIdStudent();

                    // - Возможность получить средний возраст студентов. Эндпоинт должен вернуть число.
    @Query(value = "SELECT AVG(age_student) AS avg FROM  hogwarts.public.student", nativeQuery = true)
    Integer getStudentsAverageByAgeStudent();

                    //- Возможность получить пять самых молодых студентов.
    @Query(value = "SELECT * FROM hogwarts.public.student ORDER BY age_student ASC LIMIT 5", nativeQuery = true)
    List<Student> getStudentsYangByAgeStudent();


}
