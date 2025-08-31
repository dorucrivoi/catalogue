package com.demo.catalogue.model.student.service;

import com.demo.catalogue.common.ValidationException;

public class StudentNotFoundException extends ValidationException {
    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
