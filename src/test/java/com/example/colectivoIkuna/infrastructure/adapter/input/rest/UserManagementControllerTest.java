package com.example.colectivoIkuna.infrastructure.adapter.input.rest;

import com.example.colectivoIkuna.application.dto.response.UserResponseDTO;
import com.example.colectivoIkuna.application.mapper.AdminUserMapper;
import com.example.colectivoIkuna.application.usecases.UserManagementUseCase;
import com.example.colectivoIkuna.domain.model.IkunaUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserManagementControllerTest {

    @Mock
    private UserManagementUseCase userUseCase;

    @Mock
    private AdminUserMapper userMapper;

    @InjectMocks
    private UserManagementController controller;

    @Test
    void getPending_ShouldReturnListOfPendingUsers() {
        // Arrange
        IkunaUser mockEntity = new IkunaUser();
        mockEntity.setUsername("juanperez");

        UserResponseDTO mockDto = new UserResponseDTO();
        mockDto.setUsername("juanperez");
        mockDto.setStatus("PENDING");

        when(userUseCase.getPendingUsers()).thenReturn(List.of(mockEntity));
        when(userMapper.toDTO(mockEntity)).thenReturn(mockDto);

        // Act
        List<UserResponseDTO> response = controller.getPending();

        // Assert
        assertThat(response).hasSize(1);
        assertThat(response.get(0).getUsername()).isEqualTo("juanperez");
        assertThat(response.get(0).getStatus()).isEqualTo("PENDING");
    }
}