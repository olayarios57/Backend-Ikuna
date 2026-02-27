package com.example.colectivoIkuna.application.mapper;

import com.example.colectivoIkuna.application.dto.request.UserRequestDTO;
import com.example.colectivoIkuna.application.dto.response.UserResponseDTO;
import com.example.colectivoIkuna.domain.model.IkunaUser;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

class AdminUserMapperTest {

    // We instantiate the actual generated mapper here
    private final AdminUserMapper mapper = Mappers.getMapper(AdminUserMapper.class);

    @Test
    void shouldMapEntityToDtoAndConvertDateToString() {
        // Arrange
        IkunaUser user = new IkunaUser();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRequestDate(LocalDate.of(2025, 10, 15));

        // Act
        UserResponseDTO dto = mapper.toDTO(user);

        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getUsername()).isEqualTo("testuser");
        assertThat(dto.getRequestDate()).isEqualTo("2025-10-15"); // Verifying the @Named logic
    }

    @Test
    void shouldMapDtoToEntityAndIgnoreCertainFields() {
        // Arrange
        UserRequestDTO requestDto = new UserRequestDTO();
        requestDto.setUsername("newuser");
        requestDto.setFullName("New User");
        requestDto.setEmail("new@example.com");

        // Act
        IkunaUser entity = mapper.toEntity(requestDto);

        // Assert
        assertThat(entity).isNotNull();
        assertThat(entity.getUsername()).isEqualTo("newuser");
        assertThat(entity.getId()).isNull(); // Should be ignored as per @Mapping
        assertThat(entity.getRequestDate()).isNull(); // Should be ignored
    }
}