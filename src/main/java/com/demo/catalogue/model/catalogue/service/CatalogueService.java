package com.demo.catalogue.model.catalogue.service;

import com.demo.catalogue.model.catalogue.events.SchoolClassCreatedEvent;
import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.model.catalogue.repository.CatalogueRepository;
import com.demo.catalogue.model.semester.entity.Semester;
import com.demo.catalogue.model.semester.repository.SemesterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CatalogueService  {

    private final CatalogueRepository catalogueRepository;
    private final SemesterRepository semesterRepository;

    @Autowired
    public CatalogueService(CatalogueRepository catalogueRepository, SemesterRepository semesterRepository) {
        this.catalogueRepository = catalogueRepository;
        this.semesterRepository = semesterRepository;
    }
//TODO este corect sa am transactional aici?
    @Transactional
    public Catalogue createCatalogue(SchoolClassCreatedEvent event) {
// trebuie adus in managecatalogue si toata logica
        if( catalogueRepository.existsByClassCodeAndYear(event.getClassCode(), event.getYear()) ){
            throw new CatalogueAlreadyCreatedException("Catalogue have already created");
        }

        Catalogue catalogue = new Catalogue();
        catalogue.setName("Catalogul " + event.getClassCode());
        catalogue.setCatalogueCode( event.getClassCode()  + "-" + event.getYear());
        catalogue.setClassCode(event.getClassCode());
        catalogue.setYear(event.getYear());
        catalogue.setSemesters(createSemesters(catalogue));
//fac validare si arunc exceptie avand deja entitatea catalogue trimisa
        //de creat un handler exception
        return catalogueRepository.save(catalogue);
        //tratearea exceptiilor HandlerException
    }

    private List<Semester> createSemesters(Catalogue catalogue){
        List<Semester> semesters = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            Semester semester = new Semester();
            semester.setId(Math.abs(UUID.randomUUID().getMostSignificantBits())); // unique long
            semester.setName("Semester " + i + " " + catalogue.getCatalogueCode());
            semester.setCatalogue(catalogue);
            semesters.add(semester);
        }
        return semesters;
    }
}
