package bip.online.homework111352.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Класс студента")
public class Student {
    @Schema(description = "Идентификатор",example ="1")
    private Long id;
    @Schema(description = "Название",example ="Просто Гарри")
    private String name;
    @Schema(description = "Возраст",example ="16")
    private int age;
}
