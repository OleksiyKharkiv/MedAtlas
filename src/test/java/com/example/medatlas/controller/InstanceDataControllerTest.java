//package com.example.medatlas.controller;
//
//import com.example.medatlas.dto.InstanceDataDTO;
//import com.example.medatlas.service.InstanceDataService;
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
//@WebMvcTest(controllers = InstanceDataController.class)
//@DisplayName("Test class for InstanceDataController")
//public class InstanceDataControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private InstanceDataService instanceDataService;
//
//    @Test
//    @DisplayName("Test createInstanceData")
//    void createInstanceData() throws Exception {
//        InstanceDataDTO newInstanceData = DTOCreator.createInstanceDataDTO();
//
//        when(instanceDataService.createInstanceData(newInstanceData)).thenReturn(newInstanceData);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/InstanceData/")
//                        .contentType("application/json")
//                        .content(DTOCreator.asJsonString(newInstanceData)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(newInstanceData.getId().toString())));
//    }
//
//    @Test
//    @DisplayName("Test getInstanceDataById")
//    void getInstanceDataById() throws Exception {
//        InstanceDataDTO instanceData = DTOCreator.createInstanceDataDTO();
//        UUID id = instanceData.getId();
//
//        when(instanceDataService.getInstanceDataById(id)).thenReturn(instanceData);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/InstanceData/" + id))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(instanceData.getId().toString())));
//    }
//
//    @Test
//    @DisplayName("Test updateInstanceData")
//    void updateInstanceData() throws Exception {
//        InstanceDataDTO updatedInstanceData = DTOCreator.createInstanceDataDTO();
//        UUID id = updatedInstanceData.getId();
//
//        when(instanceDataService.updateInstanceData(id, updatedInstanceData)).thenReturn(updatedInstanceData);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/InstanceData/" + id)
//                        .contentType("application/json")
//                        .content(DTOCreator.asJsonString(updatedInstanceData)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(updatedInstanceData.getId().toString())));
//    }
//
//    @Test
//    @DisplayName("Test deleteInstanceData")
//    void deleteInstanceData() throws Exception {
//        UUID id = UUID.randomUUID();
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/InstanceData/" + id))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("Test instanceDataDtoList")
//    void instanceDataDtoList() throws Exception {
//        List<InstanceDataDTO> instanceDataDTOList = DTOCreator.createInstanceDataDTOList(2);
//
//        when(instanceDataService.getAllInstanceData()).thenReturn(instanceDataDTOList);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/InstanceData"))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(instanceDataDTOList.get(0).getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(instanceDataDTOList.get(1).getId().toString())));
//    }
//}