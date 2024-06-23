package com.example.medatlas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestMedAtlasApplication {

    public static void main(String[] args) {
        SpringApplication.from(MedAtlasApplication::main).with(TestMedAtlasApplication.class).run(args);
    }
}