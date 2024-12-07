package ru.yandex.practicum.model;

import ru.yandex.practicum.controller.Marker;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    @NotNull (message = "ИД не может быть пустым", groups = Marker.Update.class)
    @Positive (message = "ИД должен быть больше 0", groups = Marker.Update.class)
    private Integer id;

    @NotBlank (message = "Email не может быть пустым", groups = {Marker.Update.class, Marker.Create.class})
    @Email (message = "Email не соответствует формату", groups = {Marker.Update.class, Marker.Create.class})
    private String email;

    @NotBlank (message = "Логин не может быть пустым", groups = {Marker.Update.class, Marker.Create.class})
    private String login;

    private String name;

    @Past (message = "День рождения не может быть в будущем", groups = {Marker.Update.class, Marker.Create.class})
    private LocalDate birthday;

    @AssertTrue(message = "Логин не может содержать пробелы")
    public boolean isValidateLogin() {
        return !login.contains(".*\\s.*");
    }
}