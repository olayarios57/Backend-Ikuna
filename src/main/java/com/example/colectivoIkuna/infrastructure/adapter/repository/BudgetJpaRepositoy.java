package com.example.colectivoIkuna.infrastructure.adapter.repository;

import com.example.colectivoIkuna.domain.model.Budget;
import com.example.colectivoIkuna.domain.model.BudgetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetJpaRepositoy extends JpaRepository<Budget, Integer> {
    List<Budget> findByProjectId(Long projectId);

    List<Budget> findByStatus(BudgetStatus status);
}
