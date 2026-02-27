package com.example.colectivoIkuna.application.usecases;

import com.example.colectivoIkuna.application.dto.request.BudgetDTO;
import com.example.colectivoIkuna.application.mapper.BudgetMapper;
import com.example.colectivoIkuna.domain.model.Budget;
import com.example.colectivoIkuna.domain.model.CulturalProject;
import com.example.colectivoIkuna.domain.port.out.BudgetRepository;
import com.example.colectivoIkuna.domain.port.out.CulturalProjectRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BudgetUseCaseTest {

    @Mock
    private CulturalProjectRepositoryPort culturalProjectRepo;

    @Mock
    private BudgetRepository budgetRepository;

    @Mock
    private BudgetMapper budgetMapper;

    @InjectMocks
    private BudgetUseCase budgetUseCase;

    @Test
    void createBudget_ShouldThrowException_WhenAmountIsZeroOrNull() {
        BudgetDTO requestDTO = new BudgetDTO();
        requestDTO.setAmount(BigDecimal.ZERO);

        assertThatThrownBy(() -> budgetUseCase.createBudget(requestDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El monto inicial del presupuesto debe ser mayor a cero 0");
    }

    @Test
    void createBudget_ShouldThrowException_WhenEndDateIsBeforeStartDate() {
        BudgetDTO requestDTO = new BudgetDTO();
        requestDTO.setAmount(new BigDecimal("100.00"));
        requestDTO.setStartDate(LocalDate.of(2026, 1, 10));
        requestDTO.setEndDate(LocalDate.of(2026, 1, 5)); // End date before start

        assertThatThrownBy(() -> budgetUseCase.createBudget(requestDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La fecha de fin no puede ser anterior a la fecha de inicio");
    }

    @Test
    void createBudget_ShouldSaveAndReturnDto_WhenValid() {
        BudgetDTO requestDTO = new BudgetDTO();
        requestDTO.setAmount(new BigDecimal("500.00"));
        requestDTO.setProjectId(1L);

        CulturalProject mockProject = new CulturalProject();
        Budget mockBudget = new Budget();
        BudgetDTO responseDTO = new BudgetDTO();
        responseDTO.setBudgetId(1L);

        when(culturalProjectRepo.findById(1L)).thenReturn(Optional.of(mockProject));
        when(budgetMapper.toEntity(requestDTO)).thenReturn(mockBudget);
        when(budgetRepository.save(mockBudget)).thenReturn(mockBudget);
        when(budgetMapper.toDto(mockBudget)).thenReturn(responseDTO);

        BudgetDTO result = budgetUseCase.createBudget(requestDTO);

        assertThat(result.getBudgetId()).isEqualTo(1L);
        verify(budgetRepository).save(mockBudget);
    }

    @Test
    void deleteBudget_ShouldCallRepositoryDelete() {
        Budget mockBudget = new Budget();
        mockBudget.setBudgetId(10L);

        when(budgetRepository.findById(10L)).thenReturn(Optional.of(mockBudget));

        budgetUseCase.deleteBudget(10L);

        verify(budgetRepository).delete(mockBudget);
    }
}