package com.monolito365.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monolito365.domain.UsuarioDomain;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDomain, Integer> {
  public List<UsuarioDomain> findAll();

  public Page<UsuarioDomain> findAllByOrderById(Pageable pageable);

  public Optional<UsuarioDomain> findById(Integer id);

  public List<UsuarioDomain> findByUserNameAndEstado(String userName, Boolean estado);

  public List<UsuarioDomain> findByUserNameAndIdNotAndEstado(String userName, Integer id, Boolean estado);

  public Optional<UsuarioDomain> findByUserName(String username);

 
//  @Query("SELECT u FROM UsuarioDomain u WHERE u.userName = :username")
//  public Optional<UsuarioDomain> findByUserName(@Param("username") String username);
}
