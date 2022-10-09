package com.monolito365.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.monolito365.common.constants.Constants;
import com.monolito365.domain.ProyectoDomain;
import com.monolito365.dto.ProyectoDTO;
import com.monolito365.utilities.DateUtil;

public class ProyectoAssembler {
  public ProyectoAssembler() {}

  public static ProyectoDTO buildDtoDomain(ProyectoDomain domain) throws Exception {
    ProyectoDTO dto = null;

    if (domain != null) {
      dto = new ProyectoDTO();
      dto.setId(domain.getId());
      dto.setUsuario(UsuarioAssembler.buildDtoDomain(domain.getUsuario()));
      dto.setProyecto(domain.getProyecto());
      dto.setDescripcion(domain.getDescripcion());
      dto.setEstado(domain.getEstado());
      dto.setFechaExpiracion(DateUtil.format(Constants.Format.DateTime.DateTime,
                                              domain.getFechaExpiracion()));
      dto.setCreatedAt(DateUtil.format(Constants.Format.DateTime.DateTime,
                                              domain.getCreatedAt()));
      dto.setUpdatedAt(domain.getUpdatedAt() == null ? null :
                             DateUtil.format(Constants.Format.DateTime.DateTime,
                                             domain.getUpdatedAt()));
    }

    return dto;
  }

  public static List<ProyectoDTO> buildDtoDomainCollection(List<ProyectoDomain> listDomain) throws Exception {
    List<ProyectoDTO> listDTO = null;

    if (listDomain != null) {
      listDTO = new ArrayList<ProyectoDTO>();

      for (ProyectoDomain domain : listDomain) {
        listDTO.add(ProyectoAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static List<ProyectoDTO> buildDtoDomainCollection(Set<ProyectoDomain> setDomain) throws Exception {
    List<ProyectoDTO> listDTO = null;

    if (setDomain != null) {
      listDTO = new ArrayList<ProyectoDTO>();

      for (ProyectoDomain domain : setDomain) {
        listDTO.add(ProyectoAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static Page<ProyectoDTO> buildDtoDomainCollection(Page<ProyectoDomain> pageDomain) throws Exception {
    List<ProyectoDTO> listDTO = new ArrayList<ProyectoDTO>();

    for (ProyectoDomain domain : pageDomain) {
      listDTO.add(ProyectoAssembler.buildDtoDomain(domain));
    }

    Page<ProyectoDTO> pageDTO = new PageImpl<ProyectoDTO>(listDTO,
                                                          pageDomain.getPageable(),
                                                          pageDomain.getTotalElements());


    return pageDTO;
  }

}
