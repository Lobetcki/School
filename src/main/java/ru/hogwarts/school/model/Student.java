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
    private Long idStudent;
    private String nameStudent;
    private Integer ageStudent;

    @ManyToOne
    private Faculty faculty;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Student(Long idStudent, String nameStudent, Integer ageStudent, Faculty faculty) {
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.ageStudent = ageStudent;
        this.faculty.setFacultyId(faculty.getFacultyId());
    }
}
