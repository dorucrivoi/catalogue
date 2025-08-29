package com.demo.catalogue.model.catalogue.service;

import com.demo.catalogue.common.ValidationException;

public class CatalogueAlreadyCreatedException extends ValidationException {

    public CatalogueAlreadyCreatedException() {
    }

    public CatalogueAlreadyCreatedException(String message) {
        super(message);
    }

    public CatalogueAlreadyCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
