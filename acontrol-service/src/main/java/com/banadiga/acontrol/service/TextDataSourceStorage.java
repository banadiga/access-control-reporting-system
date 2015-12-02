package com.banadiga.acontrol.service;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.service.module.Record;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DefaultService
public class TextDataSourceStorage implements DataSourceStorage {

  @Value("${datasource.path}")
  private String dataSourcePath;

  private StorageService storageService;

  @DefaultService
  public TextDataSourceStorage(StorageService storageService) {
    this.storageService = storageService;
  }

  @Override
  public void appent(Record record) {
    try {
      log.info("Appent Record: {}", record);

      File file = storageService.getFile(dataSourcePath, record.getId().toString());

      FileUtils.write(file, record.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int count() {
    return new File(dataSourcePath).list().length;
  }
}
