package com.example.medatlas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    @Column(name = "user_name", unique = true)
    private String userName;
    @Column(name = "e_mail", unique = true)
    private String eMail;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
}