package com.example.colectivoIkuna.application.mapper;

import com.example.colectivoIkuna.application.dto.response.ProjectCollaborationDTO;
import com.example.colectivoIkuna.domain.model.ProjectCollaboration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CollaborationMapper {

    // --- DE ENTIDAD A DTO ---
    @Mapping(source = "collaborator.id", target = "collaboratorId")
    @Mapping(source = "collaborator.name", target = "collaboratorName")
    // SOURCE: Entidad (initialParticipationDate) -> TARGET: DTO (initialParticipationDate)
    @Mapping(source = "initialParticipationDate", target = "initialParticipationDate")
    // SOURCE: Entidad (specificRole) -> TARGET: DTO (specificRole)
    @Mapping(source = "specificRole", target = "specificRole")
    ProjectCollaborationDTO toDTO(ProjectCollaboration entity);

    // --- DE DTO A ENTIDAD ---
    @Mapping(source = "collaboratorId", target = "collaborator.id")
    @Mapping(source = "initialParticipationDate", target = "initialParticipationDate")
    @Mapping(source = "specificRole", target = "specificRole")
    ProjectCollaboration toEntity(ProjectCollaborationDTO dto);
}