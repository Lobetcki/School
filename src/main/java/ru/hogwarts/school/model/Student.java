package ru.hogwarts.school.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long idStudent;
    private String nameStudent;
    private Integer ageStudent;

    @ManyToOne //(fetch = FetchType.LAZY)
    //@JoinColumn(name = "faculty_faculty_id")
    private Faculty faculty;


}
