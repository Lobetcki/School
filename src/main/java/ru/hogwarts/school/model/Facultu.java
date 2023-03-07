package ru.hogwarts.school.model;

import java.util.Objects;

public class Facultu {

    private Long idFaculty;
    private String nameFaculty;
    private Integer color;

    public Facultu(Long idFaculty, String nameFaculty, Integer color) {
        this.idFaculty = idFaculty;
        this.nameFaculty = nameFaculty;
        this.color = color;
    }

    public Long getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(Long idFaculty) {
        this.idFaculty = idFaculty;
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
                "idFaculty=" + idFaculty +
                ", nameFaculty='" + nameFaculty + '\'' +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facultu facultu = (Facultu) o;
        return Objects.equals(idFaculty, facultu.idFaculty) && Objects.equals(nameFaculty, facultu.nameFaculty) && Objects.equals(color, facultu.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFaculty, nameFaculty, color);
    }
}
