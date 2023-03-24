-- liquibase formatted sql

-- changeset anton:3
CREATE TABLE avatar
(
    id           BIGINT PRIMARY KEY,
    file_path    VARCHAR(255),
    file_size    BIGINT,
    media_type   VARCHAR(255),
    data         BYTEA,
    student_id_student BIGINT REFERENCES student(id_student)
);