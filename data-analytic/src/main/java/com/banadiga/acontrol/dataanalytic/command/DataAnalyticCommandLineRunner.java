package com.banadiga.acontrol.dataimport.command;

import com.banadiga.acontrol.module.Record;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataAnalyticCommandLineRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    log.info("DataAnalyticCommandLineRunner Record: {}", Record.builder().id(UUID.randomUUID()).build());
  }
}