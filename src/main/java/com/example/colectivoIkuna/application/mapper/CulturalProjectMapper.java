package com.example.colectivoIkuna.application.mapper;

import com.example.colectivoIkuna.application.dto.response.CulturalProjectDTO;
import com.example.colectivoIkuna.domain.model.CulturalProject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;

// 'uses' le dice a MapStruct que use los otros mappers para las listas de tareas y miembros
@Mapper(componentModel = "spring", uses = {
        TaskMapper.class,
        TeamMemberMapper.class,
        CalendarMapper.class,
        CollaborationMapper.class
})
public interface CulturalProjectMapper {

  // --- DE ENTIDAD A DTO ---
  @Mapping(source = "executionDate", target = "date", qualifiedByName = "dateToString")
  @Mapping(source = "coverImageUrl", target = "imageUrl")
  // SOURCE: Entidad (eventCalendars) -> TARGET: DTO (calendar)
  @Mapping(source = "eventCalendars", target = "calendar") // Entidad (eventCalendars) -> DTO (calendar)
  @Mapping(source = "collaborator", target = "collaborators") // Entidad (collaborator) -> DTO (collaborators)
  @Mapping(source = "tasks", target = "tasks")
  @Mapping(source = "teamMembers", target = "teamMembers")
  @Mapping(source = "expenses", target = "expenses")
  CulturalProjectDTO toDTO(CulturalProject entity);

  // --- DE DTO A ENTIDAD ---
  @Mapping(source = "date", target = "executionDate", qualifiedByName = "stringToDate")
  @Mapping(source = "imageUrl", target = "coverImageUrl")
  // SOURCE: DTO (calendar) -> TARGET: Entidad (eventCalendars)
  @Mapping(source = "calendar", target = "eventCalendars")
  // SOURCE: DTO (collaborators) -> TARGET: Entidad (collaborator)
  @Mapping(source = "collaborators", target = "collaborator")
  @Mapping(source = "tasks", target = "tasks") // <-- FORZAMOS LA RECOPILACIÓN
  @Mapping(source = "teamMembers", target = "teamMembers")
  @Mapping(source = "expenses", target = "expenses")
  CulturalProject toEntity(CulturalProjectDTO dto);

  @Named("dateToString")
  default String dateToString(LocalDate date) {
    return date != null ? date.toString() : null;
  }

  @Named("stringToDate")
  default LocalDate stringToDate(String date) {
    return (date != null && !date.isBlank()) ? LocalDate.parse(date) : null;
  }
}