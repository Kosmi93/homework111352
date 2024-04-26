package bip.online.homework111352.service;

import bip.online.homework111352.model.Faculty;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService implements CRUDRepository<Faculty, Long> {

    private final Map<Long, Faculty> faculties;
    private Long count;

    public FacultyService() {
        this.faculties = new HashMap<>();
        count = 0L;
    }

    @Override
    public Faculty save(Faculty data) {
        if (data != null) {
            data.setId(count);
            faculties.put(count, data);
            count++;
        } else
            throw new RuntimeException("В системе нельзя хранить null");
        return data;
    }

    @Override
    public void delete(Long id) {
        faculties.remove(id);
    }

    @Override
    public Faculty update(Faculty data) {
        if (data != null) {
            faculties.replace(data.getId(), data);
        } else
            throw new RuntimeException("В системе нельзя хранить null");
        return data;
    }

    @Override
    public Faculty findById(Long id) {
        return faculties.get(id);
    }
}
