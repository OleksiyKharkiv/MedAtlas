//package com.example.medatlas.controller;
//
//import com.example.medatlas.dto.AnatomicalStructureSubjectDTO;
//import com.example.medatlas.service.AnatomicalStructureSubjectService;
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
//@WebMvcTest(controllers = AnatomicalStructureSubjectController.class)
//@DisplayName("Test class for AnatomicalStructureSubjectController")
//public class AnatomicalStructureSubjectControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AnatomicalStructureSubjectService anatomicalStructureSubjectService;
//
//    @Test
//    @DisplayName("Test getAnatomicalStructureSubjectById")
//    void getAnatomicalStructureSubjectById() throws Exception {
//        AnatomicalStructureSubjectDTO subject = DTOCreator.createAnatomicalStructureSubjectDTO();
//        UUID id = subject.getId();
//
//        when(anatomicalStructureSubjectService.getAnatomicalStructureSubjectById(id)).thenReturn(subject);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/AnatomicalStructureSubject/" + id))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(subject.getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(subject.getName())));
//    }
//
//    @Test
//    @DisplayName("Test updateAnatomicalStructureSubject")
//    void updateAnatomicalStructureSubject() throws Exception {
//        AnatomicalStructureSubjectDTO updatedSubject = DTOCreator.createAnatomicalStructureSubjectDTO();
//        UUID id = updatedSubject.getId();
//
//        when(anatomicalStructureSubjectService.updateAnatomicalStructureSubject(id, updatedSubject)).thenReturn(updatedSubject);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/AnatomicalStructureSubject/" + id)
//                        .contentType("application/json")
//                        .content(DTOCreator.asJsonString(updatedSubject)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(updatedSubject.getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(updatedSubject.getName())));
//    }
//
//    @Test
//    @DisplayName("Test deleteAnatomicalStructureSubject")
//    void deleteAnatomicalStructureSubject() throws Exception {
//        UUID id = UUID.randomUUID();
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/AnatomicalStructureSubject/" + id))
//                .andExpect(status().isNoContent())
//                .andDo(print());
//    }
//}