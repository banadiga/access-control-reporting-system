package com.banadiga.acontrol.statistics.repository;

import com.banadiga.acontrol.DefaultRepository;
import com.banadiga.acontrol.statistics.module.Report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@DefaultRepository
public class BaseReportRepository extends BaseRedisRepository<String, Report> implements ReportRepository {

  @Autowired
  public BaseReportRepository(RedisTemplate<String, Report> redisTemplate) {
    super(redisTemplate);
  }
}
