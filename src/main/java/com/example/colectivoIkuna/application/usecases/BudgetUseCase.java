package com.example.colectivoIkuna.application.usecases;

import com.example.colectivoIkuna.application.dto.request.BudgetDTO;
import com.example.colectivoIkuna.application.mapper.BudgetMapper;
import com.example.colectivoIkuna.domain.model.Budget;
import com.example.colectivoIkuna.domain.model.CulturalProject;
import com.example.colectivoIkuna.domain.port.out.BudgetRepository;
import com.example.colectivoIkuna.domain.port.out.CulturalProjectRepositoryPort;
import com.example.colectivoIkuna.infrastructure.adapter.output.persistence.CulturalProjectRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetUseCase {

    private final CulturalProjectRepositoryPort culturalProjectRepositoryPort;

    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;

    //Crear presupuesto

    @Transactional
    public BudgetDTO createBudget(BudgetDTO budgetDTO) {

        if (budgetDTO.getAmount() == null || budgetDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto inicial del presupuesto debe ser mayor a cero 0");
        }

        if (budgetDTO.getStartDate() != null && budgetDTO.getEndDate() != null && budgetDTO.getEndDate().isBefore(budgetDTO.getStartDate())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        if (budgetDTO.getProjectId() == null) {
            throw new IllegalArgumentException("Ingrese el ID del proyecto");
        }

        CulturalProject project = culturalProjectRepositoryPort.findById(budgetDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        Budget budget = budgetMapper.toEntity(budgetDTO);

        budget.setProject(project);

        if (budget.getTotalIncome() == null) budget.setTotalIncome(BigDecimal.ZERO);
        if (budget.getTotalExpense() == null) budget.setTotalExpense(BigDecimal.ZERO);

        budget.calculateBalance();

        return budgetMapper.toDto(budgetRepository.save(budget));
    }

    //Buscar por ID
    @Transactional(readOnly = true)
    public BudgetDTO getBudgetById(Long id) {
        return budgetRepository.findById(id).map(budgetMapper::toDto).orElseThrow(() -> new RuntimeException("Presupuesto no encontrado"));
    }


    //Listar
    @Transactional(readOnly = true)
    public java.util.List<BudgetDTO> getAllBudgetsByProject(Long projectId) {
        List<Budget> budgets = budgetRepository.findByProjectId(projectId);

        return budgets.stream().map(budgetMapper::toDto).collect(Collectors.toList());
    }


    //Actualizar

    @Transactional
    public BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO) {
        Budget existingBudget = budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Presupuesto no encontrado" + id));

        existingBudget.setAmount(budgetDTO.getAmount());
        existingBudget.setStartDate(budgetDTO.getStartDate());
        existingBudget.setEndDate(budgetDTO.getEndDate());
        existingBudget.setTotalIncome(budgetDTO.getTotalIncome() != null ? budgetDTO.getTotalIncome() : BigDecimal.ZERO);
        existingBudget.setTotalExpense(budgetDTO.getTotalExpense() != null ? budgetDTO.getTotalExpense() : BigDecimal.ZERO);

        existingBudget.calculateBalance();

        return budgetMapper.toDto(budgetRepository.save(existingBudget));
    }

    //Eliminar

    @Transactional
    public void deleteBudget(Long id) {
        Budget b = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NO EXISTE"));

        System.out.println("ENCONTRADO: " + b.getBudgetId());

        budgetRepository.delete(b);
    }
}


