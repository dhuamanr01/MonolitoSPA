package com.monolito365.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
  private static Logger logger = LoggerFactory.getLogger(BaseController.class);

  protected HttpServletRequest httpServletRequest;

  protected HttpServletResponse httpServletResponse;

  public BaseController() {
    super();
  }

  protected Map<String, String> extractHeaders() {
    Map<String, String> headers = new HashMap<String, String>();

    if (this.httpServletRequest.getHeaderNames() != null){
      Enumeration<String> headerNames = this.httpServletRequest.getHeaderNames();

      while (headerNames.hasMoreElements()) {
        String header = headerNames.nextElement();
        logger.info("  > " + header + " = " + this.httpServletRequest.getHeader(header));

        if (header.startsWith("req.")) {
          headers.put(header.replace("req.", ""), this.httpServletRequest.getHeader(header));
        }
      }
    }

    return headers;
  }

  public void finalize(){}

}
