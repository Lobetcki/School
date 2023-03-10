package ru.hogwarts.school.dto;

import lombok.Data;
import ru.hogwarts.school.model.Faculty;

@Data
public class FacultyDTO {

    private Long facultyId;
    private String nameFaculty;
    private String color;



    public static FacultyDTO fromFaculty(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        dto.setFacultyId(faculty.getFacultyId());
        dto.setNameFaculty(faculty.getNameFaculty());
        dto.setColor(faculty.getColor());
        return dto;
    }

    public Faculty toFaculty() {
        Faculty faculty = new Faculty();
        faculty.setFacultyId(this.getFacultyId());
        faculty.setNameFaculty(this.getNameFaculty());
        faculty.setColor(this.getColor());
        return faculty;
    }

}
