package ru.hogwarts.school.dto;

import lombok.Data;
import ru.hogwarts.school.model.Student;

@Data
public class StudentDTO {

    private Long idStudent;
    private String nameStudent;
    private Integer ageStudent;
    private FacultyDTO facultyDTO;


    public static StudentDTO fromStudent(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setIdStudent(student.getIdStudent());
        dto.setNameStudent(student.getNameStudent());
        dto.setAgeStudent(student.getAgeStudent());
        dto.setFacultyDTO(FacultyDTO.fromFaculty(student.getFaculty()));
        return dto;
    }

    public Student toStudent() {
        Student student = new Student();
        student.setIdStudent(this.getIdStudent());
        student.setNameStudent(this.getNameStudent());
        student.setAgeStudent(this.getAgeStudent());
        student.setFaculty(this.getFacultyDTO().toFaculty());
        return student;
    }
}
