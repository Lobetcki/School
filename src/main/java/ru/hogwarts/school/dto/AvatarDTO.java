package ru.hogwarts.school.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

@Data
@NoArgsConstructor
public class AvatarDTO {

    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;
    private Long idStudentA;

   // private final StudentRepository studentRepository;

   //

    public static AvatarDTO fromAvatar(Avatar avatar) {
        AvatarDTO dto = new AvatarDTO();
        dto.setId(avatar.getId());
        dto.setFilePath(avatar.getFilePath());
        dto.setFileSize(avatar.getFileSize());
        dto.setMediaType(avatar.getMediaType());
        dto.setData(avatar.getData());
        dto.setIdStudentA(avatar.getStudent().getIdStudent());
        return dto;
    }
    public Avatar toAvatar() {
        StudentDTO studentDTO = new StudentDTO();
        Avatar avatar = new Avatar();
        avatar.setId(this.getId());
        avatar.setFilePath(this.getFilePath());
        avatar.setFileSize(this.getFileSize());
        avatar.setMediaType(this.getMediaType());
        avatar.setData(this.getData());
        //avatar.setStudent(studentDTO.toStudent().;
       // avatar.setStudent(new StudentDTO().toStudent(this.getIdStudent()).get());
        return avatar;
    }

}
