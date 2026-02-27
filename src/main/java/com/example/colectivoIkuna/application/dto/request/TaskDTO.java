package com.example.colectivoIkuna.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TaskDTO {
  private Long id;
  @NotBlank(message = "El título de la tarea es obligatorio")
  private String title;

  @NotBlank(message = "Debe haber un responsable asignado")
  private String assignedTo;

  @NotBlank(message = "La fecha de inicio es obligatoria")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha inicio debe ser AAAA-MM-DD")
  private String startDate;

  @NotBlank(message = "La fecha de fin es obligatoria")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha fin debe ser AAAA-MM-DD")
  private String endDate;

  @NotBlank(message = "El estado de la tarea es obligatorio")
  @Pattern(regexp = "^(pending|in-progress|completed)$", message = "El estado debe ser: pending, in-progress o completed")
  private String status;
}