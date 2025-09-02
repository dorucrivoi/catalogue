package com.demo.catalogue.model.semester.entity;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import jakarta.persistence.Entity;
import jakarta.persistence.*;


@Entity
@Table(name = "SEMESTER")
public class Semester {

    @Id
    private Long id; // not AUTO_INCREMENT as per your SQL

    private String name;

    @ManyToOne
    @JoinColumn(name = "catalogue_id", nullable = false)
    private Catalogue catalogue;

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
