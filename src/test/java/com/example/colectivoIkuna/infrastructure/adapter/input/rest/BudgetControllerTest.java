package com.example.colectivoIkuna.infrastructure.adapter.input.rest;

import com.example.colectivoIkuna.application.dto.request.BudgetDTO;
import com.example.colectivoIkuna.application.usecases.BudgetUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BudgetControllerTest {

    @Mock
    private BudgetUseCase budgetUseCase;

    @InjectMocks
    private BudgetController controller;

    @Test
    void create_ShouldReturnCreatedBudgetAndStatus201() {
        // Arrange
        BudgetDTO request = new BudgetDTO();
        request.setAmount(new BigDecimal("1500.00"));

        BudgetDTO responseDto = new BudgetDTO();
        responseDto.setBudgetId(1L);
        responseDto.setAmount(new BigDecimal("1500.00"));

        when(budgetUseCase.createBudget(any(BudgetDTO.class))).thenReturn(responseDto);

        // Act
        ResponseEntity<BudgetDTO> response = controller.create(request);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getBudgetId()).isEqualTo(1L);
    }
}