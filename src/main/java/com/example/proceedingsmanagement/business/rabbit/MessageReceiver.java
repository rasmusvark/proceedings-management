package com.example.proceedingsmanagement.business.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String messageData) {
        System.out.println("Received Message: " + messageData);
    }
}