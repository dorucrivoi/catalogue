package com.demo.catalogue.students.service;

import com.demo.catalogue.common.ValidationException;

public class StudentGradesNotFoundException extends ValidationException {
    public StudentGradesNotFoundException() {
    }

    public StudentGradesNotFoundException(String message) {
        super(message);
    }

    public StudentGradesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
