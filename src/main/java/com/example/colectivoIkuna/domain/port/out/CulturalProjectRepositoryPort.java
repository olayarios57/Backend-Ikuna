package com.example.colectivoIkuna.domain.port.out;

import com.example.colectivoIkuna.domain.model.CulturalProject;
import java.util.List;
import java.util.Optional;

public interface CulturalProjectRepositoryPort {
  List<CulturalProject> findAll();
  CulturalProject save(CulturalProject project);
  Optional<CulturalProject> findById(Long id);
}