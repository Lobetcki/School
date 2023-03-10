package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.ArrayList;
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
    public List<FacultyDTO> filterByColorFaculty(String colory) {
        List<Faculty> faculties = facultyRepository.findFacultiesByColorIgnoreCase(colory);
        List<FacultyDTO> facultyDTOs = new ArrayList<>();
        for (Faculty f : faculties) {
            FacultyDTO facultyDTO = FacultyDTO.fromFaculty(f);
            facultyDTOs.add(facultyDTO);
        }
        return facultyDTOs;
    }
                                                            // Filter by name faculty
    public FacultyDTO findByNameFaculty(String nameFaculty) {
        return FacultyDTO.fromFaculty(facultyRepository.findByNameFacultyContainingIgnoreCase(nameFaculty));
    }
}
