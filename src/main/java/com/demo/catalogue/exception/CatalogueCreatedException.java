package com.demo.catalogue.exception;

public class CatalogueCreatedException extends RuntimeException{

    public CatalogueCreatedException() {
    }

    public CatalogueCreatedException(String message) {
        super(message);
    }

    public CatalogueCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
