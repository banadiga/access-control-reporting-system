package com.banadiga.acontrol.service;

import com.banadiga.acontrol.service.module.Record;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ParquetDataSourceStorage implements DataSourceStorage {
  @Override
  public void appent(Record record) {
  }

  @Override
  public int count() {
    return 0;
  }
}
