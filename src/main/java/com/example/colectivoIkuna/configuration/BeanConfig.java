package com.example.colectivoIkuna.configuration;

import com.example.colectivoIkuna.application.usecases.AdminAuthUseCase;
import com.example.colectivoIkuna.application.usecases.IkunaManagerUseCase;
import com.example.colectivoIkuna.application.usecases.UserManagementUseCase;
import com.example.colectivoIkuna.domain.port.out.IkunaUserRepositoryPort;
import com.example.colectivoIkuna.domain.port.out.CulturalProjectRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public IkunaManagerUseCase ikunaManager(CulturalProjectRepositoryPort repo) {
        return new IkunaManagerUseCase(repo);
    }

    @Bean
    public AdminAuthUseCase adminAuth(IkunaUserRepositoryPort repo) {
        return new AdminAuthUseCase(repo);
    }
    @Bean
    public UserManagementUseCase userManagementUseCase(IkunaUserRepositoryPort repo) {
        return new UserManagementUseCase(repo);
    }
}