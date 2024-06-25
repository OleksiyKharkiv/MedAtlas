//package com.example.medatlas.controller;
//
//import com.example.medatlas.dto.AuthRequest;
//import com.example.medatlas.dto.UserDTO;
//import com.example.medatlas.model.User;
//import com.example.medatlas.service.JwtService;
//import com.example.medatlas.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @GetMapping("/isRunning")
//    @PreAuthorize("permitAll()")
//    public String isRunning() {
//        return "Service is running";
//    }
//
//    @PostMapping("/addUser")
//    @PreAuthorize("permitAll()")
//    public String addUser(@RequestBody UserDTO userDTO) {
//        userService.addUser(userDTO); // Использование UserService с UserDTO
//        return "User added successfully";
//    }
//
//    @GetMapping("/getAllUsers")
//    @PreAuthorize("hasRole('admin')")
//    public List<UserDTO> getAllUsers() {
//        return userService.getAllUsers(); // Использование UserService с UserDTO
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('admin')")
//    public UserDTO getUserById(@PathVariable long id) {
//        return userService.getUserById(id); // Использование UserService с UserDTO
//    }
//
//    @PostMapping("/getToken")
//    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
//
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(authRequest.getUserName());
//        }
//        throw new UsernameNotFoundException("Invalid user details.");
//    }
//}
//
package com.example.medatlas.controller;

import com.example.medatlas.dto.AuthRequest;
import com.example.medatlas.model.User;
import com.example.medatlas.service.JwtService;
import com.example.medatlas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/isRunning")
    @PreAuthorize("permitAll()")
    public String isRunning() {
        return "Service is running";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('admin')")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public User getUserById(@PathVariable int id) {
        return service.getUser(id);
    }

    @PostMapping("/getToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        }
        throw new UsernameNotFoundException("invalid user details.");
    }
}