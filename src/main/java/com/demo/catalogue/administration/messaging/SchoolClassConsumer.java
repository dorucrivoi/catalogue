package com.demo.catalogue.administration.messaging;

import com.demo.catalogue.common.ValidationException;
import com.demo.catalogue.model.catalogue.events.SchoolClassCreatedEvent;
import com.demo.catalogue.model.catalogue.service.CatalogueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolClassConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolClassConsumer.class);

    private final ObjectMapper objectMapper;
    private final CatalogueService catalogueService;

    @Autowired
    public SchoolClassConsumer(ObjectMapper objectMapper, CatalogueService catalogueService) {
        this.objectMapper = objectMapper;
        this.catalogueService = catalogueService;
    }

    @RabbitListener(queues = "school-class-created-queue")
    public void consume(String rawJson) {
//one way
        //request response asyncron plus o coada
        // posibil de creat un nou producer si un consumer
        // un nou tabel in primul serviciu unde monitorizez messagele consumate
        try {
            // Deserialize JSON string into POJO
            System.out.println("📥 Received event: " + rawJson);
            SchoolClassCreatedEvent event = objectMapper.readValue(rawJson, SchoolClassCreatedEvent.class);
            System.out.println("📥 Received event: " + event);
            // de creat un DTO - CatalogueCreateDetails
            //TODO mai trebuie folosit ManageCatalogue sau folosim sau service-urile??
            //TODO de ce se opreste doar cand prind Exception???
            //TODO de configurat logica de retry pentru exceptii la ce exceptie anume
            //Idempotent consumer pattern
            catalogueService.createCatalogue(event);
        } catch (JsonProcessingException e) {
            System.err.println("❌ Failed to parse JSON: " + e.getMessage());
        } catch (ValidationException ex){
            //Ignoring exception because the event maybe duplicated
            LOGGER.error("Catalogue already exists " , ex);
        }
    }
}
