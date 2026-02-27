package com.example.colectivoIkuna.application.usecases;

import com.example.colectivoIkuna.domain.model.CulturalProject;
import com.example.colectivoIkuna.domain.port.out.CulturalProjectRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
public class IkunaManagerUseCase {

  private final CulturalProjectRepositoryPort projectRepo;

  public List<CulturalProject> getPortfolio() {
    return projectRepo.findAll();
  }

  @Transactional
  public CulturalProject launchOrUpdateProject(CulturalProject project) {

    // 1. Asegurar relaciones bidireccionales
    if (project.getEventCalendars() != null) {
      project.getEventCalendars().forEach(evento -> {
        evento.setProject(project);
        if (evento.getId() != null && evento.getId() > 1000000000000L) {
          evento.setId(null);
        }
      });
    }

    if (project.getCollaborator() != null) {
      project.getCollaborator().forEach(colab -> {
        colab.setCulturalProject(project);
        if (colab.getId() != null && colab.getId() > 1000000000000L) {
          colab.setId(null);
        }
      });
    }

    // 2. Limpiar IDs temporales de React para Tareas
    if (project.getTasks() != null) {
      project.getTasks().forEach(task -> {
        // Si el ID es gigante (Date.now() de React), lo ponemos en null para que MySQL haga INSERT
        if (task.getId() != null && task.getId() > 1000000000000L) {
          task.setId(null);
        }
      });
    }

    // 3. Limpiar IDs temporales de React para Miembros de Equipo
    if (project.getTeamMembers() != null) {
      project.getTeamMembers().forEach(member -> {
        if (member.getId() != null && member.getId() > 1000000000000L) {
          member.setId(null);
        }
      });
    }

    // 4. Lógica de cálculo de progreso según presupuesto
    if (project.getTotalBudget() != null && project.getTotalBudget().compareTo(BigDecimal.ZERO) > 0) {
      BigDecimal executed = (project.getExecutedBudget() != null) ? project.getExecutedBudget() : BigDecimal.ZERO;
      BigDecimal percentage = executed.multiply(new BigDecimal("100"))
              .divide(project.getTotalBudget(), 2, RoundingMode.HALF_UP);
      project.setProgress(Math.min(100, percentage.intValue()));
    } else if (project.getProgress() == null) {
      project.setProgress(0);
    }

    // 5. Guardar el proyecto con todas sus listas
    return projectRepo.save(project);
  }
}