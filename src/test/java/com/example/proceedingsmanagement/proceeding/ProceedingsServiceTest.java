package com.example.proceedingsmanagement.proceeding;

import com.example.proceedingsmanagement.business.proceeding.ProceedingsService;
import com.example.proceedingsmanagement.business.rabbit.MessageSender;
import com.example.proceedingsmanagement.domain.proceeding.Proceeding;
import com.example.proceedingsmanagement.domain.proceeding.ProceedingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProceedingsServiceTest {

    @Autowired
    private ProceedingsService proceedingsService;

    @MockBean
    private ProceedingRepository proceedingRepository;

    @MockBean
    private MessageSender messageSender;

    @Test
    public void testGetAllProceedings() throws Exception {
        when(proceedingRepository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(Collections.emptyList(), proceedingsService.getAllProceedings());
    }

    @Test
    public void testCreateProceeding() throws Exception {
        Proceeding proceeding = new Proceeding();
        proceeding.setName("Test Name");
        proceeding.setPersonalId("Test Personal ID");
        proceeding.setEmail("test@example.com");
        proceeding.setReason("Test Reason");
        proceeding.setEmailSent(false);
        when(messageSender.sendMessage(any())).thenReturn(true);
        when(proceedingRepository.save(any())).thenReturn(proceeding);

        assertEquals(proceeding, proceedingsService.createProceeding(proceeding));
    }

    @Test
    public void testDeleteProceeding() throws Exception {
        Mockito.doNothing().when(proceedingRepository).deleteById(anyLong());

        proceedingsService.deleteProceeding(1L);
    }
}