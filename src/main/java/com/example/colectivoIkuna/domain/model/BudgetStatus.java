package com.example.colectivoIkuna.domain.model;

public enum BudgetStatus {
    APPROVED("Aprobado"),
    REJECTED("Rechazado"),
    COMPLETED("Completado"),
    CANCELLED("Cancelado"),
    DRAFT("Borrador");

    private final String displayName;

    BudgetStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
