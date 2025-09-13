package com.demo.catalogue.model.discipline.service;

import com.demo.catalogue.common.ValidationException;

public class DisciplineNotFoundException extends ValidationException {
    public DisciplineNotFoundException() {
    }

    public DisciplineNotFoundException(String message) {
        super(message);
    }

    public DisciplineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
