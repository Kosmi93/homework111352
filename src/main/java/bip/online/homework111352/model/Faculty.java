package bip.online.homework111352.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Schema(description = "Класс факультета")
public class Faculty {
    @Schema(description = "Идентификатор",example ="1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Название факультета",example ="Гриффендор")
    private String name;
    @Schema(description = "Цвет флага",example ="Какой-цвет")
    private String color;
}
