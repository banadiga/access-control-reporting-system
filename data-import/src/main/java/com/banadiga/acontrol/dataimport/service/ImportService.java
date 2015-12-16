package com.banadiga.acontrol.dataimport.service;

import com.banadiga.acontrol.engine.service.SparkTask;

import java.io.IOException;

public interface ImportService extends SparkTask {

  void importdata(String source, String destination) throws IOException, InterruptedException;
}
