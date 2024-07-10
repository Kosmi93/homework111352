package bip.online.homework111352.service;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.repo.FacultyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepo repo;
    private Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepo repo) {
        this.repo = repo;
    }

    public Faculty save(Faculty faculty) {
        logger.info("Сохранение факультета");
        return repo.save(faculty);
    }

    public Optional<Faculty> findById(Long id) {
        logger.info("Поиск факультета по id");
        return repo.findById(id);
    }

    public void delete(Long id) {
        logger.info("Удаление факультета по id");
        repo.deleteById(id);
    }

    public Faculty update(Faculty faculty) {
        logger.info("Обновление информации о факультете");
        return repo.save(faculty);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Поиск факультета по цвету");
        return repo.findByColorIgnoreCase(color);
    }

    public Collection<Faculty> findByName(String name) {
        logger.info("Поиск факультета по имени");
        return repo.findByNameIgnoreCase(name);
    }

    public  Collection<Faculty> findAll(){
        return repo.findAll();
    }
}
