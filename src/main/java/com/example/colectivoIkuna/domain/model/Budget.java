package com.example.colectivoIkuna.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "budgets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long budgetId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private CulturalProject project;

    @Column(name = "total_income", nullable = false)
    private BigDecimal totalIncome;

    @Column(name = "total_expense", nullable = false)
    private BigDecimal totalExpense;

    private BigDecimal balance;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BudgetStatus status;

    @Column(nullable = false)
    private String concept;

    @Column(name = "paid_to", nullable = false)
    private String paidTo;

    @Column(columnDefinition = "TEXT")
    private String notes;

    //Actualización del balance automáticamente
    @PrePersist
    @PreUpdate
    public void calculateBalance() {
        if (this.totalIncome == null) this.totalIncome = BigDecimal.ZERO;
        if (this.totalExpense == null) this.totalExpense = BigDecimal.ZERO;
        if (this.amount == null) this.amount = BigDecimal.ZERO;

        this.balance = this.amount
                .add(this.totalIncome)
                .subtract(this.totalExpense);

    }

}
