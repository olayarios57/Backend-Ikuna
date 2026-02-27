package com.example.colectivoIkuna.infrastructure.adapter.input.rest;

import com.example.colectivoIkuna.application.dto.request.BudgetDTO;
import com.example.colectivoIkuna.application.usecases.BudgetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BudgetController {

    private final BudgetUseCase budgetservice;

    @PostMapping("/budgets")
    public ResponseEntity<BudgetDTO> create(@RequestBody BudgetDTO request) {

        System.out.println("DTO RECIBIDO");
        System.out.println(request);

        BudgetDTO createBudget = budgetservice.createBudget(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createBudget);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(budgetservice.getBudgetById(id));
    }

    @GetMapping("/project/{Id}")
    public ResponseEntity<List<BudgetDTO>> getByProject(@PathVariable Long Id) {
        List<BudgetDTO> budgets = budgetservice.getAllBudgetsByProject(Id);
        return ResponseEntity.ok(budgets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetDTO> update(@PathVariable Long id, @RequestBody BudgetDTO request) {
        BudgetDTO updatedBudget = budgetservice.updateBudget(id, request);
        return ResponseEntity.ok(updatedBudget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        budgetservice.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }
}
