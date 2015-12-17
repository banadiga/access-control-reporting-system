package com.banadiga.acontrol.engine.service;

import com.banadiga.acontrol.core.DefaultService;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DefaultService
public class ParquetDataSourceService extends AbstractSparkTask implements DataSourceService {
  @Override
  public long count() {
    log.info("Counting...");

    SparkContext sparkContext = init();
    Objects.requireNonNull(sparkContext, "sparkConf is required");

    SQLContext sqlContext = new SQLContext(sparkContext);
    DataFrame parquetFile = sqlContext.read().parquet("./test-data/parquet.out");

    return parquetFile.count();
  }
}
