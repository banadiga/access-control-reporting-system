package com.banadiga.acontrol.dataanalytic.command;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.service.DataSourceStorage;
import com.banadiga.acontrol.statistics.repository.StatisticsRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CountCommandLineRunner implements CommandLineRunner {

  private final DataSourceStorage dataSourceStorage;
  private final StatisticsRepository statisticsRepository;

  @DefaultService
  public CountCommandLineRunner(DataSourceStorage dataSourceStorage, StatisticsRepository statisticsRepository) {
    this.dataSourceStorage = dataSourceStorage;
    this.statisticsRepository = statisticsRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    int count = dataSourceStorage.count();
    log.info("Count of records: {}", count);

    statisticsRepository.count(count);
  }
}
