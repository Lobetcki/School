package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

@RequestMapping("faculty")
@RestController
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }
                                                                     // Created
    @PostMapping
    public ResponseEntity createdFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = houseService.createdFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }
                                                                      // Get
    @GetMapping("{facultyId}")
    public ResponseEntity getFaculty(@PathVariable Long facultyId) {
        Faculty getFaculty = houseService.getFaculty(facultyId);
        if (getFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getFaculty);
    }
                                                                      // Updete
    @PutMapping()
    public ResponseEntity updeteFaculty(@RequestBody Faculty faculty) {
        Faculty updeteFaculty = houseService.updeteFaculty(faculty.getIdFaculty(), faculty);
        return ResponseEntity.ok(updeteFaculty);
    }

                                                                        // Delete
    @DeleteMapping("{facultyId}")
    public ResponseEntity deleteFaculty(@PathVariable Long facultyId) {
        Faculty deleteFaculty = houseService.deleteFaculty(facultyId);
        if (deleteFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleteFaculty);
    }
}
