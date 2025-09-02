package com.demo.catalogue.model.student.service;

import com.demo.catalogue.common.ValidationException;

public class StudentCatalogueNotFoundException extends ValidationException {
    public StudentCatalogueNotFoundException() {
    }

    public StudentCatalogueNotFoundException(String message) {
        super(message);
    }

    public StudentCatalogueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
