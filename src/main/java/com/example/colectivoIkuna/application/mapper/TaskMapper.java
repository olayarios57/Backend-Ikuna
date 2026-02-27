package com.example.colectivoIkuna.application.mapper;

import com.example.colectivoIkuna.application.dto.request.TaskDTO;
import com.example.colectivoIkuna.domain.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
  TaskDTO toDTO(Task entity);
  Task toEntity(TaskDTO dto);
}
