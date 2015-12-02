package com.banadiga.acontrol.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class HdfsStorageService extends BaseStorageService implements StorageService {

  @Override
  public File getFile(String path, String name) {
    return null;
  }
}
