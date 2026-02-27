package com.example.colectivoIkuna.application.dto.response;

import lombok.Data;

@Data
public class UserResponseDTO {

  private Long id;
  private String fullName;
  private String email;
  private String username;
  private String role;   // "COLLABORATOR"
  private String status; // "PENDING"
  private String requestDate;

}
