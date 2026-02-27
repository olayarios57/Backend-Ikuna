package com.example.colectivoIkuna.infrastructure.adapter.input.rest;

import com.example.colectivoIkuna.application.dto.request.ChangePasswordDTO;
import com.example.colectivoIkuna.application.dto.request.UpdateProfileDTO;
import com.example.colectivoIkuna.application.dto.request.UserRequestDTO;
import com.example.colectivoIkuna.application.dto.response.UserResponseDTO;
import com.example.colectivoIkuna.application.mapper.AdminUserMapper;
import com.example.colectivoIkuna.application.usecases.UserManagementUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserManagementController {

  private final UserManagementUseCase userUseCase;
  private final AdminUserMapper userMapper;

  // CORRECCIÓN: Devuelve List<UserResponseDTO>
  @GetMapping("/pending")
  public List<UserResponseDTO> getPending() {
    return userUseCase.getPendingUsers().stream()
        .map(userMapper::toDTO)
        .collect(Collectors.toList());
  }

  // CORRECCIÓN: Devuelve List<UserResponseDTO>
  @GetMapping("/active")
  public List<UserResponseDTO> getActive() {
    return userUseCase.getActiveUsers().stream()
        .map(userMapper::toDTO)
        .collect(Collectors.toList());
  }

  // Este ya estaba bien (UserResponseDTO), ahora compilará porque el mapper ya devuelve ese tipo
  @PostMapping("/register")
  public UserResponseDTO register(@Valid @RequestBody UserRequestDTO requestDto) {
    var entity = userMapper.toEntity(requestDto);
    var saved = userUseCase.registerRequest(entity);
    return userMapper.toDTO(saved);
  }

  // CORRECCIÓN: Devuelve UserResponseDTO
  @PatchMapping("/{id}/approve")
  public UserResponseDTO approve(@PathVariable Long id) {
    return userMapper.toDTO(userUseCase.approveUser(id));
  }

  @DeleteMapping("/{id}/reject")
  public void reject(@PathVariable Long id) {
    userUseCase.rejectUser(id);
  }

  @PatchMapping("/{id}/disable")
  public ResponseEntity<Void> disableUser(@PathVariable Long id) {
    userUseCase.disableUser(id);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}/enable")
  public ResponseEntity<Void> enableUser(@PathVariable Long id) {
    userUseCase.enableUser(id);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userUseCase.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/profile")
  public ResponseEntity<UserResponseDTO> updateProfile(@PathVariable Long id, @RequestBody @Valid UpdateProfileDTO request) {
    var updatedUser = userUseCase.updateProfile(id, request);
    return ResponseEntity.ok(userMapper.toDTO(updatedUser));
  }

  @PutMapping("/{id}/password")
  public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody @Valid ChangePasswordDTO request) {
    userUseCase.changePassword(id, request);
    return ResponseEntity.ok().build();
  }

}
