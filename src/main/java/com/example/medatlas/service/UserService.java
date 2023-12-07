package com.example.medatlas.service;

import com.example.medatlas.model.User;
import com.example.medatlas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(int id) {
        Optional<User> userInfo =  repository.findById(id);

        if (userInfo.isPresent()) {
            return userInfo.get();
        }

        throw new RuntimeException("User details not found for id " + id);
    }

    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "user added to system ";
    }
}