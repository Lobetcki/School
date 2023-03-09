package ru.hogwarts.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;
    private String nameFaculty;
    private String color;

    public Faculty() {
    }

    public Faculty(String nameFaculty, String color) {
       // this.facultyId = idFaculty;
        this.nameFaculty = nameFaculty;
        this.color = color;
    }

    public Long getIdFaculty() {
        return facultyId;
    }

    public void setIdFaculty(Long idFaculty) {
        this.facultyId = idFaculty;
    }

    public String getNameFaculty() {
        return nameFaculty;
    }

    public void setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Facultu{" +
                "idFaculty=" + facultyId +
                ", nameFaculty='" + nameFaculty + '\'' +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty facultu = (Faculty) o;
        return Objects.equals(facultyId, facultu.facultyId) && Objects.equals(nameFaculty, facultu.nameFaculty) && Objects.equals(color, facultu.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, nameFaculty, color);
    }
}
