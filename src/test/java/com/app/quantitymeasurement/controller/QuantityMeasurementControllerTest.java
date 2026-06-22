package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuantityMeasurementController.class)
public class QuantityMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IQuantityMeasurementService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCompare_ReturnsOk() throws Exception {
        QuantityMeasurementController.CompareRequest request = new QuantityMeasurementController.CompareRequest();
        request.setQ1(new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET));
        request.setQ2(new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES));

        QuantityMeasurementEntity mockEntity = new QuantityMeasurementEntity(request.getQ1(), request.getQ2(), "COMPARE", "true");
        when(service.compare(any(), any())).thenReturn(mockEntity);

        mockMvc.perform(post("/api/measurement/compare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("true"));
    }
}
