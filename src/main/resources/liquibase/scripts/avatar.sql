-- liquibase formatted sql

-- changeset anton:3
CREATE TABLE avatar
(
    id           BIGINT generated by default as identity,
    file_path    VARCHAR(255),
    file_size    bigint,
    media_type   VARCHAR(255),
    data         oid,
    student_id_student bigint,
    primary key (id),
    foreign key (student_id_student) references hogwarts.public.student (id_student)
);