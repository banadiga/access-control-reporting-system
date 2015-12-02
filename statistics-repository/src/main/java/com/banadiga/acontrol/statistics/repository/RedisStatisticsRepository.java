package com.banadiga.acontrol.statistics.repository;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.statistics.module.Statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@DefaultService
public class RedisStatisticsRepository implements StatisticsRepository {

  private static final String COUNT_KEY = "Statistics:Count";

  private RedisTemplate<String, Long> redisTemplate;

  @Autowired
  public RedisStatisticsRepository(RedisTemplate<String, Long> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public Statistics getStatistics() {
    return Statistics
        .builder()
        .countOfrecords(redisTemplate.opsForValue().get(COUNT_KEY))
        .build();
  }

  @Override
  public void cout(long count) {
    redisTemplate.opsForValue().set(COUNT_KEY, count);
  }
}
