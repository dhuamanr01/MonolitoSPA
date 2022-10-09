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
import com.monolito365.assembler.ClienteAssembler;
import com.monolito365.assembler.ProyectoAssembler;
import com.monolito365.assembler.ResetTokenAssembler;
import com.monolito365.assembler.UsuarioAssembler;
import com.monolito365.domain.ProyectoDomain;
import com.monolito365.domain.RoleDomain;
import com.monolito365.domain.UsuarioDomain;
import com.monolito365.dto.ProyectoDTO;
import com.monolito365.dto.UsuarioDTO;
import com.monolito365.exception.FailledValidationException;
import com.monolito365.repository.RoleRepository;
import com.monolito365.repository.UsuarioRepository;

@Service
public class UsuarioService {
  @SuppressWarnings("unused")
  private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private RoleRepository roleRepository;

  public UsuarioService() {
    super();
  }

  public void finalize(){}

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public List<UsuarioDTO> findAll() throws Exception {
    List<UsuarioDomain> listDomain = this.usuarioRepository.findAll();
    List<UsuarioDTO> listDTO = UsuarioAssembler.buildDtoDomainCollection(listDomain);

    return listDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public Page<UsuarioDTO> findAll(Pageable pageable) throws Exception {
    Page<UsuarioDomain> pageDomain = this.usuarioRepository.findAllByOrderById(pageable);
    Page<UsuarioDTO> pageDTO = UsuarioAssembler.buildDtoDomainCollection(pageDomain);
    return pageDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public UsuarioDTO findById(Integer id, boolean dependency) throws Exception {
   UsuarioDTO dto = null;
    Optional<UsuarioDomain> opt = this.usuarioRepository.findById(id);

    if (opt.isPresent()) {
      UsuarioDomain dom = opt.get();
      dto = UsuarioAssembler.buildDtoDomain(dom);

      if (dependency) {
        dto.setTokens(ResetTokenAssembler.buildDtoDomainCollection(dom.getTokens()));

        for (ProyectoDomain proj : dom.getProyectos()) {
          ProyectoDTO p = ProyectoAssembler.buildDtoDomain(proj);
          p.setArchivos(ProyectoArchivoAssembler.buildDtoDomainCollection(proj.getArchivos()));
          dto.getProyectos().add(p);
        }

        dto.setClientes(ClienteAssembler.buildDtoDomainCollection(dom.getClientes()));
      }
    }

    return dto;
  }
  
  //logear por username
  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public UsuarioDTO findByUserName(String username, boolean dependency) throws Exception {
    UsuarioDTO dto = null;
    Optional<UsuarioDomain> opt = this.usuarioRepository.findByUserName(username);

    if (opt.isPresent()) {
      UsuarioDomain dom = opt.get();
      dto = UsuarioAssembler.buildDtoDomain(dom);

      if (dependency) {
        dto.setTokens(ResetTokenAssembler.buildDtoDomainCollection(dom.getTokens()));

        for (ProyectoDomain proj : dom.getProyectos()) {
          ProyectoDTO p = ProyectoAssembler.buildDtoDomain(proj);
          p.setArchivos(ProyectoArchivoAssembler.buildDtoDomainCollection(proj.getArchivos()));
          dto.getProyectos().add(p);
        }

        dto.setClientes(ClienteAssembler.buildDtoDomainCollection(dom.getClientes()));
      }
    }
    return dto;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public UsuarioDTO store(UsuarioDTO dto) throws Exception {
    if (dto == null) {
      throw new FailledValidationException("datos son obligatorios");
    }
    else if (dto.getUserName() == null || dto.getUserName().isEmpty()) {
      throw new FailledValidationException("[user_name] es obligatorio");
    }
    else if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
      throw new FailledValidationException("[password] es obligatorio");
    }
    else if (dto.getRole() == null) {
      throw new FailledValidationException("[role] es obligatorio");
    }
    else if (dto.getRole().getId() == null) {
      throw new FailledValidationException("[role.id] es obligatorio");
    }

    List<UsuarioDomain> list = this.usuarioRepository.findByUserNameAndEstado(dto.getUserName(), new Boolean(true));
    if (list != null && list.size() > 0) {
      throw new FailledValidationException("[user_name] ya se encuentra registrado");
    }

    Optional<RoleDomain> optRole = this.roleRepository.findById(dto.getRole().getId());
    if (!optRole.isPresent()) {
      throw new FailledValidationException("[role.id] no se encuentra");
    }

    UsuarioDomain domain = new UsuarioDomain();
    domain.setRole(optRole.get());
    domain.setUserName(dto.getUserName());
    domain.setPassword(dto.getPassword());
    domain.setEstado(new Boolean(true));
    domain.setCreatedAt(new Date());
    domain.setUpdatedAt(null);
    domain = this.usuarioRepository.save(domain);

    return UsuarioAssembler.buildDtoDomain(domain);
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public UsuarioDTO update(UsuarioDTO dto) throws Exception {
    if (dto == null) {
      throw new FailledValidationException("datos son obligatorios");
    }
    else if (dto.getId() == null) {
      throw new FailledValidationException("[id] es obligatorio");
    }

    UsuarioDomain domain = null;
    Optional<UsuarioDomain> optUser = this.usuarioRepository.findById(dto.getId());

    if (optUser.isPresent()) {
      domain = optUser.get();

      if (!domain.getEstado().booleanValue()) {
        throw new FailledValidationException("[usuario] eliminado anteriormente");
      }

      if (dto.getUserName() != null && !dto.getUserName().isEmpty()) {
        if (!dto.getUserName().equals(domain.getUserName())) {
          List<UsuarioDomain> list = this.usuarioRepository.findByUserNameAndIdNotAndEstado(dto.getUserName(),
                                                                                            dto.getId(),
                                                                                            new Boolean(true));

          if (list != null && list.size() > 0) {
            throw new FailledValidationException("[user_name] ya se encuentra registrado");
          }
          domain.setUserName(dto.getUserName());
        }
      }

      if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
        if (!dto.getPassword().equals(domain.getPassword())) {
          domain.setPassword(dto.getPassword());
        }
      }

      //Se agrego este fragmento de codigo para actualizar el campo estado
      if (dto.getEstado() != null ) {
        if (dto.getEstado() !=domain.getEstado() ) {
          domain.setEstado(dto.getEstado());
        }
      }

      if (dto.getRole() != null && dto.getRole().getId() != null) {
        if (dto.getRole().getId().intValue() != domain.getRole().getId().intValue()) {
          Optional<RoleDomain> optRole = this.roleRepository.findById(dto.getRole().getId());

          if (!optRole.isPresent()) {
            throw new FailledValidationException("[role.id] no se encuentra");
          }

          domain.setRole(optRole.get());
        }
      }

      domain.setUpdatedAt(new Date());
      domain = this.usuarioRepository.save(domain);
    }

    return UsuarioAssembler.buildDtoDomain(domain);
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public UsuarioDTO delete(Integer id) throws Exception {
    UsuarioDomain domain = null;
    Optional<UsuarioDomain> optUser = this.usuarioRepository.findById(id);

    if (optUser.isPresent()) {
      domain = optUser.get();

      if (!domain.getEstado().booleanValue()) {
        domain = null;
      }
      else {
        domain.setEstado(new Boolean(false));
        domain.setUpdatedAt(new Date());
        domain = this.usuarioRepository.save(domain);
      }
    }

    return UsuarioAssembler.buildDtoDomain(domain);
  }




}
