package com.monolito365.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.monolito365.common.constants.Constants;
import com.monolito365.domain.UsuarioDomain;
import com.monolito365.dto.UsuarioDTO;
import com.monolito365.utilities.DateUtil;

public class UsuarioAssembler {
  public UsuarioAssembler() {}

  public static UsuarioDTO buildDtoDomain(UsuarioDomain domain) throws Exception {
    UsuarioDTO dto = null;

    if (domain != null) {
      dto = new UsuarioDTO();
      dto.setId(domain.getId());
      dto.setRole(RoleAssembler.buildDtoDomain(domain.getRole()));
      dto.setUserName(domain.getUserName());
      dto.setPassword(domain.getPassword());
      dto.setEstado(domain.getEstado());
      dto.setCreatedAt(DateUtil.format(Constants.Format.DateTime.DateTime,
                                              domain.getCreatedAt()));
      dto.setUpdatedAt(domain.getUpdatedAt() == null ? null :
                             DateUtil.format(Constants.Format.DateTime.DateTime,
                                             domain.getUpdatedAt()));
    }

    return dto;
  }

  public static List<UsuarioDTO> buildDtoDomainCollection(List<UsuarioDomain> listDomain) throws Exception {
    List<UsuarioDTO> listDTO = new ArrayList<UsuarioDTO>();

    if (listDomain != null) {
      for (UsuarioDomain domain : listDomain) {
        listDTO.add(UsuarioAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static List<UsuarioDTO> buildDtoDomainCollection(Set<UsuarioDomain> setDomain) throws Exception {
    List<UsuarioDTO> listDTO = new ArrayList<UsuarioDTO>();

    if (setDomain != null) {
      for (UsuarioDomain domain : setDomain) {
        listDTO.add(UsuarioAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static Page<UsuarioDTO> buildDtoDomainCollection(Page<UsuarioDomain> pageDomain) throws Exception {
    List<UsuarioDTO> listDTO = new ArrayList<UsuarioDTO>();

    for (UsuarioDomain domain : pageDomain) {
      listDTO.add(UsuarioAssembler.buildDtoDomain(domain));
    }

    Page<UsuarioDTO> pageDTO = new PageImpl<UsuarioDTO>(listDTO,
                                                        pageDomain.getPageable(),
                                                        pageDomain.getTotalElements());


    return pageDTO;
  }

}
