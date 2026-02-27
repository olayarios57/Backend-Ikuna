package com.example.colectivoIkuna.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "ikuna_tasks")
@Data
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String assignedTo;
  private LocalDate startDate;
  private LocalDate endDate;
  private String status; // 'pending', 'in-progress', 'completed'
}