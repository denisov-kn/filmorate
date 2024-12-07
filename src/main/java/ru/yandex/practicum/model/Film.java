package ru.yandex.practicum.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.yandex.practicum.controller.Marker;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.Duration;
import java.time.LocalDate;


@Data
public class Film {

    private static final LocalDate MIN_FILM_DATE = LocalDate.parse("1895-12-28", DateFormat.DATE_FORMAT.getFormatter());

    @NotNull (message = "ИД не может быть пустым", groups = Marker.Update.class)
    @Positive (message = "ИД должен быть больше 0", groups = Marker.Update.class)
    private Integer id;

    @NotBlank (message = "Имя фильма не может быть пустым", groups = {Marker.Create.class, Marker.Update.class})
    private String name;

    @Size (message = "Описание фильма не может быть больше 200 символов",
            max = 200,
            groups = {Marker.Create.class, Marker.Update.class
    })
    private String description;

    @Past (message = "Дата релиза не может быть в будущем", groups = {Marker.Create.class, Marker.Update.class})
    private LocalDate releaseDate;

    @Positive  (message = "Длительность фильма должно быть больше 0")
    private Integer duration;

    @AssertTrue (message = "Дата фильма не может быть раньше чем 28.12.1895")
    public boolean isValidateReleaseDate() {
        return releaseDate.isAfter(MIN_FILM_DATE);
    }
}
