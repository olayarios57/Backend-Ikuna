package com.example.colectivoIkuna.application.usecases;

import com.example.colectivoIkuna.domain.model.CulturalProject;
import com.example.colectivoIkuna.domain.port.out.CulturalProjectRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IkunaManagerUseCaseTest {

    @Mock
    private CulturalProjectRepositoryPort projectRepo;

    @InjectMocks
    private IkunaManagerUseCase ikunaManagerUseCase;

    @Test
    void launchOrUpdateProject_ShouldCalculateProgressCorrectly() {
        CulturalProject project = new CulturalProject();
        project.setTotalBudget(new BigDecimal("1000.00"));
        project.setExecutedBudget(new BigDecimal("250.00"));

        when(projectRepo.save(any(CulturalProject.class))).thenAnswer(i -> i.getArguments()[0]);

        CulturalProject savedProject = ikunaManagerUseCase.launchOrUpdateProject(project);

        assertThat(savedProject.getProgress()).isEqualTo(25);
        verify(projectRepo).save(project);
    }
}