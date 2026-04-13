package com.example.actions.service;

import com.example.actions.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        user.setId(UUID.randomUUID());
        users.add(user);
    }

    public void deleteUser(UUID id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}
