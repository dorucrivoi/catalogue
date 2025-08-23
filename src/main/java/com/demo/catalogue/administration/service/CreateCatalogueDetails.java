package com.demo.catalogue.administration.service;

public class CreateCatalogueDetails {

    private String classCode;
    private int year;

    public CreateCatalogueDetails(String classCode, int year) {
        this.classCode = classCode;
        this.year = year;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
