package com.monolito365.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.monolito365.common.constants.Constants;
import com.monolito365.domain.RoleDomain;
import com.monolito365.dto.RoleDTO;
import com.monolito365.utilities.DateUtil;

public class RoleAssembler {
  public RoleAssembler() {}

  public static RoleDTO buildDtoDomain(RoleDomain domain) throws Exception {
    RoleDTO dto = null;

    if (domain != null) {
      dto = new RoleDTO();
      dto.setId(domain.getId());
      dto.setNombre(domain.getNombre());
      dto.setAuth(domain.getAuth());
      dto.setCreatedAt(DateUtil.format(Constants.Format.DateTime.DateTime,
                                              domain.getCreatedAt()));
      dto.setUpdatedAt(domain.getUpdatedAt() == null ? null :
                             DateUtil.format(Constants.Format.DateTime.DateTime,
                                             domain.getUpdatedAt()));
    }

    return dto;
  }

  public static List<RoleDTO> buildDtoDomainCollection(List<RoleDomain> listDomain) throws Exception {
    List<RoleDTO> listDTO = null;

    if (listDomain != null) {
      listDTO = new ArrayList<RoleDTO>();

      for (RoleDomain domain : listDomain) {
        listDTO.add(RoleAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static List<RoleDTO> buildDtoDomainCollection(Set<RoleDomain> setDomain) throws Exception {
    List<RoleDTO> listDTO = null;

    if (setDomain != null) {
      listDTO = new ArrayList<RoleDTO>();

      for (RoleDomain domain : setDomain) {
        listDTO.add(RoleAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static Page<RoleDTO> buildDtoDomainCollection(Page<RoleDomain> pageDomain) throws Exception {
    List<RoleDTO> listDTO = new ArrayList<RoleDTO>();

    for (RoleDomain domain : pageDomain) {
      listDTO.add(RoleAssembler.buildDtoDomain(domain));
    }

    Page<RoleDTO> pageDTO = new PageImpl<RoleDTO>(listDTO,
                                                  pageDomain.getPageable(),
                                                  pageDomain.getTotalElements());


    return pageDTO;
  }

}
