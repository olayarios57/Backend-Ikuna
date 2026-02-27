package com.example.colectivoIkuna.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class CulturalProjectDTO {
  private Long id;

  @NotBlank(message = "El título del proyecto es obligatorio")
  @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
  private String title;

  @NotBlank(message = "El estado es obligatorio")
  @Pattern(regexp = "^(in-progress|completed|pending)$", message = "El estado debe ser: in-progress, completed o pending")
  private String status;

  @NotBlank(message = "La fecha es obligatoria")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha debe tener el formato AAAA-MM-DD")
  private String date; // String para React

  @NotNull(message = "El progreso es obligatorio")
  @Min(value = 0, message = "El progreso no puede ser menor a 0")
  @Max(value = 100, message = "El progreso no puede ser mayor a 100")
  private Integer progress;

  @NotBlank(message = "La categoria es obligatoria")
  private String category;

  @NotBlank(message = "La descripción es obligatoria")
  @Size(min = 20, max = 1000, message = "La descripción debe tener al menos 20 caacteres")
  private String description;

  @NotBlank(message = "La URL de la imagen es obligatoria")
  @org.hibernate.validator.constraints.URL(message = "Debe ser una URL válida (http/https)")
  private String imageUrl;

  @Valid
  private List<TeamMemberDTO> teamMembers;
  @Valid
  private List<TaskDTO> tasks;
}