package com.example.proceedingsmanagement.rabbit;

import com.example.proceedingsmanagement.business.rabbit.MessageSender;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class MessageSenderTest {

    @Autowired
    private MessageSender messageSender;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessageSuccess() {
        Mockito.doNothing().when(rabbitTemplate).convertAndSend(any(String.class), any(String.class), any(Object.class),
                any(CorrelationData.class));
        boolean result = messageSender.sendMessage("Test message");
        assertTrue(result);
    }

    @Test
    public void testSendMessageFailure() {
        doThrow(new RuntimeException("Test exception")).when(rabbitTemplate).convertAndSend(any(String.class),
                any(String.class), any(String.class), any(MessagePostProcessor.class));
        boolean result = messageSender.sendMessage("Test message");
        assertFalse(result);
    }
}