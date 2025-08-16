package com.demo.catalogue.model.catalogue.entity;

import com.demo.catalogue.model.semester.entity.Semester;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CATALOGUE")
public class Catalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="CATALOGUE_CODE",unique = true)
    private String catalogueCode;

    @Column(name="CLASS_CODE")
    private String classCode;

    @Column(name="CATALOGUE_YEAR")
    private Integer year;

    @OneToMany(mappedBy = "catalogue", cascade = CascadeType.ALL)
    private List<Semester> semesters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatalogueCode() {
        return catalogueCode;
    }

    public void setCatalogueCode(String catalogueCode) {
        this.catalogueCode = catalogueCode;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }
}
