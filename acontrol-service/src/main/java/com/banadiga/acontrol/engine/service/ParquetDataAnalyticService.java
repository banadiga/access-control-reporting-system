package com.banadiga.acontrol.engine.service;

import com.banadiga.acontrol.core.DefaultService;
import com.banadiga.acontrol.engine.module.GeneralWorkingTime;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import scala.Tuple2;

@Slf4j
@DefaultService
public class ParquetDataAnalyticService extends AbstractSparkTask implements DataAnalyticService {

  @Override
  public GeneralWorkingTime getGeneralWorkingTime() throws IOException {
    log.info("Calculation GeneralWorkingTime...");

    SparkContext sparkContext = init();
    Objects.requireNonNull(sparkContext, "sparkConf is required");

    SQLContext sqlContext = new SQLContext(sparkContext);
    DataFrame parquetFile = sqlContext.read().parquet("./test-data/parquet.out");


    JavaPairRDD<String, Long> stringLongJavaPairRDD = parquetFile.toJavaRDD().mapToPair(new PairFunction<Row, String, Long>() {
      @Override
      public Tuple2<String, Long> call(Row row) throws Exception {
        Timestamp start = row.getAs("start");
        Timestamp end = row.getAs("end");
        return new Tuple2<String, Long>(start.getHours() + "|" + end.getHours(), 1L);
      }
    });

    JavaPairRDD<String, Long> stringLongJavaPairRDD1 = stringLongJavaPairRDD.reduceByKey(new Function2<Long, Long, Long>() {
      @Override
      public Long call(Long v1, Long v2) throws Exception {
        return v1 + v2;
      }
    });

    Collection<GeneralWorkingTime.OneA> data = new ArrayList<GeneralWorkingTime.OneA>(Math.round(stringLongJavaPairRDD1.count())); // FIXME

    stringLongJavaPairRDD1.toArray().stream().forEach(e -> data.add(
            GeneralWorkingTime.OneA.builder()
                .key(e._1())
                .val(e._2())
                .build()
        )
    );

    FileUtils.deleteDirectory(new File("./test-data/text.out"));
    stringLongJavaPairRDD1.saveAsTextFile("./test-data/text.out");

    return GeneralWorkingTime.builder().data(data).build();
  }
}
