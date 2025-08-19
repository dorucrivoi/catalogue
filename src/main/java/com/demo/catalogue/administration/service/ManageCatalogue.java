package com.demo.catalogue.administration.service;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.demo.catalogue.model.catalogue.service.CatalogueService;
import com.example.model.CreateCatalogueRequest;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageCatalogue {
    private static final Logger logger = LoggerFactory.getLogger(ManageCatalogue.class);

    private final CatalogueService catalogueService;

    @Autowired
    public ManageCatalogue(CatalogueService catalogueService){
        this.catalogueService = catalogueService;
    }

//    @Transactional
//    public Catalogue createCatalogue(CreateCatalogueRequest request){// DTO convertit din json
//        //un json construit si trimis prin eveniment
//        // de create un Consumer
//        //ExceptionHandler pentru RestController
//        //validarea dupa code +year sa vedem
//       // return catalogueService.createCatalogue(request);
//        return null
//    }
}
