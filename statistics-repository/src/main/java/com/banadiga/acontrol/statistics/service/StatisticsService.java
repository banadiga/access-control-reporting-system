package com.banadiga.acontrol.statistics.service;

import com.banadiga.acontrol.statistics.module.Statistics;

public interface StatisticsService {

  Statistics get();

  void count(long count);

  void delete();
}
