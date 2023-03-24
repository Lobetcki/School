-- liquibase formatted sql

-- changeset anton:1
CREATE TABLE faculty
(
    faculty_id BIGINT PRIMARY KEY,
    name_faculty VARCHAR(255) UNIQUE NOT NULL,
    color VARCHAR(255) NOT NULL
);

