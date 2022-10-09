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

import com.monolito365.assembler.ClienteAssembler;
import com.monolito365.assembler.TipoDocumentoAssembler;
import com.monolito365.domain.TipoDocumentoDomain;
import com.monolito365.dto.TipoDocumentoDTO;
import com.monolito365.repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {
  @SuppressWarnings("unused")
  private static Logger logger = LoggerFactory.getLogger(TipoDocumentoService.class);

  @Autowired
  private TipoDocumentoRepository tipoDocumentoRepository;

  public TipoDocumentoService() {
    super();
  }

  public void finalize(){}

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public List<TipoDocumentoDTO> findAll() throws Exception {
    List<TipoDocumentoDomain> listDomain = this.tipoDocumentoRepository.findAll();
    List<TipoDocumentoDTO> listDTO = TipoDocumentoAssembler.buildDtoDomainCollection(listDomain);

    return listDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public Page<TipoDocumentoDTO> findAll(Pageable pageable) throws Exception {
    Page<TipoDocumentoDomain> pageDomain = this.tipoDocumentoRepository.findAll(pageable);
    Page<TipoDocumentoDTO> pageDTO = TipoDocumentoAssembler.buildDtoDomainCollection(pageDomain);

    return pageDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public TipoDocumentoDTO findById(Integer id, boolean dependency) throws Exception {
    TipoDocumentoDTO dto = null;
    Optional<TipoDocumentoDomain> opt = this.tipoDocumentoRepository.findById(id);

    if (opt.isPresent()) {
      TipoDocumentoDomain dom = opt.get();
      dto = TipoDocumentoAssembler.buildDtoDomain(dom);

      if (dependency) {
        dto.setClientes(ClienteAssembler.buildDtoDomainCollection(dom.getClientes()));
      }
    }

    return dto;
  }
}
