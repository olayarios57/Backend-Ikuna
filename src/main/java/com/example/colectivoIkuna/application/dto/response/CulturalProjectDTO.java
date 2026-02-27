package com.example.colectivoIkuna.application.dto.response;

import com.example.colectivoIkuna.application.dto.request.BudgetDTO;
import com.example.colectivoIkuna.application.dto.request.TaskDTO;
import com.example.colectivoIkuna.application.dto.request.TeamMemberDTO;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

@Data
public class CulturalProjectDTO {
  private Long id;
  private String title;
  private String status;
  private String date; // String para React
  private Integer progress;
  private String category;
  private String description;
  private String imageUrl;
  private BigDecimal totalBudget;
  private BigDecimal executedBudget;


  private List<EventCalendarDTO> calendar;
  private List<ProjectCollaborationDTO> collaborators;

  private List<TaskDTO> tasks;
  private List<TeamMemberDTO> teamMembers;
  private List<BudgetDTO> expenses;
}