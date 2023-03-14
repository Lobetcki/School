package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
                                                                  // Created
    public FacultyDTO createdFaculty(FacultyDTO facultyDTO) {
        facultyDTO.setFacultyId(null);
        return FacultyDTO.fromFaculty(facultyRepository.save(facultyDTO.toFaculty()));
    }

                                                                 // Updete
    public FacultyDTO updeteFaculty(FacultyDTO facultyDTO) {
        return FacultyDTO.fromFaculty(facultyRepository.save(facultyDTO.toFaculty()));
    }
                                                                // Delete
    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

                                                                 // Get
    public FacultyDTO getFacultyDTO(Long facultyId) {
        return FacultyDTO.fromFaculty(facultyRepository.findById(facultyId).orElse(null));
    }

                                                               // Filter by color
    public List<FacultyDTO> filterByColorFaculty(String colory) {
        return facultyRepository.findFacultiesByColorIgnoreCase(colory)
                .stream().map(FacultyDTO::fromFaculty)
                .collect(Collectors.toList());
    }

                                                            // Filter by name faculty
    public FacultyDTO findByNameFaculty(String nameFaculty) {
        return FacultyDTO.fromFaculty(facultyRepository.findByNameFacultyContainingIgnoreCase(nameFaculty));

    }
                                                            //Faculty's Students by faculty's id
    public List<StudentDTO> findStudentsByFacultyId(@RequestParam Long facultyId) {

        //PageRequest pageRequest = StudentService.pageRequest(pageNumber, pageSize);

        return facultyRepository.findById(facultyId).get()
                .getStudents()
                .stream().map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }
}
