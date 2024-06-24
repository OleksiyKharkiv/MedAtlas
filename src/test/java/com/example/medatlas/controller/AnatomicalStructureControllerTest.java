package com.example.medatlas.controller;
import com.example.medatlas.controller.AnatomicalStructureController;
import com.example.medatlas.dto.AnatomicalStructureDTO;
import com.example.medatlas.service.AnatomicalStructureService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnatomicalStructureController.class)
@AutoConfigureMockMvc
@DisplayName("Test class for AnatomicalStructureController")
public class AnatomicalStructureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnatomicalStructureService anatomicalStructureService;

    @Test
    @DisplayName("Test getAnatomicalStructureById")
    void getAnatomicalStructureById() throws Exception {
        // Prepare test data
        AnatomicalStructureDTO structure = new AnatomicalStructureDTO();
        structure.setId(UUID.randomUUID());
        structure.setName("Test Structure");

        // Mock service behavior
        when(anatomicalStructureService.getAnatomicalStructureById(structure.getId())).thenReturn(structure);

        // Perform the MVC request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/AnatomicalStructure/" + structure.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(structure.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(structure.getName())))
                .andDo(print());
    }

    @Test
    @DisplayName("Test updateAnatomicalStructure")
    void updateAnatomicalStructure() throws Exception {
        // Prepare test data
        AnatomicalStructureDTO updatedStructure = new AnatomicalStructureDTO();
        updatedStructure.setId(UUID.randomUUID());
        updatedStructure.setName("Updated Structure");

        // Mock service behavior
        when(anatomicalStructureService.updateAnatomicalStructure(updatedStructure.getId(), updatedStructure)).thenReturn(updatedStructure);

        // Perform the MVC request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.put("/api/AnatomicalStructure/" + updatedStructure.getId())
                        .contentType("application/json")
                        .content("{\"id\":\"" + updatedStructure.getId() + "\",\"name\":\"Updated Structure\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(updatedStructure.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(updatedStructure.getName())))
                .andDo(print());
    }

    @Test
    @DisplayName("Test deleteAnatomicalStructure")
    void deleteAnatomicalStructure() throws Exception {
        UUID id = UUID.randomUUID();

        // Perform the MVC request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/AnatomicalStructure/" + id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}

//package com.example.medatlas.controller;
//
//import com.example.medatlas.dto.AnatomicalStructureDTO;
//import com.example.medatlas.service.AnatomicalStructureService;
//import com.example.medatlas.util.DTOCreator;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.UUID;
//
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@WebMvcTest(AnatomicalStructureController.class)
//@DisplayName("Test class for AnatomicalStructureController")
//public class AnatomicalStructureControllerTest {
//    @MockBean
//    private AnatomicalStructureService anatomicalStructureService;
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @Test
//    void getAnatomicalStructureById() throws Exception {
//        AnatomicalStructureDTO structure = DTOCreator.createAnatomicalStructureDTO();
//        UUID id = structure.getId();
//        when(anatomicalStructureService.getAnatomicalStructureById(id)).thenReturn(structure);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/AnatomicalStructure/" + id))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(structure.getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(structure.getName())));
//    }
//
//    @Test
//    void updateAnatomicalStructure() throws Exception {
//        AnatomicalStructureDTO updatedStructure = DTOCreator.createAnatomicalStructureDTO();
//        UUID id = updatedStructure.getId();
//        when(anatomicalStructureService.updateAnatomicalStructure(id, updatedStructure)).thenReturn(updatedStructure);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/AnatomicalStructure/" + id)
//                        .contentType("application/json")
//                        .content(DTOCreator.asJsonString(updatedStructure)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(updatedStructure.getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(updatedStructure.getName())));
//    }
//
//    @Test
//    void deleteAnatomicalStructure() throws Exception {
//        UUID id = UUID.randomUUID();
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/AnatomicalStructure/" + id))
//                .andExpect(status().isOk());
//    }
//}