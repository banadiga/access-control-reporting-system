package com.banadiga.acontrol.dataimport.command;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.dataimport.service.ImportService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataImportCommandLineRunner implements CommandLineRunner {

  @Value("${import.source}")
  private String source;

  @Value("${import.destination}")
  private String destination;

  private ImportService importService;

  @DefaultService
  public DataImportCommandLineRunner(ImportService importService) {
    this.importService = importService;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Import started");

    importService.importdata(source, destination);

    log.info("Import finished");
  }
}