package com.monolito365.service;

import java.io.File;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.monolito365.assembler.ProyectoArchivoAssembler;
import com.monolito365.controller.model.FileModel;
import com.monolito365.domain.ProyectoArchivoDomain;
import com.monolito365.domain.ProyectoDomain;
import com.monolito365.dto.ProyectoArchivoDTO;
import com.monolito365.dto.ProyectoDTO;
import com.monolito365.exception.FailledValidationException;
import com.monolito365.repository.ProyectoArchivoRepository;
import com.monolito365.repository.ProyectoRepository;
import com.monolito365.utilities.NumberUtil;

@Service
public class ProyectoArchivoService {
  private static Logger logger = LoggerFactory.getLogger(ProyectoArchivoService.class);

  @Autowired
  private ProyectoArchivoRepository proyectoArchivoRepository;

  @Autowired
  private ProyectoRepository proyectoRepository;

  @Value("${application.folder.proyectos}")
  private String folder;

  public ProyectoArchivoService() {
    super();
  }

  public void finalize(){}

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public List<ProyectoArchivoDTO> findAll() throws Exception {
    List<ProyectoArchivoDomain> listDomain = this.proyectoArchivoRepository.findAll();
    List<ProyectoArchivoDTO> listDTO = ProyectoArchivoAssembler.buildDtoDomainCollection(listDomain);

    return listDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public Page<ProyectoArchivoDTO> findAll(Pageable pageable) throws Exception {
    Page<ProyectoArchivoDomain> pageDomain = this.proyectoArchivoRepository.findAll(pageable);
    Page<ProyectoArchivoDTO> pageDTO = ProyectoArchivoAssembler.buildDtoDomainCollection(pageDomain);

    return pageDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public ProyectoArchivoDTO findById(Integer id) throws Exception {
    ProyectoArchivoDTO dto = null;
    Optional<ProyectoArchivoDomain> opt = this.proyectoArchivoRepository.findById(id);

    if (opt.isPresent()) {
      ProyectoArchivoDomain dom = opt.get();
      dto = ProyectoArchivoAssembler.buildDtoDomain(dom);
    }

    return dto;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public ProyectoArchivoDTO downloadBase64(Integer id) throws Exception {
    ProyectoArchivoDTO dto = null;
    Optional<ProyectoArchivoDomain> opt = this.proyectoArchivoRepository.findById(id);

    if (opt.isPresent()) {
      ProyectoArchivoDomain dom = opt.get();
      dto = ProyectoArchivoAssembler.buildDtoDomain(dom);
      dto.setProyecto(null);

      String folderPath = this.folder + "/Proj" + NumberUtil.format("000000", dom.getProyecto().getId());
      File file = new File(folderPath + "/" + NumberUtil.format("000000", dto.getId()) + ".bin");
      logger.info(" > File: " + dto.getRuta());
      logger.info(" > File: " + file.getAbsolutePath() + " exists?" + file.exists());

      if (!file.exists()) {
        dto.setContenido("No existe archivo");
      }
      else {
        byte[] content = FileUtils.readFileToByteArray(file);
        dto.setContenido(Base64.getEncoder().encodeToString(content));
      }
    }

    return dto;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public List<ProyectoArchivoDTO> findByProyectoId(Integer id) throws Exception {
    Optional<ProyectoDomain> optProj = this.proyectoRepository.findById(id);
    if (!optProj.isPresent()) {
      throw new FailledValidationException("[proyecto.id] no se encuentra");
    }
    else if (!optProj.get().getEstado().booleanValue()) {
      throw new FailledValidationException("[proyecto] eliminado anteriormente");
    }

    List<ProyectoArchivoDomain> listDomain = this.proyectoArchivoRepository.findByProyecto(optProj.get());
    List<ProyectoArchivoDTO> listDTO = ProyectoArchivoAssembler.buildDtoDomainCollection(listDomain);

    return listDTO;
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public ProyectoArchivoDTO storeBase64(ProyectoArchivoDTO dto) throws Exception {
    if (dto == null) {
      throw new FailledValidationException("datos son obligatorios");
    }
    else if (dto.getNombre() == null || dto.getNombre().isEmpty()) {
      throw new FailledValidationException("[titulo] es obligatorio");
    }
    else if (dto.getRuta() == null || dto.getRuta().isEmpty()) {
      throw new FailledValidationException("[nombre] es obligatorio");
    }
    else if (dto.getContenido() == null || dto.getContenido().isEmpty()) {
      throw new FailledValidationException("[contenido] es obligatorio");
    }
    else if (dto.getProyecto() == null) {
      throw new FailledValidationException("[proyecto] es obligatorio");
    }
    else if (dto.getProyecto().getId() == null) {
      throw new FailledValidationException("[proyecto.id] es obligatorio");
    }

    Optional<ProyectoDomain> optProj = this.proyectoRepository.findById(dto.getProyecto().getId());
    if (!optProj.isPresent()) {
      throw new FailledValidationException("[proyecto.id] no se encuentra");
    }
    else if (!optProj.get().getEstado().booleanValue()) {
      throw new FailledValidationException("[proyecto] eliminado anteriormente");
    }
    ProyectoDomain proyectoDomain = optProj.get();

    List<ProyectoArchivoDomain> list = this.proyectoArchivoRepository.findByProyectoAndNombre(proyectoDomain,
                                                                                              dto.getNombre().toUpperCase());
    if (list != null && list.size() > 0) {
      throw new FailledValidationException("[titulo] ya se encuentra registrado para el proyecto");
    }

    list = this.proyectoArchivoRepository.findByProyectoAndRuta(proyectoDomain,
                                                                dto.getRuta());
    if (list != null && list.size() > 0) {
      throw new FailledValidationException("[nombre] ya se encuentra registrado para el proyecto");
    }

    ProyectoArchivoDomain domain = new ProyectoArchivoDomain();
    domain.setProyecto(proyectoDomain);
    domain.setNombre(dto.getNombre().toUpperCase());
    domain.setRuta(dto.getRuta());
    domain.setCreatedAt(new Date());
    domain.setUpdatedAt(null);
    domain = this.proyectoArchivoRepository.save(domain);

    String folderPath = this.folder + "/Proj" + NumberUtil.format("000000", dto.getProyecto().getId());
    File folder = new File(folderPath);
    logger.info(" > Folder: " + folder.getAbsolutePath() + " exists?" + folder.exists());
    if (!folder.exists()) {
      FileUtils.forceMkdir(folder);
      logger.info(" > Folder created: " + folder.getAbsolutePath());
    }

    File file = new File(folderPath + "/" + NumberUtil.format("000000", domain.getId()) + ".bin");
    byte[] decodedBytes = Base64.getDecoder().decode(dto.getContenido());
    FileUtils.writeByteArrayToFile(file, decodedBytes);
    logger.info(" > File: " + dto.getRuta());
    logger.info(" > File save: " + file.getAbsolutePath());

    return ProyectoArchivoAssembler.buildDtoDomain(domain);
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public ProyectoArchivoDTO storeStream(Map<String, String> headers, FileModel fileModel) throws Exception {
    if (headers.size() == 0) {
      throw new FailledValidationException("headers son obligatorios");
    }
    else if (!headers.containsKey("titulo") || headers.get("titulo").isEmpty()) {
      throw new FailledValidationException("header [titulo] es obligatorio");
    }
    else if (!headers.containsKey("proyectoid") || headers.get("proyectoid").isEmpty()) {
      throw new FailledValidationException("header [proyectoid] es obligatorio");
    }
    else if (!NumberUtil.validate(headers.get("proyectoid"))) {
      throw new FailledValidationException("header [proyectoid] debe ser numerico");
    }
    else if (fileModel == null || fileModel.getFile() == null) {
      throw new FailledValidationException("fileModel es obligatorio");
    }

    ProyectoArchivoDTO dto = new ProyectoArchivoDTO();
    dto.setNombre(headers.get("titulo"));
    dto.setRuta(fileModel.getFile().getOriginalFilename());
    dto.setProyecto(new ProyectoDTO());
    dto.getProyecto().setId(new Integer(headers.get("proyectoid")));

    Optional<ProyectoDomain> optProj = this.proyectoRepository.findById(dto.getProyecto().getId());
    if (!optProj.isPresent()) {
      throw new FailledValidationException("[proyectoid] no se encuentra");
    }
    else if (!optProj.get().getEstado().booleanValue()) {
      throw new FailledValidationException("[proyecto] eliminado anteriormente");
    }
    ProyectoDomain proyectoDomain = optProj.get();

    List<ProyectoArchivoDomain> list = this.proyectoArchivoRepository.findByProyectoAndNombre(proyectoDomain,
                                                                                              dto.getNombre().toUpperCase());
    if (list != null && list.size() > 0) {
      throw new FailledValidationException("[titulo] ya se encuentra registrado para el proyecto");
    }

    list = this.proyectoArchivoRepository.findByProyectoAndRuta(proyectoDomain,
                                                                dto.getRuta());
    if (list != null && list.size() > 0) {
      throw new FailledValidationException("[nombre] ya se encuentra registrado para el proyecto");
    }

    ProyectoArchivoDomain domain = new ProyectoArchivoDomain();
    domain.setProyecto(proyectoDomain);
    domain.setNombre(dto.getNombre().toUpperCase());
    domain.setRuta(dto.getRuta());
    domain.setCreatedAt(new Date());
    domain.setUpdatedAt(null);
    domain = this.proyectoArchivoRepository.save(domain);

    String folderPath = this.folder + "/Proj" + NumberUtil.format("000000", dto.getProyecto().getId());
    File folder = new File(folderPath);
    logger.info(" > Folder: " + folder.getAbsolutePath() + " exists?" + folder.exists());
    if (!folder.exists()) {
      FileUtils.forceMkdir(folder);
      logger.info(" > Folder created: " + folder.getAbsolutePath());
    }

    File file = new File(folderPath + "/" + NumberUtil.format("000000", domain.getId()) + ".bin");
    FileUtils.writeByteArrayToFile(file, fileModel.getFile().getBytes());
    logger.info(" > File: " + dto.getRuta());
    logger.info(" > File save: " + file.getAbsolutePath());

    return ProyectoArchivoAssembler.buildDtoDomain(domain);
  }

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public ProyectoArchivoDTO delete(Integer id) throws Exception {
    ProyectoArchivoDomain domain = null;
    Optional<ProyectoArchivoDomain> optArch = this.proyectoArchivoRepository.findById(id);

    if (optArch.isPresent()) {
      domain = optArch.get();

      String folderPath = this.folder + "/Proj" + NumberUtil.format("000000", domain.getProyecto().getId());
      File file = new File(folderPath + "/" + NumberUtil.format("000000", domain.getId()) + ".bin");
      logger.info(" > File: " + domain.getRuta());
      logger.info(" > File: " + file.getAbsolutePath() + " exists?" + file.exists());

      if (file.exists()) {
        file.delete();
      }

      this.proyectoArchivoRepository.delete(domain);
    }

    return ProyectoArchivoAssembler.buildDtoDomain(domain);
  }
}
