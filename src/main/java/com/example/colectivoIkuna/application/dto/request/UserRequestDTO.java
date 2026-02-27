package com.example.colectivoIkuna.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {
  private Long id;

  @NotBlank(message = "El nombre completo es obligatorio")
  @Size(min = 3, max = 80, message = "El nombre debe tener entre 3 y 80 caracteres")
  @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo puede contener letras y espacios")
  private String fullName; // "María González"

  @NotBlank(message = "El email es obligatorio")
  @Email(message = "El formato del correo no es válido")
  private String email;

  @NotBlank(message = "El nombre de usuario es obligatorio")
  @Size(min = 4, max = 20, message = "El usuario debe tener entre 4 y 20 caracteres")
  @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "El usuario solo acepta letras, números, puntos y guiones")
  private String username;
  private String role;
  private String status;
  private String requestDate; // String para React

  @NotBlank(message = "La contraseña es obligatoria")
  @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
  private String password;    // Solo para registro
}
