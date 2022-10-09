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

import com.monolito365.assembler.ResetTokenAssembler;
import com.monolito365.domain.ResetTokenDomain;
import com.monolito365.dto.ResetTokenDTO;
import com.monolito365.repository.ResetTokenRepository;

@Service
public class ResetTokenService {
  @SuppressWarnings("unused")
  private static Logger logger = LoggerFactory.getLogger(ResetTokenService.class);

  @Autowired
  private ResetTokenRepository resetTokenRepository;

  public ResetTokenService() {
    super();
  }

  public void finalize(){}

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public List<ResetTokenDTO> findAll() throws Exception {
    List<ResetTokenDomain> listDomain = this.resetTokenRepository.findAll();
    List<ResetTokenDTO> listDTO = ResetTokenAssembler.buildDtoDomainCollection(listDomain);

    return listDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public Page<ResetTokenDTO> findAll(Pageable pageable) throws Exception {
    Page<ResetTokenDomain> pageDomain = this.resetTokenRepository.findAll(pageable);
    Page<ResetTokenDTO> pageDTO = ResetTokenAssembler.buildDtoDomainCollection(pageDomain);

    return pageDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public ResetTokenDTO findById(Long id) throws Exception {
    ResetTokenDTO dto = null;
    Optional<ResetTokenDomain> opt = this.resetTokenRepository.findById(id);

    if (opt.isPresent()) {
      ResetTokenDomain dom = opt.get();
      dto = ResetTokenAssembler.buildDtoDomain(dom);
    }

    return dto;
  }
}
