package com.demo.catalogue.common;


import com.demo.catalogue.administration.controller.AdminController;
import com.demo.catalogue.model.catalogue.service.CatalogueService;
import com.demo.catalogue.professors.controller.GradesController;
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
        AdminController.class,
        StudentsController.class,
        GradesController.class,
        CatalogueService.class
})
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        logException(ex);
        return buildErrorMessage(HttpStatus.NOT_FOUND, "Entity not found", ex);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorMessage> handleDatabaseError(DataAccessException ex, WebRequest request) {
        logException(ex);
        return buildErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", ex);
    }

    @ExceptionHandler(AmqpException.class)
    public ResponseEntity<ErrorMessage> handleAmqpError(AmqpException ex, WebRequest request) {
        logException(ex);
        return buildErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, "Message broker error", ex);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorMessage> handleConnectException(ConnectException ex, WebRequest request) {
        logException(ex);
        return buildErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, "Connection refused", ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex, WebRequest request) {
        logException(ex);
        return buildErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex);
    }

    private ResponseEntity<ErrorMessage> buildErrorMessage(HttpStatus status, String error, Exception ex) {
        ErrorMessage apiError = new ErrorMessage(
                LocalDateTime.now(),
                error,
                ex.getMessage());
        return ResponseEntity.status(status).body(apiError);
    }

    private  static void logException(Exception e){
        LOGGER.error("Unexpected exception", e);
    }
}
