package com.example.proceedingsmanagement.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue proceedingsInitiationQueue() {
        return new Queue("proceedingsInitiationQueue", true);
    }

    @Bean
    public TopicExchange proceedingsExchange() {
        return new TopicExchange("proceedingsExchange", true, false);
    }

    @Bean
    public Binding proceedingsEmailBinding(Queue proceedingsInitiationQueue, TopicExchange proceedingsExchange) {
        return BindingBuilder.bind(proceedingsInitiationQueue).to(proceedingsExchange).with("proceedings.email.send");
    }
}