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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserManagementUseCaseTest {

    @Mock
    private IkunaUserRepositoryPort userRepo;

    @InjectMocks
    private UserManagementUseCase userManagementUseCase;

    @Test
    void registerRequest_ShouldSetDefaultRoleAndStatus() {
        IkunaUser newUser = new IkunaUser();
        newUser.setUsername("johndoe");

        when(userRepo.findByUsername("johndoe")).thenReturn(Optional.empty());
        when(userRepo.save(any(IkunaUser.class))).thenAnswer(i -> i.getArguments()[0]);

        IkunaUser savedUser = userManagementUseCase.registerRequest(newUser);

        assertThat(savedUser.getRole()).isEqualTo("COLLABORATOR");
        assertThat(savedUser.getStatus()).isEqualTo("PENDING");
        assertThat(savedUser.getRequestDate()).isNotNull();
    }

    @Test
    void registerRequest_ShouldThrowException_WhenUsernameExists() {
        IkunaUser existingUser = new IkunaUser();
        existingUser.setUsername("johndoe");

        when(userRepo.findByUsername("johndoe")).thenReturn(Optional.of(existingUser));

        assertThatThrownBy(() -> userManagementUseCase.registerRequest(existingUser))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ya existe");
    }
}