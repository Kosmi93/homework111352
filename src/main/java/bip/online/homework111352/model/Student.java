package bip.online.homework111352.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Schema(description = "Класс студента")
public class Student {
    @Schema(description = "Идентификатор", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Название", example = "Просто Гарри")
    private String name;
    @Schema(description = "Возраст", example = "16")
    private int age;
    @Schema(description = "Факультет на котором обучается студент", example = "1")
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    Faculty faculty;
}
