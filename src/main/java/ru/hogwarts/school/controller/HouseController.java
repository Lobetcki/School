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
    public ResponseEntity updeteFaculty(@RequestBody Faculty faculty) {
        Faculty updeteFaculty = houseService.updeteFaculty(faculty.getIdFaculty(), faculty);
        return ResponseEntity.ok(updeteFaculty);
    }

                                                                        // Delete
    @DeleteMapping("/{facultyId}")
    public ResponseEntity deleteFaculty(@PathVariable Long facultyId) {
        Faculty deleteFaculty = houseService.deleteFaculty(facultyId);
        if (deleteFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deleteFaculty);
    }

                                                                    // Get
    @GetMapping("/{facultyId}")
    public Faculty getFaculty(@PathVariable Long facultyId) {
        System.out.println(houseService);

        Faculty getFaculty = houseService.getFaculty(facultyId);

        return getFaculty; //ResponseEntity.ok(getFaculty);
    }

                                                                    // Filter by color
    @GetMapping
    public List<Faculty> filterByColorFaculty(@RequestParam String color) {
        return houseService.filterByColorFaculty(color);

    }


}
