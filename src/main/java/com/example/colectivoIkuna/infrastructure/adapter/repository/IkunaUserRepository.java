package com.example.colectivoIkuna.infrastructure.adapter.repository;

import com.example.colectivoIkuna.domain.model.IkunaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IkunaUserRepository extends JpaRepository<IkunaUser, Long> {
  Optional<IkunaUser> findByUsername(String username);
  List<IkunaUser> findByStatus(String status);
  Optional<IkunaUser> findByEmail(String email);
}