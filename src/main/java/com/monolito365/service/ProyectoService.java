package com.monolito365.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.monolito365.assembler.ProyectoArchivoAssembler;
import com.monolito365.assembler.ProyectoAssembler;
import com.monolito365.common.constants.Constants;
import com.monolito365.domain.ProyectoDomain;
import com.monolito365.domain.UsuarioDomain;
import com.monolito365.dto.ProyectoDTO;
import com.monolito365.exception.FailledValidationException;
import com.monolito365.repository.ProyectoRepository;
import com.monolito365.repository.UsuarioRepository;
import com.monolito365.utilities.DateUtil;

@Service
public class ProyectoService {
  @SuppressWarnings("unused")
  private static Logger logger = LoggerFactory.getLogger(ProyectoService.class);

  @Autowired
  private ProyectoRepository proyectoRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  public ProyectoService() {
    super();
  }

  public void finalize(){}

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public List<ProyectoDTO> findAll() throws Exception {
    List<ProyectoDomain> listDomain = this.proyectoRepository.findAll();
    List<ProyectoDTO> listDTO = ProyectoAssembler.buildDtoDomainCollection(listDomain);

    return listDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public Page<ProyectoDTO> findAll(Pageable pageable) throws Exception {
    Page<ProyectoDomain> pageDomain = this.proyectoRepository.findAll(pageable);
    Page<ProyectoDTO> pageDTO = ProyectoAssembler.buildDtoDomainCollection(pageDomain);

    return pageDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public ProyectoDTO findById(Integer id, boolean dependency) throws Exception {
    ProyectoDTO dto = null;
    Optional<ProyectoDomain> opt = this.proyectoRepository.findById(id);

    if (opt.isPresent()) {
      ProyectoDomain dom = opt.get();
      dto = ProyectoAssembler.buildDtoDomain(dom);

      if (dependency) {
        dto.setArchivos(ProyectoArchivoAssembler.buildDtoDomainCollection(dom.getArchivos()));
      }
    }

    return dto;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public ProyectoDTO store(ProyectoDTO dto) throws Exception {
    if (dto == null) {
      throw new FailledValidationException("datos son obligatorios");
    }
    else if (dto.getProyecto() == null || dto.getProyecto().isEmpty()) {
      throw new FailledValidationException("[proyecto] es obligatorio");
    }
    else if (dto.getDescripcion() == null || dto.getDescripcion().isEmpty()) {
      throw new FailledValidationException("[descripcion] es obligatorio");
    }
    else if (dto.getFechaExpiracion() == null || dto.getFechaExpiracion().isEmpty()) {
      throw new FailledValidationException("[fecha_expiracion] es obligatorio");
    }
    else if (!DateUtil.validateDateFormat(dto.getFechaExpiracion())) {
      throw new FailledValidationException("[fecha_expiracion] con formato invalido");
    }
    else if (!DateUtil.validateDateValue(dto.getFechaExpiracion())) {
      throw new FailledValidationException("[fecha_expiracion] invalido");
    }
    else if (Integer.parseInt(DateUtil.format(Constants.Format.Date.YearMonthDay, DateUtil.getDate(dto.getFechaExpiracion()))) <=
             Integer.parseInt(DateUtil.format(Constants.Format.Date.YearMonthDay))) {
      throw new FailledValidationException("[fecha_expiracion] no puede ser anterior o igual al dia de hoy");
    }
    else if (dto.getUsuario() == null) {
      throw new FailledValidationException("[usuario] es obligatorio");
    }
    else if (dto.getUsuario().getId() == null) {
      throw new FailledValidationException("[usuario.id] es obligatorio");
    }

//    List<ProyectoDomain> list = this.proyectoRepository.findByProyectoAndEstado(dto.getProyecto().toUpperCase(), new Boolean(true));
//    
    List<ProyectoDomain> list = this.proyectoRepository.findByProyectoAndEstado(dto.getProyecto(), new Boolean(true));
    
    if (list != null && list.size() > 0) {
      throw new FailledValidationException("[proyecto] ya se encuentra registrado");
    }

    Optional<UsuarioDomain> optUser = this.usuarioRepository.findById(dto.getUsuario().getId());
    if (!optUser.isPresent()) {
      throw new FailledValidationException("[usuario.id] no se encuentra");
    }
    else if (!optUser.get().getEstado().booleanValue()) {
      throw new FailledValidationException("[usuario] eliminado anteriormente");
    }

    ProyectoDomain domain = new ProyectoDomain();
    domain.setUsuario(optUser.get());
//    domain.setProyecto(dto.getProyecto().toUpperCase());
    domain.setProyecto(dto.getProyecto());
    domain.setDescripcion(dto.getDescripcion());
    domain.setFechaExpiracion(DateUtil.getDate(dto.getFechaExpiracion()));
    domain.setEstado(new Boolean(true));
    domain.setCreatedAt(new Date());
    domain.setUpdatedAt(null);
    domain = this.proyectoRepository.save(domain);

    return ProyectoAssembler.buildDtoDomain(domain);
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public ProyectoDTO update(ProyectoDTO dto) throws Exception {
    if (dto == null) {
      throw new FailledValidationException("datos son obligatorios");
    }
    else if (dto.getId() == null) {
      throw new FailledValidationException("[id] es obligatorio");
    }

    ProyectoDomain domain = null;
    Optional<ProyectoDomain> optProj = this.proyectoRepository.findById(dto.getId());

    if (optProj.isPresent()) {
      domain = optProj.get();

      if (!domain.getEstado().booleanValue()) {
        throw new FailledValidationException("[proyecto] eliminado anteriormente");
      }

      if (dto.getProyecto() != null && !dto.getProyecto().isEmpty()) {
        if (!dto.getProyecto().toUpperCase().equals(domain.getProyecto().toUpperCase())) {
          List<ProyectoDomain> list = this.proyectoRepository.findByProyectoAndIdNotAndEstado(dto.getProyecto(),
                                                                                              dto.getId(),
                                                                                              new Boolean(true));

          if (list != null && list.size() > 0) {
            throw new FailledValidationException("[proyecto] ya se encuentra registrado");
          }
//          domain.setProyecto(dto.getProyecto().toUpperCase());
          domain.setProyecto(dto.getProyecto());
        }
      }

      if (dto.getDescripcion() != null && !dto.getDescripcion().isEmpty()) {
        if (!dto.getDescripcion().equals(domain.getDescripcion())) {
          domain.setDescripcion(dto.getDescripcion());
        }
      }

      if (dto.getEstado() != null ) {
        if (!dto.getEstado().equals(domain.getEstado())) {
          domain.setEstado(dto.getEstado());
        }
      }

      if (dto.getFechaExpiracion() != null && !dto.getFechaExpiracion().isEmpty()) {
        if (!DateUtil.validateDateFormat(dto.getFechaExpiracion())) {
          throw new FailledValidationException("[fecha_expiracion] con formato invalido");
        }
        else if (!DateUtil.validateDateValue(dto.getFechaExpiracion())) {
          throw new FailledValidationException("[fecha_expiracion] invalido");
        }
        else if (Integer.parseInt(DateUtil.format(Constants.Format.Date.YearMonthDay, DateUtil.getDate(dto.getFechaExpiracion()))) <=
                 Integer.parseInt(DateUtil.format(Constants.Format.Date.YearMonthDay))) {
          throw new FailledValidationException("[fecha_expiracion] no puede ser anterior o igual al dia de hoy");
        }
        domain.setFechaExpiracion(DateUtil.getDate(dto.getFechaExpiracion()));
      }

      if (dto.getUsuario() != null && dto.getUsuario().getId() != null) {
        if (dto.getUsuario().getId().intValue() != domain.getUsuario().getId().intValue()) {
          Optional<UsuarioDomain> optUser = this.usuarioRepository.findById(dto.getUsuario().getId());

          if (!optUser.isPresent()) {
            throw new FailledValidationException("[usuario.id] no se encuentra");
          }
          else if (!optUser.get().getEstado().booleanValue()) {
            throw new FailledValidationException("[usuario] eliminado anteriormente");
          }

          domain.setUsuario(optUser.get());
        }
      }

      domain.setUpdatedAt(new Date());
      domain = this.proyectoRepository.save(domain);
    }

    return ProyectoAssembler.buildDtoDomain(domain);
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public ProyectoDTO delete(Integer id) throws Exception {
    ProyectoDomain domain = null;
    Optional<ProyectoDomain> optProj = this.proyectoRepository.findById(id);

    if (optProj.isPresent()) {
      domain = optProj.get();

      if (!domain.getEstado().booleanValue()) {
        domain = null;
      }
      else {
        domain.setEstado(new Boolean(false));
        domain.setUpdatedAt(new Date());
        domain = this.proyectoRepository.save(domain);
      }
    }

    return ProyectoAssembler.buildDtoDomain(domain);
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public int disableExpiration() throws Exception {
    int updated = this.proyectoRepository.updateExpiration();
    return updated;
  }
}
