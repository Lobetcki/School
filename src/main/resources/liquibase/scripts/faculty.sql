-- liquibase formatted sql

-- changeset anton:1
CREATE TABLE faculty
(
    faculty_id BIGSERIAL PRIMARY KEY,
    name_faculty VARCHAR(255) NOT NULL ,
    color VARCHAR(255) NOT NULL
);

-- -- changeset anton:1.1
-- ALTER  TABLE  hogwarts.public.faculty
--     ADD  CONSTRAINT  constr_faculty  UNIQUE (name_faculty);