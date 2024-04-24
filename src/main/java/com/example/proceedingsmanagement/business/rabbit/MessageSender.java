package com.example.proceedingsmanagement.business.rabbit;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    public MessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String messageData) {
        rabbitTemplate.convertAndSend(exchange, routingKey, messageData, message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        });
        System.out.println("Message Sent: " + messageData);
    }
}