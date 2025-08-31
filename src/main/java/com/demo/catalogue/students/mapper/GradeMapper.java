package com.demo.catalogue.students.mapper;


import com.demo.catalogue.model.grade.entity.Grade;
import com.example.model.GradesStudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    // Map a single Grade entity to GradesStudentResponse DTO
    @Mapping(source = "semester.name", target = "semesterName")
    @Mapping(source = "discipline.code", target = "disciplineCode")
    GradesStudentResponse toGradesStudentResponse(Grade grade);

    // Map a list of Grade entities to a list of DTOs
    List<GradesStudentResponse> toGradesStudentResponseList(List<Grade> grades);
}