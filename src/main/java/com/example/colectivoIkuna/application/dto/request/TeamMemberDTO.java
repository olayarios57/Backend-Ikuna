package com.example.colectivoIkuna.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeamMemberDTO {
  private Long id;
  @NotBlank(message = "El nombre del miembro es obligatorio")
  private String name;

  @NotBlank(message = "El email del miembro es obligatorio")
  @Email(message = "El formato del email del miembro no es válido")
  private String email;

  @NotBlank(message = "El rol del miembro es obligatorio")
  private String role; // Ej: "Líder", "Fotógrafo"
}