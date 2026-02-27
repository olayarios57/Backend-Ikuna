package com.example.colectivoIkuna.infrastructure.adapter.input.rest;

import com.example.colectivoIkuna.application.dto.request.LoginRequestDTO;
import com.example.colectivoIkuna.application.usecases.AdminAuthUseCase;
import com.example.colectivoIkuna.domain.model.IkunaUser;
import com.example.colectivoIkuna.infrastructure.adapter.input.rest.AdminAuthController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminAuthControllerTest {

    @Mock
    private AdminAuthUseCase authUseCase;

    // Injects the mock directly into the controller
    @InjectMocks
    private AdminAuthController adminAuthController;

    @Test
    void login_ShouldReturnUser_WhenCredentialsAreValid() {
        // Arrange
        LoginRequestDTO request = new LoginRequestDTO();
        request.setUsername("admin");
        request.setPassword("ikuna2024");

        IkunaUser mockUser = new IkunaUser();
        mockUser.setUsername("admin");
        mockUser.setRole("SUPER_ADMIN");

        when(authUseCase.authenticate("admin", "ikuna2024")).thenReturn(mockUser);

        // Act - Call the method directly!
        IkunaUser response = adminAuthController.login(request);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getUsername()).isEqualTo("admin");
        assertThat(response.getRole()).isEqualTo("SUPER_ADMIN");
    }
}