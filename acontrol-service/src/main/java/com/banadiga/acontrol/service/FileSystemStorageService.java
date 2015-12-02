package com.banadiga.acontrol.service;

import com.banadiga.acontrol.DefaultService;

import java.io.File;

@DefaultService
public class FileSystemStorageService extends BaseStorageService implements StorageService {

  @Override
  public File getFile(String path, String name) {
    return getParquetFile(path,name);
  }
}
