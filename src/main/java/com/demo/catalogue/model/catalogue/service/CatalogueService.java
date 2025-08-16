package com.demo.catalogue.model.catalogue.service;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.model.catalogue.repository.CatalogueRepository;
import com.demo.catalogue.model.semester.entity.Semester;
import com.demo.catalogue.model.semester.repository.SemesterRepository;
import com.example.model.CreateCatalogueRequest;
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


    public Catalogue createCatalogue(CreateCatalogueRequest request) {
// trebuie adus in managecatalogue si toata logica
        Catalogue catalogue = new Catalogue();
        catalogue.setName("Catalogul " + request.getClassCode());
        catalogue.setCatalogueCode( request.getClassCode()  + "-" + request.getYear());
        catalogue.setClassCode(request.getClassCode());
        catalogue.setYear(request.getYear());
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
