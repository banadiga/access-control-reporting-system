package com.banadiga.acontrol.engine.service;


import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

import java.io.Serializable;

public abstract class AbstractSparkTask implements SparkTask, Serializable {

  public SparkContext init() {
    SparkConf javaWordCount = new SparkConf().setAppName("JavaWordCount").setMaster("local[2]");
    return new SparkContext(javaWordCount);
  }
}
