package com.banadiga.acontrol.engine.service;

import org.apache.spark.SparkContext;

public interface SparkTask {
  SparkContext init();
}
