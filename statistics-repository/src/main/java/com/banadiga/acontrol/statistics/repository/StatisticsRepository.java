package com.banadiga.acontrol.statistics.repository;

import com.banadiga.acontrol.statistics.module.Statistics;

public interface StatisticsRepository {

  Statistics get();

  void count(long count);

  void delete();
}
