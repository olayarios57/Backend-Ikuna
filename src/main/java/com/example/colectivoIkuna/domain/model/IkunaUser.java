package com.example.colectivoIkuna.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ikuna_admins")
@Data
public class IkunaUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;
  private String password;
  private String fullName;
  private String role; //SUPER_ADMIN, COLLABORATOR
  private String email;

  private String status; // "PENDING", "ACTIVE", "REJECTED"
  private LocalDate requestDate; // Fecha de solicitud

}