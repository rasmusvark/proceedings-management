package com.example.proceedingsmanagement.business.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String messageData) {
        try {
            logger.info("Received Message: {}", messageData);
        } catch (Exception e) {
            logger.error("Error processing message: {}", messageData, e);
        }
    }
}