package bip.online.homework111352.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Класс факультета")
public class Faculty {
    @Schema(description = "Идентификатор",example ="1")
    private Long id;
    @Schema(description = "Название факультета",example ="Гриффендор")
    private String name;
    @Schema(description = "Цвет флага",example ="Какой-цвет")
    private String color;
}
