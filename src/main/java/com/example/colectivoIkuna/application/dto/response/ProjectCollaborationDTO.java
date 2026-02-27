package com.example.colectivoIkuna.application.dto.response;

import lombok.Data;

@Data
public class ProjectCollaborationDTO {
    private long id;
    private String specificRole;
    private String initialParticipationDate;
    private Long collaboratorId;
    private String collaboratorName;
}
