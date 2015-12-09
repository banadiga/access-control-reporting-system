package com.banadiga.acontrol.statistics.repository;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.statistics.module.Statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@DefaultService
public class RedisStatisticsRepository implements StatisticsRepository {

  private static final String STATISTICS_PREFIX = "Statistics:";
  private static final String COUNT_KEY = "Count";

  private RedisTemplate<String, Long> redisTemplate;

  @Autowired
  public RedisStatisticsRepository(RedisTemplate<String, Long> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public Statistics get() {
    Long count = redisTemplate.opsForValue().get(STATISTICS_PREFIX + COUNT_KEY);
    return Statistics
        .builder()
        .countOfRecords(count == null ? 0 : count.longValue())
        .build();
  }

  @Override
  public void count(long count) {
    redisTemplate.opsForValue().set(STATISTICS_PREFIX + COUNT_KEY, count);
  }

  @Override
  public void delete() {
    redisTemplate.delete(STATISTICS_PREFIX + COUNT_KEY);
  }
}
