package ru.hogwarts.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;
    private String nameStudent;
    private Integer ageStudent;

    public Student() {
    }

    public Student(String nameStudent, Integer ageStudent) {
       // this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.ageStudent = ageStudent;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long id) {
        this.idStudent = id;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String name) {
        this.nameStudent = name;
    }

    public Integer getAgeStudent() {
        return ageStudent;
    }

    public void setAgeStudent(Integer age) {
        this.ageStudent = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(idStudent, student.idStudent) && Objects.equals(nameStudent, student.nameStudent) && Objects.equals(ageStudent, student.ageStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, nameStudent, ageStudent);
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", nameStudent='" + nameStudent + '\'' +
                ", ageStudent=" + ageStudent +
                '}';
    }
}
