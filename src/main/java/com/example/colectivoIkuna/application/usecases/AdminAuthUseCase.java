package com.example.colectivoIkuna.application.usecases;

import com.example.colectivoIkuna.domain.model.IkunaUser;
import com.example.colectivoIkuna.domain.port.out.IkunaUserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminAuthUseCase {

  private final IkunaUserRepositoryPort adminRepo;

  public IkunaUser authenticate(String username, String password) {
    IkunaUser admin = adminRepo.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    // En producción usa BCrypt
    if (!admin.getPassword().equals(password)) {
      throw new RuntimeException("Contraseña incorrecta");
    }
    return admin;
  }

  // Método auxiliar para crear el primer admin si no existe
  public void createInitialAdmin() {
    if(adminRepo.findByUsername("admin").isEmpty()){
      IkunaUser admin = new IkunaUser();
      admin.setUsername("admin");
      admin.setPassword("ikuna2024");
      admin.setFullName("Super Admin");
      admin.setRole("superadmin");
      admin.setEmail("admin@ikuna.com");
      adminRepo.save(admin);
    }
  }
}