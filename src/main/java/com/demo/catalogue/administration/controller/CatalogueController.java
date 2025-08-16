package com.demo.catalogue.administration.controller;

import com.demo.catalogue.administration.service.ManageCatalogue;
import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.example.api.CataloguesApi;
import com.example.model.CreateCatalogueRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

//TODO sterge catalogul si crearea se va face din managecatlogue
@RestController
public class CatalogueController implements CataloguesApi {

    private static final Logger logger = LoggerFactory.getLogger(CatalogueController.class);

   private final ManageCatalogue manageCatalogue;

   @Autowired
    public CatalogueController(ManageCatalogue manageCatalogue){
       this.manageCatalogue = manageCatalogue;
   }

   @Override
   public ResponseEntity<Void> createCatalogue(CreateCatalogueRequest request){
       manageCatalogue.createCatalogue(request);
       logger.info("Create catalogue from controller");
       return ResponseEntity.ok().build();
   }
}
