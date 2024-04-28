package com.example.proceedingsmanagement.proceeding;

import com.example.proceedingsmanagement.business.proceeding.ProceedingsService;
import com.example.proceedingsmanagement.domain.proceeding.Proceeding;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProceedingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProceedingsService proceedingsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllProceedings() throws Exception {
        when(proceedingsService.getAllProceedings()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/proceedings")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateProceeding() throws Exception {
        Proceeding proceeding = new Proceeding();
        proceeding.setName("Test Name");
        proceeding.setPersonalId("Test Personal ID");
        proceeding.setEmail("test@example.com");
        proceeding.setReason("Test Reason");
        proceeding.setEmailSent(false);
        when(proceedingsService.createProceeding(any(Proceeding.class))).thenReturn(proceeding);

        mockMvc.perform(post("/api/proceedings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proceeding)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteProceeding() throws Exception {
        mockMvc.perform(delete("/api/proceedings/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        doThrow(new RuntimeException()).when(proceedingsService).deleteProceeding(anyLong());
        mockMvc.perform(delete("/api/proceedings/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
    }
}