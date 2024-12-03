package controller;

import model.Film;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")

public class FilmController {

    private Map<Long, Film> films = new HashMap<>();

    @PostMapping
    public Film createFilm (@RequestBody Film film) {
        return film;
    }

    @PutMapping
    public Film updateFilm (@RequestBody Film film) {
        return  film;
    }


    @GetMapping
    public Collection<Film> findAll(){
        return new ArrayList<>(films.values());
    }

    private long getNextId() {
        long currentMaxId = films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
