package com.demo.catalogue.model.grade.service;

import com.demo.catalogue.common.ValidationException;

public class GradeNotFoundException extends ValidationException {
    public GradeNotFoundException() {
    }

    public GradeNotFoundException(String message) {
        super(message);
    }

    public GradeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
