package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final FacultyRepository facultyRepository;

    @Autowired
    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }
                                                                  // Created
    public FacultyDTO createdFaculty(FacultyDTO facultyDTO) {
        logger.info("Was invoked method for create faculty");
        facultyDTO.setFacultyId(null);
        return FacultyDTO.fromFaculty(facultyRepository.save(facultyDTO.toFaculty()));
    }

                                                                 // Updete
    public FacultyDTO updeteFaculty(FacultyDTO facultyDTO) {
        logger.info("Was invoked method for updete faculty");
        return FacultyDTO.fromFaculty(facultyRepository.save(facultyDTO.toFaculty()));
    }
                                                                // Delete
    public void deleteFaculty(Long facultyId) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(facultyId);
    }

                                                                 // Get
    public FacultyDTO getFacultyDTO(Long facultyId) {
        logger.info("Was invoked method for get faculty");
        return FacultyDTO.fromFaculty(facultyRepository.findById(facultyId).orElse(null));
    }

                                                               // Filter by color
    public List<FacultyDTO> filterByColorFaculty(String colory) {
        logger.info("Was invoked method for get faculty by color");
        return facultyRepository.findFacultiesByColorIgnoreCase(colory)
                .stream().map(FacultyDTO::fromFaculty)
                .collect(Collectors.toList());
    }

                                                            // Filter by name faculty
    public FacultyDTO findByNameFaculty(String nameFaculty) {
        logger.info("Was invoked method for get faculty by name faculty");
        return FacultyDTO.fromFaculty(facultyRepository.findByNameFacultyContainingIgnoreCase(nameFaculty));

    }
                                                            //Faculty's Students by faculty's id
    public List<StudentDTO> findStudentsByFacultyId(@RequestParam Long facultyId) {
        logger.info("Was invoked method for get faculty's students by faculty's id");
//        PageRequest pageRequest = StudentService.pageRequest(pageNumber, pageSize);
//        PageRequest pageRequest = StudentService.pageRequest(1, 5);

        return facultyRepository.findById(facultyId).get()
                .getStudents()
                .stream().map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
    }
}
