package com.banadiga.acontrol.statistics.repository;

import com.banadiga.acontrol.DefaultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@DefaultRepository
public class BaseStatisticsRepository extends BaseRedisRepository<String, Long> implements StatisticsRepository {

  @Autowired
  public BaseStatisticsRepository(RedisTemplate<String, Long> redisTemplate) {
    super(redisTemplate);
  }
}
