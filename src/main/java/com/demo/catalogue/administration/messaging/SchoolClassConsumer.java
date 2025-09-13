package com.demo.catalogue.administration.messaging;

import com.demo.catalogue.common.ValidationException;
import com.demo.catalogue.model.catalogue.events.SchoolClassCreatedEvent;
import com.demo.catalogue.model.catalogue.events.SchoolClassDeletedEvent;
import com.demo.catalogue.model.catalogue.service.CatalogueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SchoolClassConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SchoolClassConsumer.class);

    private final ObjectMapper objectMapper;
    private final CatalogueService catalogueService;

    @Autowired
    public SchoolClassConsumer(ObjectMapper objectMapper, CatalogueService catalogueService) {
        this.objectMapper = objectMapper;
        this.catalogueService = catalogueService;
    }

    @RabbitListener(queues = "school-class-queue")
    public void consumeSchoolClassEvent(Message message) {
        try {
            // Deserialize JSON string into POJO
            String type = (String) message.getMessageProperties().getHeaders().get("type");
            switch (type) {
                case "SchoolClassCreatedEvent" -> {
                    SchoolClassCreatedEvent event =
                            objectMapper.readValue(message.getBody(), SchoolClassCreatedEvent.class);
                    catalogueService.createCatalogue(event);
                    logger.info("Received event for creation: {}", event);
                }
                case "SchoolClassDeletedEvent" -> {
                    SchoolClassDeletedEvent event =
                            objectMapper.readValue(message.getBody(), SchoolClassDeletedEvent.class);
                    catalogueService.deleteCatalogue(event);
                    logger.info("Received event for deletion: {}", event);
                }
                default -> logger.warn("Received unknown event type: {}", type);
            }
        } catch (JsonProcessingException e) {
            logger.error(" Failed to parse JSON: {}", e.getMessage());
        } catch (ValidationException ex){
            //Ignoring exception because the event maybe duplicated
            logger.error("Catalogue already exists " , ex);
        } catch (IOException ex) {
            logger.error("Unexpected exception " , ex);
        }
    }
}
