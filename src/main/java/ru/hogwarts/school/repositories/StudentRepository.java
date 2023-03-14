package ru.hogwarts.school.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

                                                                        // Filter by age
    List<Student> findStudentByAgeStudent(Integer age, PageRequest pageRequest);

                                                                        // Filter by age between min and max
    List<Student> findByAgeStudentBetween(Integer min, Integer max, PageRequest pageRequest);

                                                                    //- Возможность получить количество всех студентов в школе. Эндпоинт должен вернуть число.
    @Query(value = "SELECT COUNT(id_student) AS count FROM student s", nativeQuery = true)
    Long getStudentsCountByIdStudent();

                                                                    // - Возможность получить средний возраст студентов. Эндпоинт должен вернуть число.
    @Query(value = "SELECT AVG(age_student) AS avg FROM  student s", nativeQuery = true)
    Integer getStudentsAverageByAgeStudent();

                                                                    //- Возможность получить пять самых молодых студентов.
    @Query(value = "SELECT * FROM student ORDER BY age_student ASC LIMIT 5", nativeQuery = true)
    List<Student> getStudentsYangByAgeStudent();

    Collection<Student> findAll(Pageable pageable);
}
