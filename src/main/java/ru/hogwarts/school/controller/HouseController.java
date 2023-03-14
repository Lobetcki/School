package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.service.HouseService;

import java.util.Collection;
import java.util.List;

@RequestMapping("/faculty")
@RestController
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

                                                                     // Created
    @PostMapping
    public ResponseEntity createdFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO createdFaculty = houseService.createdFaculty(facultyDTO);
        return ResponseEntity.ok(createdFaculty);
    }

                                                                      // Updete
    @PutMapping()
    public ResponseEntity<FacultyDTO> updeteFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO updeteFaculty = houseService.updeteFaculty(facultyDTO);
        return ResponseEntity.ok(updeteFaculty);
    }

                                                                        // Delete
    @DeleteMapping("/{facultyId}")
    public ResponseEntity deleteFaculty(@PathVariable Long facultyId) {
        houseService.deleteFaculty(facultyId);
        return ResponseEntity.ok().build();
    }

                                                                    // Get
    @GetMapping("/{facultyId}")
    public ResponseEntity<FacultyDTO> getFaculty(@PathVariable Long facultyId) {
        FacultyDTO getFaculty = houseService.getFacultyDTO(facultyId);
        if (getFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getFaculty);
    }

                                                                    // Find  Faculty
    @GetMapping
    public ResponseEntity<Object> findFaculty(@RequestParam(required = false) String color,
                                              @RequestParam(required = false) String nameFaculty) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(houseService.filterByColorFaculty(color));
        }

        if (nameFaculty != null && !nameFaculty.isBlank()) {
            return ResponseEntity.ok(houseService.findByNameFaculty(nameFaculty));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping ("/students/{facultyId}")                               //Faculty's Students by faculty's id
    public ResponseEntity<List<StudentDTO>> findStudentsByFacultyId(@PathVariable Long facultyId) {
        List<StudentDTO> students = houseService.findStudentsByFacultyId(facultyId);
        if (students == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }
}
