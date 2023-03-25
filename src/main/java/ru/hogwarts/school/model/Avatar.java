package ru.hogwarts.school.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    @Type(type ="org.hibernate.type.BinaryType")
    private byte[] data;

    @OneToOne
   // @JoinColumn(name = "id_student")
    private Student student;



}
