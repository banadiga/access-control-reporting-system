package com.banadiga.acontrol.statistics.repository;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.statistics.module.Report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@DefaultService
public class RedisReportRepository implements ReportRepository {
  private final static String PREFIX = "reposrts:";

  private RedisTemplate<String, Report> template;

  @Autowired
  public RedisReportRepository(RedisTemplate<String, Report> template) {
    this.template = template;
  }

  @Override
  public Collection<String> findNames() {
    Set<String> result = new HashSet<>();

    Iterator<byte[]> it = template.getConnectionFactory()
        .getConnection()
        .keys((PREFIX + "*").getBytes())
        .iterator();

    while (it.hasNext()) {
      byte[] data = it.next();
      result.add(new String(data, PREFIX.length(), data.length - PREFIX.length()));
    }

    return result;
  }

  @Override
  public Report findByName(String name) {
    return template.opsForValue().get(PREFIX + name);
  }

  @Override
  public void create(Report report) {
    template.opsForValue().set(PREFIX + report.getKey(), report);
  }
}
