package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("${student.avatar.dir.path}")
    private String avatarDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

//    public AvatarService(String avatarDir, StudentService studentService, AvatarRepository avatarRepository) {
//        this.avatarDir = avatarDir;
//        this.studentService = studentService;
//        this.avatarRepository = avatarRepository;
//    }

    @Autowired
    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(Long idStudent, MultipartFile avatarFile) throws IOException {
        Student student = studentService.getStudent(idStudent).toStudent();

        Path filePath = Path.of(avatarDir, idStudent + "." + (avatarFile.getOriginalFilename()
                .substring(avatarFile.getOriginalFilename().lastIndexOf(".") + 1)));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = avatarFile.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW); //  CREATE_NEW
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ){
            bis.transferTo(bos);
        }

        Avatar avatar = avatarRepository.findByStudentIdStudent(idStudent).orElse(new Avatar());
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(generateImagePreview(filePath));

        avatarRepository.save(avatar);

    }

    private byte[] generateImagePreview(Path filePath) throws IOException{
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage prewiew = new BufferedImage(100, height, image.getType());
            Graphics2D graphics2D = prewiew.createGraphics();
            graphics2D.drawImage(image, 0, 0, 100, height, null);
            graphics2D.dispose();

            ImageIO.write(prewiew, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }


    private String getExtension(String fileName) {
    return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

                                                                // GET
    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findByStudentIdStudent(studentId).orElseThrow();
    }
}
