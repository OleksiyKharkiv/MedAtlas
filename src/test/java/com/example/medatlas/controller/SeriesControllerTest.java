//package com.example.medatlas.controller;
//
//import com.example.medatlas.dto.SeriesDTO;
//import com.example.medatlas.service.SeriesService;
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
//@WebMvcTest(controllers = SeriesController.class)
//@DisplayName("Test class for SeriesController")
//public class SeriesControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SeriesService seriesService;
//
//    @Test
//    @DisplayName("Test createSeries")
//    void createSeries() throws Exception {
//        SeriesDTO newSeries = DTOCreator.getSeriesDTO();
//
//        when(seriesService.createSeries(newSeries)).thenReturn(newSeries);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/Series/")
//                        .contentType("application/json")
//                        .content(DTOCreator.asJsonString(newSeries)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(newSeries.getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.number", is(newSeries.getNumber())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(newSeries.getName())));
//    }
//
//    @Test
//    @DisplayName("Test getSeriesById")
//    void getSeriesById() throws Exception {
//        SeriesDTO series = DTOCreator.getSeriesDTO();
//        UUID id = series.getId();
//
//        when(seriesService.getSeriesById(id)).thenReturn(series);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/Series/" + id))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(series.getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.number", is(series.getNumber())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(series.getName())));
//    }
//
//    @Test
//    @DisplayName("Test updateSeries")
//    void updateSeries() throws Exception {
//        SeriesDTO updatedSeries = DTOCreator.getSeriesDTO();
//        UUID id = updatedSeries.getId();
//
//        when(seriesService.updateSeries(id, updatedSeries)).thenReturn(updatedSeries);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/Series/" + id)
//                        .contentType("application/json")
//                        .content(DTOCreator.asJsonString(updatedSeries)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(updatedSeries.getId().toString())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.number", is(updatedSeries.getNumber())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(updatedSeries.getName())));
//    }
//
//    @Test
//    @DisplayName("Test deleteSeries")
//    void deleteSeries() throws Exception {
//        UUID id = UUID.randomUUID();
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/Series/" + id))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//}