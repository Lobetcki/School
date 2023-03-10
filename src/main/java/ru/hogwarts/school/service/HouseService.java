package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.List;

@Service
public class HouseService {

    private final FacultyRepository facultyRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
                                                       // Created
    public Faculty createdFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
        return faculty;
    }

                                                        // Updete
    public Faculty updeteFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
                                                         // Delete
    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

                                                            // Get
    public Faculty getFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId).orElse(null);
    }

                                                            // Filter by color
    public List<Faculty> filterByColorFaculty(String colory) {
        return facultyRepository.findFacultiesByColorIgnoreCase(colory);
    }
                                                            // Filter by name faculty
    public Faculty findByNameFaculty(String nameFaculty) {
        return facultyRepository.findByNameFacultyContainingIgnoreCase(nameFaculty);
    }
}
