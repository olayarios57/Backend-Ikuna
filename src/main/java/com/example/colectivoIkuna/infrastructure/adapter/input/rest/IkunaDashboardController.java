package com.example.colectivoIkuna.infrastructure.adapter.input.rest;

import com.example.colectivoIkuna.application.dto.response.CulturalProjectDTO;
import com.example.colectivoIkuna.application.mapper.CulturalProjectMapper;
import com.example.colectivoIkuna.application.usecases.IkunaManagerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/ikuna")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Agregado para evitar problemas de CORS con React
public class IkunaDashboardController {

  private final IkunaManagerUseCase ikunaUseCase;
  private final CulturalProjectMapper projectMapper;

  @GetMapping("/portfolio")
  public ResponseEntity<List<CulturalProjectDTO>> getPortfolio() {
    var dtos = ikunaUseCase.getPortfolio().stream()
            .map(projectMapper::toDTO)
            .toList();
    return ResponseEntity.ok(dtos);
  }

  @PostMapping ("/projects")
  public ResponseEntity<CulturalProjectDTO> createProject(@RequestBody CulturalProjectDTO dto) {
    var savedProject = ikunaUseCase.launchOrUpdateProject(projectMapper.toEntity(dto));
    return new ResponseEntity<>(projectMapper.toDTO(savedProject), HttpStatus.CREATED);
  }

  @PutMapping("/projects/{id}")
  public ResponseEntity<CulturalProjectDTO> updateProject(@PathVariable Long id, @RequestBody CulturalProjectDTO dto) {

    // ESTE LOG TE DIRÁ SI EL FRONTEND MANDÓ LA TAREA:
    System.out.println("====== LLEGARON DATOS AL BACKEND ======");
    System.out.println("Tareas en el DTO: " + dto.getTasks());
    System.out.println("Miembros en el DTO: " + dto.getTeamMembers());
    System.out.println("=======================================");

    dto.setId(id);
    var updated = ikunaUseCase.launchOrUpdateProject(projectMapper.toEntity(dto));
    return ResponseEntity.ok(projectMapper.toDTO(updated));
  }
}