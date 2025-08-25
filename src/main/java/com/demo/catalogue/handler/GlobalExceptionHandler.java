package com.demo.catalogue.handler;


import com.demo.catalogue.administration.controller.DisciplineController;
import com.demo.catalogue.model.catalogue.service.CatalogueService;
import com.demo.catalogue.students.controller.GradeController;
import com.demo.catalogue.students.controller.StudentsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.persistence.EntityNotFoundException;
import java.net.ConnectException;
import java.time.LocalDateTime;


@ControllerAdvice(assignableTypes = {
        DisciplineController.class,
        StudentsController.class,
        GradeController.class,
        CatalogueService.class
})
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        logException(ex);
        return buildErrorMessage(HttpStatus.NOT_FOUND, "Entity not found", ex, request);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorMessage> handleDatabaseError(DataAccessException ex, WebRequest request) {
        logException(ex);
        return buildErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", ex, request);
    }

    @ExceptionHandler(AmqpException.class)
    public ResponseEntity<ErrorMessage> handleAmqpError(AmqpException ex, WebRequest request) {
        logException(ex);
        return buildErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, "Message broker error", ex, request);
    }

//    @ExceptionHandler(AmqpConnectException.class)
//    public ResponseEntity<ErrorMessage> handleAmqpConnectError(AmqpConnectException ex, WebRequest request) {
//        log.error("RabbitMQ connection error", ex);
//        return buildErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, "RabbitMQ connection error", ex, request);
//    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorMessage> handleConnectException(ConnectException ex, WebRequest request) {
        logException(ex);
        return buildErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, "Connection refused", ex, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex, WebRequest request) {
        logException(ex);
        return buildErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex, request);
    }

    private ResponseEntity<ErrorMessage> buildErrorMessage(HttpStatus status, String error, Exception ex, WebRequest request) {
        ErrorMessage apiError = new ErrorMessage(
                LocalDateTime.now(),
                status.value(),
                error,
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return ResponseEntity.status(status).body(apiError);
    }

    private  static void logException(Exception e){
        LOGGER.error(e.getMessage());
        LOGGER.debug("Cause:", e);
    }
}
