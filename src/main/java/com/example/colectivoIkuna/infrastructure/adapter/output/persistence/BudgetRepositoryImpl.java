package com.example.colectivoIkuna.infrastructure.adapter.output.persistence;

import com.example.colectivoIkuna.domain.model.Budget;
import com.example.colectivoIkuna.domain.model.BudgetStatus;
import com.example.colectivoIkuna.domain.port.out.BudgetRepository;
import com.example.colectivoIkuna.infrastructure.adapter.repository.BudgetJpaRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BudgetRepositoryImpl implements BudgetRepository {

    private final BudgetJpaRepositoy budgetJpaRepositoy;

    @Override
    public Budget save(Budget budget) {
        return budgetJpaRepositoy.save(budget);
    }

    @Override
    public Optional<Budget> findById(Long id) {
        return budgetJpaRepositoy.findById(Math.toIntExact(id));
    }

    @Override
    public List<Budget> findProjectById(Long projectId) {
        return budgetJpaRepositoy.findByProjectId(projectId);
    }

    @Override
    public List<Budget> findByStatus(BudgetStatus status) {
        return budgetJpaRepositoy.findByStatus(status);
    }

    @Override
    public void deleteById(Long id) {
        budgetJpaRepositoy.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Budget> findByProjectId(Long projectId) {
        return List.of();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public void delete(Budget b) {

    }
}
