package com.demo.catalogue.administration.mapper;

import com.demo.catalogue.model.catalogue.entity.Catalogue;
import com.example.model.CatalogueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {

    CatalogueMapper INSTANCE = Mappers.getMapper(CatalogueMapper.class);

    CatalogueResponse toCatalogueResponse(Catalogue entity);
}
