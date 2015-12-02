package com.banadiga.acontrol.service;

import com.banadiga.acontrol.service.module.Record;

public interface DataSourceStorage {
  void appent(Record record);
  int count();
}
