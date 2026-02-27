package com.example.colectivoIkuna.domain.port.out;

import com.example.colectivoIkuna.domain.model.IkunaUser;

import java.util.List;
import java.util.Optional;

public interface IkunaUserRepositoryPort {
  Optional<IkunaUser> findByUsername(String username);
  Optional<IkunaUser> findById(Long id);
  IkunaUser save(IkunaUser user);
  List<IkunaUser> findByStatus(String status);
  void deleteById(Long id);
}