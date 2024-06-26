//package com.example.medatlas.controller;
//
//import com.example.medatlas.dto.StudyDTO;
//import com.example.medatlas.service.StudyService;
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
//import java.util.List;
//import java.util.UUID;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@WebMvcTest(controllers = StudyController.class)
//@DisplayName("Test class for StudyController")
//public class StudyControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private StudyService studyService;
//
//    @Test
//    @DisplayName("Test createStudy")
//    void createStudy() throws Exception {
//        StudyDTO newStudy = DTOCreator.getStudyDTO();
//
//        when(studyService.createStudy(newStudy)).thenReturn(newStudy);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/Study/")
//                        .contentType("application/json")
//                        .content(DTOCreator.asJsonString(newStudy)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(newStudy.getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(newStudy.getName())));
//    }
//
//    @Test
//    @DisplayName("Test getStudyById")
//    void getStudyById() throws Exception {
//        StudyDTO study = DTOCreator.getStudyDTO();
//        UUID id = study.getId();
//
//        when(studyService.getStudyById(id)).thenReturn(study);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/Study/" + id))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(study.getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(study.getName())));
//    }
//
//    @Test
//    @DisplayName("Test updateStudy")
//    void updateStudy() throws Exception {
//        StudyDTO updatedStudy = DTOCreator.getStudyDTO();
//        UUID id = updatedStudy.getId();
//
//        when(studyService.updateStudy(id, updatedStudy)).thenReturn(updatedStudy);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/Study/" + id)
//                        .contentType("application/json")
//                        .content(DTOCreator.asJsonString(updatedStudy)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(updatedStudy.getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(updatedStudy.getName())));
//    }
//
//    @Test
//    @DisplayName("Test deleteStudy")
//    void deleteStudy() throws Exception {
//        UUID id = UUID.randomUUID();
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/Study/" + id))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("Test studyDtoList")
//    void studyDtoList() throws Exception {
//        List<StudyDTO> studyDTOList = DTOCreator.getStudyDTOList();
//
//        when(studyService.getAllStudies()).thenReturn(studyDTOList);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/Study"))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(studyDTOList.get(0).getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is(studyDTOList.get(0).getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(studyDTOList.get(1).getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is(studyDTOList.get(1).getName())));
//    }
//}