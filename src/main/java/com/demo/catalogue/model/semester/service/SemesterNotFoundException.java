package com.demo.catalogue.model.semester.service;

import com.demo.catalogue.common.ValidationException;

public class SemesterNotFoundException extends ValidationException {
    public SemesterNotFoundException() {
    }

    public SemesterNotFoundException(String message) {
        super(message);
    }

    public SemesterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
