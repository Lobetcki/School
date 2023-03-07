package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HouseService {

    private final Map<Long, Faculty> mapFaculty = new HashMap<>();

    private Long generatedFacultyID = 1L;

    public HouseService() {
    }
                                                       // Created
    public Faculty createdFaculty(Faculty faculty) {
        mapFaculty.put(generatedFacultyID, faculty);
        generatedFacultyID++;
        return faculty;
    }

                                                        // Updete
    public Faculty updeteFaculty(Long facultyId, Faculty faculty) {
        mapFaculty.put(facultyId, faculty);
        return faculty;
    }
                                                         // Delete
    public Faculty deleteFaculty(Long facultyId) {
        return mapFaculty.remove(facultyId);
    }

                                                            // Get
    public Faculty getFaculty(Long facultyId) {
        System.out.println(mapFaculty);
        return mapFaculty.get(facultyId);
    }

                                                            // Filter by age
    public List<Faculty> filterByColorFaculty(String colory) {
        return mapFaculty.values().stream()
                .filter(longStudentEntry -> longStudentEntry.getColor().contains(colory))
                .collect(Collectors.toList());
    }

}
