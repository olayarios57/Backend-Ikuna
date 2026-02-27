package com.example.colectivoIkuna.application.mapper;

import com.example.colectivoIkuna.application.dto.request.UserRequestDTO;
import com.example.colectivoIkuna.application.dto.response.UserResponseDTO;
import com.example.colectivoIkuna.domain.model.IkunaUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface AdminUserMapper {

  @Mapping(source = "requestDate", target = "requestDate", qualifiedByName = "dateToString")
  UserResponseDTO toDTO(IkunaUser entity);

  // --- DE REQUEST (FRONT) A ENTIDAD (BD) ---
  // Ignoramos ID, Status y Date porque eso lo pone la lógica de negocio, no el usuario
  @Mapping(target = "id", ignore = true)
  //@Mapping(target = "status", ignore = true)
  @Mapping(target = "requestDate", ignore = true)
  IkunaUser toEntity(UserRequestDTO dto);

  @Named("dateToString")
  default String dateToString(LocalDate date) {
    return date != null ? date.toString() : null;
  }

}
