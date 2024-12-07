package ru.yandex.practicum.controller;
import jakarta.validation.Valid;
import ru.yandex.practicum.exception.NotFoundException;
import ru.yandex.practicum.model.Film;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/films")
public class FilmController {

    private Map<Integer, Film> films = new HashMap<>();


    @Validated(Marker.Create.class)
    @PostMapping
    public Film createFilm(@RequestBody @Valid  Film film) {
        film.setId(getNextId());
        films.put(film.getId(), film);
        return film;
    }

    @Validated(Marker.Update.class)
    @PutMapping
    public Film updateFilm(@RequestBody @Valid Film film) {
        if (!films.containsKey(film.getId()))
            throw new NotFoundException("Фильм с таким id: " + film.getId() + " не найден");
        films.replace(film.getId(), film);
        return  film;
    }


    @GetMapping
    public Collection<Film> findAll() {
        return new ArrayList<>(films.values());
    }



    private Integer getNextId() {
        Integer currentMaxId = films.keySet()
                .stream()
                .mapToInt(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
