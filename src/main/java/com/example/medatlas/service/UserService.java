package com.example.medatlas.service;

import com.example.medatlas.dto.UserDTO;
import com.example.medatlas.mapper.UserMapper;
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
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper; // Использование UserMapper для преобразования между User и UserDTO

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTOList(users); // Преобразование списка User в список UserDTO
    }

    public UserDTO getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user); // Преобразование User в UserDTO
    }

    public void addUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO); // Преобразование UserDTO в User
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
//
//package com.example.medatlas.service;
//
//import com.example.medatlas.model.User;
//import com.example.medatlas.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository repository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public List<User> getAllUsers() {
//        return repository.findAll();
//    }
//
//    public User getUser(long id) {
//        Optional<User> userInfo =  repository.findById(id);
//
//        if (userInfo.isPresent()) {
//            return userInfo.get();
//        }
//
//        throw new RuntimeException("User details not found for id " + id);
//    }
//
//    public String addUser(User user) {
//        System.out.println("Received user: " + user.toString());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        repository.save(user);
//        System.out.println("User saved: " + user);
//        return "user added to system ";
//    }
//}