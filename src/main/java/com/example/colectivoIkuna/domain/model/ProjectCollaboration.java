package com.example.colectivoIkuna.domain.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ikuna_colaboracion_proyectos")
@Data
public class ProjectCollaboration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colaboracion")
    private Long id;

    @Column(name = "rol_especifico")
    private String specificRole; // Ej: "Muralista,"Tallerista", "Organizador de eventos", etc.
        @Column(name = "fechaInicio_participacion")
    private LocalDate initialParticipationDate;

    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private CulturalProject culturalProject;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private TeamMember collaborator;
}
