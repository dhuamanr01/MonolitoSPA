package com.monolito365.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.monolito365.common.constants.Constants;
import com.monolito365.domain.TipoDocumentoDomain;
import com.monolito365.dto.TipoDocumentoDTO;
import com.monolito365.utilities.DateUtil;

public class TipoDocumentoAssembler {
  public TipoDocumentoAssembler() {}

  public static TipoDocumentoDTO buildDtoDomain(TipoDocumentoDomain domain) throws Exception {
    TipoDocumentoDTO dto = null;

    if (domain != null) {
      dto = new TipoDocumentoDTO();
      dto.setId(domain.getId());
      dto.setDescripcion(domain.getDescripcion());
      dto.setCreatedAt(DateUtil.format(Constants.Format.DateTime.DateTime,
                                              domain.getCreatedAt()));
      dto.setUpdatedAt(domain.getUpdatedAt() == null ? null :
                             DateUtil.format(Constants.Format.DateTime.DateTime,
                                             domain.getUpdatedAt()));
    }

    return dto;
  }

  public static List<TipoDocumentoDTO> buildDtoDomainCollection(List<TipoDocumentoDomain> listDomain) throws Exception {
    List<TipoDocumentoDTO> listDTO = null;

    if (listDomain != null) {
      listDTO = new ArrayList<TipoDocumentoDTO>();

      for (TipoDocumentoDomain domain : listDomain) {
        listDTO.add(TipoDocumentoAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static List<TipoDocumentoDTO> buildDtoDomainCollection(Set<TipoDocumentoDomain> setDomain) throws Exception {
    List<TipoDocumentoDTO> listDTO = null;

    if (setDomain != null) {
      listDTO = new ArrayList<TipoDocumentoDTO>();

      for (TipoDocumentoDomain domain : setDomain) {
        listDTO.add(TipoDocumentoAssembler.buildDtoDomain(domain));
      }
    }

    return listDTO;
  }

  public static Page<TipoDocumentoDTO> buildDtoDomainCollection(Page<TipoDocumentoDomain> pageDomain) throws Exception {
    List<TipoDocumentoDTO> listDTO = new ArrayList<TipoDocumentoDTO>();

    for (TipoDocumentoDomain domain : pageDomain) {
      listDTO.add(TipoDocumentoAssembler.buildDtoDomain(domain));
    }

    Page<TipoDocumentoDTO> pageDTO = new PageImpl<TipoDocumentoDTO>(listDTO,
                                                                    pageDomain.getPageable(),
                                                                    pageDomain.getTotalElements());


    return pageDTO;
  }

}
