package com.monolito365.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monolito365.assembler.RoleAssembler;
import com.monolito365.assembler.ServicioAssembler;
import com.monolito365.domain.ServicioDomain;
import com.monolito365.dto.ServicioDTO;
import com.monolito365.repository.ServicioRepository;

@Service
public class ServicioService {
  @SuppressWarnings("unused")
  private static Logger logger = LoggerFactory.getLogger(ServicioService.class);

  @Autowired
  private ServicioRepository servicioRepository;

  public ServicioService() {
    super();
  }

  public void finalize(){}

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<ServicioDTO> findAll() throws Exception {
    List<ServicioDomain> listDomain = this.servicioRepository.findAll(Sort.by(Sort.Direction.ASC, "metodo", "orden"));

    List<ServicioDTO> listDTO = new ArrayList<ServicioDTO>();
    for (ServicioDomain dom : listDomain) {
      ServicioDTO dto = ServicioAssembler.buildDtoDomain(dom);
      dto.setRoles(RoleAssembler.buildDtoDomainCollection(dom.getRoles()));
      listDTO.add(dto);
    }

    return listDTO;
  }

  @Transactional(Transactional.TxType.SUPPORTS)
  public List<ServicioDTO> findByMetodo(String metodo, Sort sort) throws Exception {
    List<ServicioDomain> listDomain = this.servicioRepository.findByMetodo(metodo, Sort.by(Sort.Direction.ASC, "orden"));

    List<ServicioDTO> listDTO = new ArrayList<ServicioDTO>();
    for (ServicioDomain dom : listDomain) {
      ServicioDTO dto = ServicioAssembler.buildDtoDomain(dom);
      dto.setRoles(RoleAssembler.buildDtoDomainCollection(dom.getRoles()));
      listDTO.add(dto);
    }

    return listDTO;
  }
}
