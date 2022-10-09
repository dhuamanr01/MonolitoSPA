package com.monolito365.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.monolito365.common.constants.Constants;
import com.monolito365.domain.ResetTokenDomain;
import com.monolito365.dto.ResetTokenDTO;
import com.monolito365.utilities.DateUtil;

public class ResetTokenAssembler {
  public ResetTokenAssembler() {}

  public static ResetTokenDTO buildDtoDomain(ResetTokenDomain domain) throws Exception {
    ResetTokenDTO dto = null;

    if (domain != null) {
      dto = new ResetTokenDTO();
      dto.setId(domain.getId());
      dto.setToken(domain.getToken());
      dto.setUsuario(UsuarioAssembler.buildDtoDomain(domain.getUsuario()));
      dto.setExpiracion(domain.getExpiracion() == null ? null :
                             DateUtil.format(Constants.Format.DateTime.DateTime,
                                             domain.getExpiracion()));
    }

    return dto;
  }

  public static List<ResetTokenDTO> buildDtoDomainCollection(List<ResetTokenDomain> listDomain) throws Exception {
    List<ResetTokenDTO> listDTO = null;

    if (listDomain != null) {
      listDTO = new ArrayList<ResetTokenDTO>();

      for (ResetTokenDomain domain : listDomain) {
        listDTO.add(ResetTokenAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static List<ResetTokenDTO> buildDtoDomainCollection(Set<ResetTokenDomain> setDomain) throws Exception {
    List<ResetTokenDTO> listDTO = null;

    if (setDomain != null) {
      listDTO = new ArrayList<ResetTokenDTO>();

      for (ResetTokenDomain domain : setDomain) {
        listDTO.add(ResetTokenAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static Page<ResetTokenDTO> buildDtoDomainCollection(Page<ResetTokenDomain> pageDomain) throws Exception {
    List<ResetTokenDTO> listDTO = new ArrayList<ResetTokenDTO>();

    for (ResetTokenDomain domain : pageDomain) {
      listDTO.add(ResetTokenAssembler.buildDtoDomain(domain));
    }

    Page<ResetTokenDTO> pageDTO = new PageImpl<ResetTokenDTO>(listDTO,
                                                              pageDomain.getPageable(),
                                                              pageDomain.getTotalElements());


    return pageDTO;
  }

}
