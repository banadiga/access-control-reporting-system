package com.banadiga.acontrol.dataimport.service;

import com.banadiga.acontrol.core.DefaultService;
import com.banadiga.acontrol.dataimport.module.Event;
import com.banadiga.acontrol.engine.service.AbstractSparkTask;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DefaultService
public class TextFileImportService extends AbstractSparkTask implements ImportService, Serializable {

  private VisitConverterService visitConverterService;

  private Function visitConvertor = new Function<String, Event>() {
    public Event call(String line) throws Exception {
      return visitConverterService.converter(line);
    }
  };

  @DefaultService
  public TextFileImportService(VisitConverterService visitConverterService) {
    this.visitConverterService = visitConverterService;
  }

  @Override
  public void importdata(String source, String destination) throws IOException, InterruptedException {
    log.info("Import from {} to {}", source, destination);

    SparkContext sparkContext = init();
    Objects.requireNonNull(sparkContext, "SparkContext is required");

    RDD<String> peoples = sparkContext.textFile(source, 1);
    JavaRDD<Event> map = peoples.toJavaRDD().map(visitConvertor);

    SQLContext sqlContext = new SQLContext(sparkContext);
    DataFrame schemaPeople = sqlContext.createDataFrame(map, Event.class);

    FileUtils.deleteDirectory(new File(destination));

    schemaPeople.write().parquet(destination);
  }
}
