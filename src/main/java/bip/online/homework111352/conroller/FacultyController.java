package bip.online.homework111352.conroller;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.service.FacultyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;


@RestController
@RequestMapping("/faculty")
@Tag(name = "Контроллер по работе с факультетами", description = "Контроллер выполняет операции с факультетами в университете")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Faculty>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color, @RequestParam(required = false) String name) {
        if (color != null && !color.isBlank()){
            return ResponseEntity.ok(service.findByColor(color));
        }
        if(name != null && !name.isBlank()) {
            return ResponseEntity.ok(service.findByName(name));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @Operation(
            summary = "Добавление факультетов",
            description = "Позволяет добавлять факультет в систему"
    )
    @PostMapping
    public ResponseEntity<Faculty> save(@RequestBody Faculty faculty) {
        if (faculty != null)
            return ResponseEntity.ok(service.save(faculty));
        else
            return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Получение факультета",
            description = "Позволяет получить факультет из системы по его id"
    )
    @GetMapping
    public ResponseEntity<Faculty> get(@RequestParam Long id) {
        return ResponseEntity.ok(service.findById(id).orElseThrow());
    }

    @Operation(
            summary = "Удаление факультета",
            description = "Позволяет удалить факультета из системы по его id"
    )
    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Редактирование факультета",
            description = "Позволяет отредактировать факультета в системе"
    )
    @PutMapping("/update")
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(service.update(faculty));
    }


}
