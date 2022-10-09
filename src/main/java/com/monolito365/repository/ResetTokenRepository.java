package com.monolito365.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monolito365.domain.ResetTokenDomain;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetTokenDomain, Long> {
  public List<ResetTokenDomain> findAll();

  public Page<ResetTokenDomain> findAll(Pageable pageable);

  public Optional<ResetTokenDomain> findById(Long id);
}
