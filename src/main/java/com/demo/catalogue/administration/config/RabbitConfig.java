package com.demo.catalogue.administration.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final String QUEUE = "school-class-created-queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }
}
