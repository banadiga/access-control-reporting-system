package com.banadiga.acontrol.service;

import java.io.File;

public abstract class BaseStorageService {

  public File getParquetFile(String path, String name) {
    return new File(path, name + ".parquet");
  }
}
