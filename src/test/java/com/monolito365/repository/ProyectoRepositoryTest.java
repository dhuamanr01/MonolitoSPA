package com.monolito365.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.monolito365.domain.ProyectoArchivoDomain;
import com.monolito365.domain.ProyectoDomain;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Ignore
public class ProyectoRepositoryTest {
  private static Logger logger = LoggerFactory.getLogger(ProyectoRepositoryTest.class);

  @Autowired(required=true)
  private ProyectoRepository proyectoRepository;

  public ProyectoRepositoryTest() {}

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
  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public void testFindAll() {
    logger.info("  >> @testFindAll");

    try {
      List<ProyectoDomain> list = this.proyectoRepository.findAll();

      for (ProyectoDomain dom : list) {
        logger.info("   >> " + dom);
      }
    }
    catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }

    logger.info("  << @testFindAll");
  }

  @Test
  @Order(2)
  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public void testFindById() {
    logger.info("  >> @testFindById");

    try {
      Optional<ProyectoDomain> opt = this.proyectoRepository.findById(new Integer(2));

      if (opt.isPresent()) {
        ProyectoDomain dom = opt.get();
        logger.info("   >> " + dom);

        for (ProyectoArchivoDomain arch : dom.getArchivos()) {
          logger.info("    >> " + arch);
        }
      }
    }
    catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }

    logger.info("  << @testFindById");
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
