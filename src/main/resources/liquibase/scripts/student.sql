-- liquibase formatted sql

-- changeset anton:2
CREATE TABLE student
(
    id_student BIGINT PRIMARY KEY,
    name_student VARCHAR(255) NOT NULL,
    age_student INTEGER NOT NULL,
    faculty_faculty_id BIGINT REFERENCES faculty(faculty_id)
);

