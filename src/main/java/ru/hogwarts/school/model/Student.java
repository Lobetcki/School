package ru.hogwarts.school.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;
    private String nameStudent;
    private Integer ageStudent;

    public Student(Long idStudent, String nameStudent, Integer ageStudent) {
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.ageStudent = ageStudent;
    }

}
