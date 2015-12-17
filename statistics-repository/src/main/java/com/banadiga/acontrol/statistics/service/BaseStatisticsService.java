package com.banadiga.acontrol.statistics.service;

import com.banadiga.acontrol.core.DefaultRepository;
import com.banadiga.acontrol.core.DefaultService;
import com.banadiga.acontrol.statistics.module.Statistics;
import com.banadiga.acontrol.statistics.repository.StatisticsRepository;

import java.util.Optional;

@DefaultService
public class BaseStatisticsService implements StatisticsService {

  private static final String STATISTICS_PREFIX = "Statistics:";
  private static final String COUNT_KEY = "Count";

  private StatisticsRepository statisticsRepository;

  @DefaultRepository
  public BaseStatisticsService(StatisticsRepository statisticsRepository) {
    this.statisticsRepository = statisticsRepository;
  }

  @Override
  public Statistics get() {
    Optional<Long> count = statisticsRepository.get(STATISTICS_PREFIX + COUNT_KEY);
    return Statistics
        .builder()
        .countOfRecords(count.orElse(0L))
        .build();
  }

  @Override
  public void count(long count) {
    statisticsRepository.set(STATISTICS_PREFIX + COUNT_KEY, count);
  }

  @Override
  public void delete() {
    statisticsRepository.deleteAll();
  }
}
