package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

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
                                                          // Get
    public Faculty getFaculty(Long facultyId) {
       return mapFaculty.get(facultyId);
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


}
