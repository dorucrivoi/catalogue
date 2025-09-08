package com.demo.catalogue.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String QUEUE = "school-class-queue";

    @Bean
    public Queue queueCreate() {
        return QueueBuilder.durable(QUEUE).build();
    }

}

