package com.example.colectivoIkuna.application.usecases;

import com.example.colectivoIkuna.domain.model.IkunaUser;
import com.example.colectivoIkuna.domain.port.out.IkunaUserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminAuthUseCaseTest {

    @Mock
    private IkunaUserRepositoryPort adminRepo;

    @InjectMocks
    private AdminAuthUseCase adminAuthUseCase;

    @Test
    void authenticate_ShouldReturnUser_WhenCredentialsAreValid() {
        IkunaUser mockUser = new IkunaUser();
        mockUser.setUsername("admin");
        mockUser.setPassword("ikuna2024");

        when(adminRepo.findByUsername("admin")).thenReturn(Optional.of(mockUser));

        IkunaUser result = adminAuthUseCase.authenticate("admin", "ikuna2024");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("admin");
    }

    @Test
    void authenticate_ShouldThrowException_WhenUserNotFound() {
        when(adminRepo.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> adminAuthUseCase.authenticate("unknown", "password"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Usuario no encontrado");
    }

    @Test
    void authenticate_ShouldThrowException_WhenPasswordIsIncorrect() {
        IkunaUser mockUser = new IkunaUser();
        mockUser.setUsername("admin");
        mockUser.setPassword("ikuna2024");

        when(adminRepo.findByUsername("admin")).thenReturn(Optional.of(mockUser));

        assertThatThrownBy(() -> adminAuthUseCase.authenticate("admin", "wrongpassword"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Contraseña incorrecta");
    }
}