package com.example.colectivoIkuna.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "ikuna_calendario_eventos")
@Data
public class EventCalendar {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long id;

    @Column(name = "nombre_actividad", nullable = false)
    private String activityName;
    @Column(name = "fecha_hora")
    private LocalDateTime dateTime;
    @Column(name = "avance_porcentaje")
    private Double percentageProgress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto")
    private CulturalProject project;

}


