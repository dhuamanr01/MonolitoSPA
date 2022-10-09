package com.monolito365.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monolito365.domain.ProyectoArchivoDomain;
import com.monolito365.domain.ProyectoDomain;

@Repository
public interface ProyectoArchivoRepository extends JpaRepository<ProyectoArchivoDomain, Integer> {
  public List<ProyectoArchivoDomain> findAll();

  public Page<ProyectoArchivoDomain> findAll(Pageable pageable);

  public Optional<ProyectoArchivoDomain> findById(Integer id);

  public List<ProyectoArchivoDomain> findByProyecto(ProyectoDomain proyecto);

  public List<ProyectoArchivoDomain> findByProyectoAndNombre(ProyectoDomain proyecto, String nombre);

  public List<ProyectoArchivoDomain> findByProyectoAndRuta(ProyectoDomain proyecto, String ruta);
}
