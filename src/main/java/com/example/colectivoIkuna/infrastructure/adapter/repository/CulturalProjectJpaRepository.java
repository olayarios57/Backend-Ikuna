package com.example.colectivoIkuna.infrastructure.adapter.repository;

import com.example.colectivoIkuna.domain.model.CulturalProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CulturalProjectJpaRepository extends JpaRepository<CulturalProject, Long> {
}