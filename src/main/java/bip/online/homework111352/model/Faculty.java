package bip.online.homework111352.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @JsonIgnore
    @OneToMany(mappedBy = "faculty")
    List<Student>students;

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
