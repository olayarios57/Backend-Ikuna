package com.example.colectivoIkuna.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ikuna_team_members")
@Data
public class TeamMember {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long userId; //El ID de la tabla ikuna_users
  private String name;
  private String email;
  private String role;
}