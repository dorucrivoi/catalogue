package com.demo.catalogue.model.semester.entity;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.model.grade.entity.Grade;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Semester {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "catalogue_id", nullable = false)
    private Catalogue catalogue;

//    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
//    private List<Grade> grades;


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

    public Catalogue getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
    }
}
