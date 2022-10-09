package com.monolito365.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.monolito365.assembler.RoleAssembler;
import com.monolito365.assembler.UsuarioAssembler;
import com.monolito365.domain.RoleDomain;
import com.monolito365.dto.RoleDTO;
import com.monolito365.repository.RoleRepository;

@Service
public class RoleService {
  @SuppressWarnings("unused")
  private static Logger logger = LoggerFactory.getLogger(RoleService.class);

  @Autowired
  private RoleRepository roleRepository;

  public RoleService() {
    super();
  }

  public void finalize(){}

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public List<RoleDTO> findAll() throws Exception {
    List<RoleDomain> listDomain = this.roleRepository.findAll();
    List<RoleDTO> listDTO = RoleAssembler.buildDtoDomainCollection(listDomain);

    return listDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public Page<RoleDTO> findAll(Pageable pageable) throws Exception {
    Page<RoleDomain> pageDomain = this.roleRepository.findAll(pageable);
    Page<RoleDTO> pageDTO = RoleAssembler.buildDtoDomainCollection(pageDomain);

    return pageDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public RoleDTO findById(Integer id, boolean dependency) throws Exception {
    RoleDTO dto = null;
    Optional<RoleDomain> opt = this.roleRepository.findById(id);

    if (opt.isPresent()) {
      RoleDomain dom = opt.get();
      dto = RoleAssembler.buildDtoDomain(dom);

      if (dependency) {
        dto.setUsuarios(UsuarioAssembler.buildDtoDomainCollection(dom.getUsuarios()));
      }
    }

    return dto;
  }
}
