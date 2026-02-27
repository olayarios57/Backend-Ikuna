package com.example.colectivoIkuna.infrastructure.adapter.output.persistence;

import com.example.colectivoIkuna.domain.model.IkunaUser;
import com.example.colectivoIkuna.domain.port.out.IkunaUserRepositoryPort;
import com.example.colectivoIkuna.infrastructure.adapter.repository.IkunaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IkunaUserRepositoryAdapter implements IkunaUserRepositoryPort {

  private final IkunaUserRepository jpaRepository;

  @Override
  public Optional<IkunaUser> findByUsername(String username) {
    return jpaRepository.findByUsername(username);
  }

  @Override
  public Optional<IkunaUser> findById(Long id) {
    return jpaRepository.findById(id);
  }

  @Override
  public IkunaUser save(IkunaUser user) {
    return jpaRepository.save(user);
  }

  @Override
  public List<IkunaUser> findByStatus(String status) {
    return jpaRepository.findByStatus(status);
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }
}