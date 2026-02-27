package com.example.colectivoIkuna.application.usecases;

import com.example.colectivoIkuna.application.dto.request.ChangePasswordDTO;
import com.example.colectivoIkuna.application.dto.request.UpdateProfileDTO;
import com.example.colectivoIkuna.domain.model.CulturalProject;
import com.example.colectivoIkuna.domain.model.IkunaUser;
import com.example.colectivoIkuna.domain.port.out.IkunaUserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserManagementUseCase {

    private final IkunaUserRepositoryPort userRepo;

    public List<IkunaUser> getPendingUsers() {
        return userRepo.findByStatus("PENDING");
    }

    public List<IkunaUser> getActiveUsers() {
        return userRepo.findByStatus("ACTIVE");
    }

    public void createSuperAdmin() {
        IkunaUser admin = new IkunaUser();
        admin.setUsername("admin");
        admin.setRole("SUPER_ADMIN"); // <--- Aquí definimos que es el jefe
        admin.setStatus("ACTIVE");
        userRepo.save(admin);
    }

    public IkunaUser registerRequest(IkunaUser user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El usuario '" + user.getUsername() + "' ya existe.");
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("COLLABORATOR");
        }

        if (user.getStatus() == null || user.getStatus().isEmpty()) {
            user.setStatus("PENDING");
        }

        user.setRequestDate(java.time.LocalDate.now());

        return userRepo.save(user);
    }

    public IkunaUser approveUser(Long userId) {
        IkunaUser user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setStatus("ACTIVE");
        return userRepo.save(user);
    }

    public void rejectUser(Long userId) {
        if (!userRepo.findById(userId).isPresent()) {
            throw new RuntimeException("No se puede rechazar, el usuario no existe.");
        }
        userRepo.deleteById(userId);
    }

    @Transactional
    public void disableUser(Long userId) {
        IkunaUser user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // Evitar que el admin principal se deshabilite a sí mismo por error (opcional pero recomendado)
        if ("SUPER_ADMIN".equals(user.getRole()) && "admin".equals(user.getUsername())) {
            throw new IllegalArgumentException("No puedes inhabilitar al Administrador Principal.");
        }
        user.setStatus("INACTIVE");
        userRepo.save(user);
    }

    @Transactional
    public void enableUser(Long userId) {
        IkunaUser user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setStatus("ACTIVE");
        userRepo.save(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        IkunaUser user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if ("SUPER_ADMIN".equals(user.getRole()) && "admin".equals(user.getUsername())) {
            throw new IllegalArgumentException("No puedes eliminar al Administrador Principal.");
        }
        userRepo.deleteById(userId);
    }

    @Transactional
    public IkunaUser updateProfile(Long userId, UpdateProfileDTO dto) {
        IkunaUser user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());

        return userRepo.save(user);
    }

    @Transactional
    public void changePassword(Long userId, ChangePasswordDTO dto) {
        IkunaUser user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validación de contraseña actual (Sin BCrypt por ahora, como tienes en AdminAuthUseCase)
        if (!user.getPassword().equals(dto.getCurrentPassword())) {
            throw new IllegalArgumentException("La contraseña actual es incorrecta");
        }

        user.setPassword(dto.getNewPassword());
        userRepo.save(user);
    }
}
