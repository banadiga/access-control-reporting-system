package com.banadiga.acontrol.dataimport.command;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.service.module.Record;
import com.banadiga.acontrol.service.DataSourceStorage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataImportCommandLineRunner implements CommandLineRunner {

  private DataSourceStorage dataSourceStorage;

  @DefaultService
  public DataImportCommandLineRunner(DataSourceStorage dataSourceStorage) {
    this.dataSourceStorage = dataSourceStorage;
  }

  @Override
  public void run(String... args) throws Exception {
    Record record = Record.builder().id(UUID.randomUUID()).build();
    log.info("DataImportCommandLineRunner Record: {}", record);
    dataSourceStorage.appent(record);
  }
}