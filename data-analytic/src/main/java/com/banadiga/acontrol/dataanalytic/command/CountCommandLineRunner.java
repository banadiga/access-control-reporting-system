package com.banadiga.acontrol.dataanalytic.command;

import com.banadiga.acontrol.core.DefaultService;
import com.banadiga.acontrol.engine.service.DataSourceService;
import com.banadiga.acontrol.statistics.service.StatisticsService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CountCommandLineRunner implements CommandLineRunner {

  private final DataSourceService dataSourceStorage;
  private final StatisticsService statisticsService;

  @DefaultService
  public CountCommandLineRunner(DataSourceService dataSourceStorage, StatisticsService statisticsService) {
    this.dataSourceStorage = dataSourceStorage;
    this.statisticsService = statisticsService;
  }

  @Override
  public void run(String... args) throws Exception {
    //long count = dataSourceStorage.count();
    //log.info("Count of records: {}", count);

    //statisticsService.count(count);
  }
}
