package com.banadiga.acontrol.reporting.api.controller;

import com.banadiga.acontrol.module.Record;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {

  @RequestMapping("/")
  public String index() {
    log.info("Record: {}", Record.builder().id(UUID.randomUUID()).build());
    return "Greetings from Spring Boot!";
  }

}
