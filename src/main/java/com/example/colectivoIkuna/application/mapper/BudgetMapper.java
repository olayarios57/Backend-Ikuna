package com.example.colectivoIkuna.application.mapper;

import com.example.colectivoIkuna.application.dto.request.BudgetDTO;
import com.example.colectivoIkuna.domain.model.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    @Mapping(source = "budgetId", target = "budgetId")
    @Mapping(target = "project", ignore = true)
    Budget toEntity(BudgetDTO dto);

    @Mapping(source = "project.id", target = "projectId")
    BudgetDTO toDto(Budget entity);


}
