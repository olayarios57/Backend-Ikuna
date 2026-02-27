package com.example.colectivoIkuna.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

@Entity
@Table(name = "proyecto")
@Data
public class CulturalProject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "project_id")
  private Long id;

  @Column(name = "titulo_proyecto")
  private String title;
  @Column(name = "linea_trabajo_cultural")
  private String category;
  @Column(name = "estado_proyecto")
  private String status;
  @Column(name = "progreso_ejecucion_proyecto")
  private Integer progress;
  @Column(name = "fecha_inicio_proyecto")
  private LocalDate executionDate;
  @Column(name = "monto_inicial")
  private BigDecimal totalBudget;
    @Column(name = "monto_ejecutado")
  private BigDecimal executedBudget;


  @Column(name = "descripcion_proyecto", columnDefinition = "TEXT")
  private String description;
  @Column(name = "imagen_referencia")
  private String coverImageUrl;

  // Relaciones: Un proyecto tiene muchos miembros y tareas
  // CascadeType.ALL significa que si guardas el proyecto, se guardan las tareas automáticamente
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "id_proyecto")
  private List<TeamMember> teamMembers = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "id_proyecto")
  private List<Task> tasks = new ArrayList<>();

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<EventCalendar> eventCalendars = new ArrayList<>();

  @OneToMany(mappedBy = "culturalProject", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProjectCollaboration> collaborator = new ArrayList<>();

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Budget> expenses = new ArrayList<>();
}