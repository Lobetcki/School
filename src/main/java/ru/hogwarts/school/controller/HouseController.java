package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

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
    public ResponseEntity createdFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = houseService.createdFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

                                                                      // Updete
    @PutMapping()
    public ResponseEntity<Faculty> updeteFaculty(@RequestBody Faculty faculty) {
        Faculty updeteFaculty = houseService.updeteFaculty(faculty);
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
    public ResponseEntity getFaculty(@PathVariable Long facultyId) {
        Faculty getFaculty = houseService.getFaculty(facultyId);
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


}
