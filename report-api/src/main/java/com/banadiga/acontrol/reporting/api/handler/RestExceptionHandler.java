package com.banadiga.acontrol.reporting.api.handler;

import com.banadiga.acontrol.statistics.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public String exception(HttpServletRequest request, Exception ex) {
    log.error("Request {}, with exception", request, ex);
    return ex.getMessage();
  }

  @ExceptionHandler(value = {ResourceNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public String resourceNotFoundException(HttpServletRequest request, Exception ex) {
    return exception(request, ex);
  }
}
