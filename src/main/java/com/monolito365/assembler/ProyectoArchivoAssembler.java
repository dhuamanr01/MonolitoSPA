package com.monolito365.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.monolito365.common.constants.Constants;
import com.monolito365.domain.ProyectoArchivoDomain;
import com.monolito365.dto.ProyectoArchivoDTO;
import com.monolito365.utilities.DateUtil;

public class ProyectoArchivoAssembler {
  public ProyectoArchivoAssembler() {}

  public static ProyectoArchivoDTO buildDtoDomain(ProyectoArchivoDomain domain) throws Exception {
    ProyectoArchivoDTO dto = null;

    if (domain != null) {
      dto = new ProyectoArchivoDTO();
      dto.setId(domain.getId());
      dto.setProyecto(ProyectoAssembler.buildDtoDomain(domain.getProyecto()));
      dto.setNombre(domain.getNombre());
      dto.setRuta(domain.getRuta());
      dto.setCreatedAt(DateUtil.format(Constants.Format.DateTime.DateTime,
                                              domain.getCreatedAt()));
      dto.setUpdatedAt(domain.getUpdatedAt() == null ? null :
                             DateUtil.format(Constants.Format.DateTime.DateTime,
                                             domain.getUpdatedAt()));
    }

    return dto;
  }

  public static List<ProyectoArchivoDTO> buildDtoDomainCollection(List<ProyectoArchivoDomain> listDomain) throws Exception {
    List<ProyectoArchivoDTO> listDTO = new ArrayList<ProyectoArchivoDTO>();

    if (listDomain != null) {
      for (ProyectoArchivoDomain domain : listDomain) {
        listDTO.add(ProyectoArchivoAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static List<ProyectoArchivoDTO> buildDtoDomainCollection(Set<ProyectoArchivoDomain> setDomain) throws Exception {
    List<ProyectoArchivoDTO> listDTO = new ArrayList<ProyectoArchivoDTO>();

    if (setDomain != null) {
      for (ProyectoArchivoDomain domain : setDomain) {
        listDTO.add(ProyectoArchivoAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static Page<ProyectoArchivoDTO> buildDtoDomainCollection(Page<ProyectoArchivoDomain> pageDomain) throws Exception {
    List<ProyectoArchivoDTO> listDTO = new ArrayList<ProyectoArchivoDTO>();

    for (ProyectoArchivoDomain domain : pageDomain) {
      listDTO.add(ProyectoArchivoAssembler.buildDtoDomain(domain));
    }

    Page<ProyectoArchivoDTO> pageDTO = new PageImpl<ProyectoArchivoDTO>(listDTO,
                                                                        pageDomain.getPageable(),
                                                                        pageDomain.getTotalElements());


    return pageDTO;
  }

}
