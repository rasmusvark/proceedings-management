package com.example.proceedingsmanagement.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    @Bean
    public Queue proceedingsInitiationQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public TopicExchange proceedingsExchange() {
        return new TopicExchange(exchangeName, true, false);
    }

    @Bean
    public Binding proceedingsEmailBinding(Queue proceedingsInitiationQueue, TopicExchange proceedingsExchange) {
        return BindingBuilder.bind(proceedingsInitiationQueue).to(proceedingsExchange).with(routingKey);
    }
}