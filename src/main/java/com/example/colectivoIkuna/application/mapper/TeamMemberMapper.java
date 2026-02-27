package com.example.colectivoIkuna.application.mapper;

import com.example.colectivoIkuna.application.dto.request.TeamMemberDTO;
import com.example.colectivoIkuna.domain.model.TeamMember;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMemberMapper {
  TeamMemberDTO toDTO(TeamMember entity);
  TeamMember toEntity(TeamMemberDTO dto);
}