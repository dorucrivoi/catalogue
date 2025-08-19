package com.demo.catalogue.administration.events;

import com.demo.catalogue.model.catalogue.service.CatalogueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassConsumer {

    private final ObjectMapper objectMapper;
    private final CatalogueService catalogueService;

    @Autowired
    public SchoolClassConsumer(ObjectMapper objectMapper, CatalogueService catalogueService) {
        this.objectMapper = objectMapper;
        this.catalogueService = catalogueService;
    }

    @RabbitListener(queues = "school-class-created-queue")
    public void consume(String rawJson) {

        try {
            // Deserialize JSON string into POJO
            System.out.println("📥 Received event: " + rawJson);
            SchoolClassCreatedConsumer event = objectMapper.readValue(rawJson, SchoolClassCreatedConsumer.class);
            System.out.println("📥 Received event: " + event);
            catalogueService.createCatalogue(event);
        } catch (Exception e) {
            System.err.println("❌ Failed to parse JSON: " + e.getMessage());
        }
    }
}
