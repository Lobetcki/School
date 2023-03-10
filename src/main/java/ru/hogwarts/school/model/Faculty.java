package ru.hogwarts.school.model;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long facultyId;
    private String nameFaculty;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;


//    public Faculty(Long idFaculty,String nameFaculty, String color) {
//        this.facultyId = idFaculty;
//        this.nameFaculty = nameFaculty;
//        this.color = color;
//    }


}
