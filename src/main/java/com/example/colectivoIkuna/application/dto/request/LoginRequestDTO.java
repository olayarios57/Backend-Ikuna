package com.example.colectivoIkuna.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequestDTO {

  @NotNull(message = "El nombre de usuario es obligatorio")
  private String username;
  @NotBlank(message = "La contraseña es obligatoria")
  private String password;
}