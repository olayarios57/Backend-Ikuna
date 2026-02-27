package com.example.colectivoIkuna.application.dto.request;

import com.example.colectivoIkuna.domain.model.BudgetStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BudgetDTO {

    private Long budgetId;

    @NotNull(message = "Debe existir un monto asignado")
    @PositiveOrZero(message = "El monto no puede ser negativo")
    private BigDecimal amount;

    @Pattern(regexp = "^(0|[1-9]\\d*)(\\.\\d{1,2})?$",
            message = "totalIncome debe ser un número válido con máximo 2 decimales")
    private BigDecimal totalIncome;

   @PositiveOrZero
    private BigDecimal totalExpense;

    private BigDecimal balance;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate startDate;

    private LocalDate endDate;
    private BudgetStatus status;

    @NotNull(message = "El ID del proyecto es obligatorio")
    @JsonProperty("projectId")
    private Long projectId;

    @NotBlank(message = "El concepto es obligatorio")
    private String concept;

    @NotBlank(message = "El destinatario (Pagado a) es obligatorio")
    private String paidTo;

    private String notes;
}
