package bip.online.homework111352.service;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.repo.FacultyRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepo repo;

    public FacultyService(FacultyRepo repo) {
        this.repo = repo;
    }

    public Faculty save(Faculty faculty) {
        return repo.save(faculty);
    }

    public Optional<Faculty> findById(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Faculty update(Faculty faculty) {
        return repo.save(faculty);
    }

    public Collection<Faculty> findByColor(String color) {
        return repo.findByColor(color);
    }
}
