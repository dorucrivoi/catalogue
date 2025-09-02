package com.demo.catalogue.model.catalogue.service;

import com.demo.catalogue.common.ValidationException;

public class CatalogueNotFoundException extends ValidationException {
    public CatalogueNotFoundException() {
    }

    public CatalogueNotFoundException(String message) {
        super(message);
    }

    public CatalogueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
