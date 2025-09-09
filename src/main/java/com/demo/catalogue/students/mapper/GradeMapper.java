package com.demo.catalogue.students.mapper;


import com.demo.catalogue.model.grade.entity.Grade;
import com.example.model.GradesStudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GradeMapper {


    @Mapping(source = "semester.name", target = "semesterName")
    @Mapping(source = "discipline.code", target = "disciplineCode")
    GradesStudentResponse toGradesStudentResponse(Grade grade);

    List<GradesStudentResponse> toGradesStudentResponseList(List<Grade> grades);
}