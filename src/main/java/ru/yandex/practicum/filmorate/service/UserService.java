package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User createUser(User user) {
        user.setId(nextId++); // Generate and set a unique ID
        users.put(user.getId(), user); // Add the user to the HashMap
        return user;
    }

    public User updateUser(User user) throws FilmNotFoundException {
        if (!users.containsKey(user.getId())) {
            throw new FilmNotFoundException("Пользователь с ID " + user.getId() + " не найден");
        }
        users.put(user.getId(), user);
        return user;
    }

    public User getUserById(int id) throws FilmNotFoundException {
        if (!users.containsKey(id)) {
            throw new FilmNotFoundException("Пользователь с ID " + id + " не найден");
        }
        return users.get(id);
    }

    private int generateId() {
        return nextId++;
    }
}