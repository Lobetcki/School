package ru.hogwarts.school.model;

import java.util.Objects;

public class Faculty {

    private Long facultyId;
    private String nameFaculty;
    private Integer color;

    public Faculty() {
    }

    public Faculty(Long idFaculty, String nameFaculty, Integer color) {
        this.facultyId = idFaculty;
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

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
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
