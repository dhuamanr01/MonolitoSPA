package com.monolito365.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monolito365.common.constants.Constants;
import com.monolito365.common.constants.Constants.Security;
import com.monolito365.utilities.DateUtil;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
  private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
    super.setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    logger.info(Constants.Logger.Method.Initialize);
    logger.info("----------------------------------------------------------------------");

    logger.info(" > HTTP Method   : " + request.getMethod());
    logger.info(" > ContextPath   : " + request.getContextPath());
    logger.info(" > ServletPath   : " + request.getServletPath());
    logger.info(" > QueryString   : " + request.getQueryString());
    logger.info(" > RequestURI    : " + request.getRequestURI());
    logger.info(" > RequestURL    : " + request.getRequestURL());
    logger.info(" > AuthType      : " + request.getAuthType());
    logger.info(" > LocalAddr     : " + request.getLocalAddr());
    logger.info(" > LocalName     : " + request.getLocalName());
    logger.info(" > LocalPort     : " + request.getLocalPort());
    logger.info(" > RemoteAddr    : " + request.getRemoteAddr());
    logger.info(" > RemteHost     : " + request.getRemoteHost());
    logger.info(" > RemotePort    : " + request.getRemotePort());
    logger.info(" > ContentType   : " + request.getContentType());
    logger.info(" > ContentLength : " + request.getContentLength());

    try {
      String authContent = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
      logger.info(" > Content       : " + authContent);

      AuthenticationRequest credenciales = new ObjectMapper().readValue(authContent, AuthenticationRequest.class);
      logger.info(" > Credenciales: " + credenciales);
      response.addHeader("access-control-expose-headers", Security.Headers.Authorization + ", " +
                                                          Security.Headers.AuthorizationExpirationSeconds);
      logger.info(" > " + Security.Headers.Authorization + " : Add response header [access-control-expose-headers]");
      logger.info(" > " + Security.Headers.AuthorizationExpirationSeconds + " : Add response header [access-control-expose-headers]");

      Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                                                         credenciales.getUser(),
                                                                         credenciales.getPassword(),
                                                                         new ArrayList<>()
                                                                        )
                                                                       );
      logger.info(Constants.Logger.Method.Finalize);
      return authenticate;
    }
    catch (IOException e) {
      logger.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication auth) throws IOException, ServletException {
    logger.info(Constants.Logger.Method.Initialize);
    logger.info(Constants.Logger.Method.Initialize);

    Date expiration = new Date(System.currentTimeMillis() + Security.Token.ExpirationTime * 1000);
    String token = TokenProvider.generateToken(auth, expiration);

    response.addHeader(Security.Headers.Authorization, Security.Token.BearerPrefix + " " + token);
    response.addHeader(Security.Headers.AuthorizationExpirationSeconds, Long.toString(Security.Token.ExpirationTime));

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String resOut = "{\"user\" : \"" + auth.getName() + "\", " +
                     "\"token\" : \"" + Security.Token.BearerPrefix + " " + token + "\", " +
                     "\"expire_seconds\" : " + Long.toString(Security.Token.ExpirationTime) + "," +
                     "\"expire_date\" : \"" + DateUtil.format(Constants.Format.DateTime.DateTime, expiration) + "\"}";

    PrintWriter out = response.getWriter();
    out.print(resOut);
    out.flush();

    logger.info(" > response: " + resOut);
    logger.info(Constants.Logger.Method.Finalize);
  }
}
