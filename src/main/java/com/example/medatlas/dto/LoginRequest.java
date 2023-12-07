package com.example.medatlas.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class LoginRequest {

    private String loginId;
    private String password;
}