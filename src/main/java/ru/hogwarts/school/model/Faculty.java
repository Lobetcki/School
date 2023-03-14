package ru.hogwarts.school.model;

import jdk.jfr.DataAmount;
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
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;
    private String nameFaculty;
    private String color;

    public Faculty(Long idFaculty,String nameFaculty, String color) {
        this.facultyId = idFaculty;
        this.nameFaculty = nameFaculty;
        this.color = color;
    }


}
