package com.example.colectivoIkuna.infrastructure.adapter.output.persistence;

import com.example.colectivoIkuna.domain.model.CulturalProject;
import com.example.colectivoIkuna.domain.port.out.CulturalProjectRepositoryPort;
import com.example.colectivoIkuna.infrastructure.adapter.repository.CulturalProjectJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CulturalProjectRepositoryAdapter implements CulturalProjectRepositoryPort {

  private final CulturalProjectJpaRepository jpaRepository;

  @Override
  public List<CulturalProject> findAll() {
    return jpaRepository.findAll();
  }

  @Override
  public CulturalProject save(CulturalProject project) {
    return jpaRepository.save(project);
  }

  @Override
  public Optional<CulturalProject> findById(Long id) {
    return jpaRepository.findById(id);
  }
}