package com.monolito365.service;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.monolito365.dto.ProyectoArchivoDTO;
import com.monolito365.dto.ClienteDTO;
import com.monolito365.dto.ProyectoDTO;
import com.monolito365.dto.ResetTokenDTO;
import com.monolito365.dto.UsuarioDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Ignore
public class UsuarioServiceTest {
  private static Logger logger = LoggerFactory.getLogger(UsuarioServiceTest.class);

  @Autowired(required=true)
  private UsuarioService usuarioService;

  public UsuarioServiceTest() {}

  @BeforeClass
  public static void setUpClass() {
    logger.info(">> @BeforeClass");
  }

  @Before
  public void setUp() {
    logger.info(" >> @Before");
  }

  @Test
  @Order(1)
  public void testFindAll() {
    logger.info("  >> @testFindAll");

    try {
      List<UsuarioDTO> list = this.usuarioService.findAll();

      for (UsuarioDTO dto : list) {
        logger.info("   >> " + dto);
      }
    }
    catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }

    logger.info("  << @testFindAll");
  }

  @Test
  @Order(2)
  public void testFindById() {
    logger.info("  >> @testFindById");

    try {
     // UsuarioDTO dto = this.usuarioService.findById(new Integer(3), true);
      UsuarioDTO dto = this.usuarioService.findByUserName("mportilla2020", true);
      logger.info("   >> " + dto);

      for (ResetTokenDTO tok : dto.getTokens()) {
        logger.info("    >> " + tok);
      }

      for (ProyectoDTO proj : dto.getProyectos()) {
        logger.info("    >> " + proj);

        for (ProyectoArchivoDTO arch : proj.getArchivos()) {
          logger.info("     >> " + arch);
        }
      }

      for (ClienteDTO cli : dto.getClientes()) {
        logger.info("    >> " + cli);
      }
    }
    catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }

    logger.info("  << @testFindById");
  }

  @Test
  @Order(3)
  public void testFindAllPage() {
    logger.info("  >> @testFindAllPage");

    try {
      Pageable pageable = PageRequest.of(1 - 1, 3);
      Page<UsuarioDTO> page = this.usuarioService.findAll(pageable);
      logger.info("   >> " + page);

      for (UsuarioDTO dto : page) {
        logger.info("   >> " + dto);
      }

      pageable = PageRequest.of(2 - 1, 3);
      page = this.usuarioService.findAll(pageable);
      logger.info("   >> " + page);

      for (UsuarioDTO dto : page) {
        logger.info("   >> " + dto);
      }
    }
    catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }

    logger.info("  << @testFindAllPage");
  }

  @After
  public void tearDown() {
    logger.info(" << @Before");
  }

  @AfterClass
  public static void tearDownClass() {
    logger.info("<< @BeforeClass");
  }
}
