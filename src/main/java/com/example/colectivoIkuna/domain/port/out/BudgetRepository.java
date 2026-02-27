package com.example.colectivoIkuna.domain.port.out;

import com.example.colectivoIkuna.domain.model.Budget;
import com.example.colectivoIkuna.domain.model.BudgetStatus;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository {
    Budget save(Budget budget);
    Optional<Budget> findById(Long id);
    List<Budget> findProjectById(Long projectId);
    List<Budget> findByStatus(BudgetStatus status);
    void deleteById(Long id);

    List<Budget> findByProjectId(Long projectId);

    boolean existsById(Long id);

    void delete(Budget b);
}
