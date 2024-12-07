package controller;

import exception.NotFoundException;
import jakarta.validation.Valid;
import model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<Integer, User> users = new HashMap<>();

    @Validated(Marker.Create.class)
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        user.setId(getNextId());
        users.put(user.getId(),user);
        return user;
    }

    @Validated(Marker.Update.class)
    @PutMapping
    public User updateUser(@Valid @RequestBody User user) {
        if (!users.containsKey(user.getId())) {
            throw new NotFoundException("Пользователь с таким id - " + user.getId() + " не найден");
        }
        users.replace(user.getId(), user);
        return user;
    }

    public Collection<User> finAll() {
        return new ArrayList<>(users.values());
    }

    private Integer getNextId() {
        Integer currentMaxId = users.keySet()
                .stream()
                .mapToInt(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
}
