package com.example.colectivoIkuna.application.mapper;

import com.example.colectivoIkuna.application.dto.response.EventCalendarDTO;
import com.example.colectivoIkuna.domain.model.EventCalendar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface CalendarMapper {

    // --- DE ENTIDAD A DTO ---
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "dateTimeToString")
    // Si los nombres son iguales (activityName -> activityName), no hace falta @Mapping,
    // pero los dejamos para asegurar.
    @Mapping(source = "activityName", target = "activityName")
    @Mapping(source = "percentageProgress", target = "percentageProgress")
    EventCalendarDTO toDTO(EventCalendar entity);

    // --- DE DTO A ENTIDAD ---
    @Mapping(source = "dateTime", target = "dateTime", qualifiedByName = "stringToDateTime")
    @Mapping(source = "activityName", target = "activityName")
    @Mapping(source = "percentageProgress", target = "percentageProgress")
    EventCalendar toEntity(EventCalendarDTO dto);

    @Named("dateTimeToString")
    default String dateTimeToString(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toString() : null;
    }

    @Named("stringToDateTime")
    default LocalDateTime stringToDateTime(String dateTime) {
        return (dateTime != null && !dateTime.isBlank()) ? LocalDateTime.parse(dateTime) : null;
    }
}