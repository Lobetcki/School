-- liquibase formatted sql

-- changeset anton:3
CREATE TABLE avatar
(
    id         BIGSERIAL PRIMARY KEY,
    data       BYTEA,
    file_path  VARCHAR(255),
    file_size  BIGINT,
    media_type VARCHAR(255),
    student_id_student bigint,
    foreign key (student_id_student) references student(id_student)
)