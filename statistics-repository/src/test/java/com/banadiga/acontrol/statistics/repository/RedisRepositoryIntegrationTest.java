package com.banadiga.acontrol.statistics.repository;

import com.banadiga.acontrol.DefaultRepository;
import com.banadiga.acontrol.statistics.module.TestReport;
import com.banadiga.acontrol.test.IntegrationTestContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTestContext
public class RedisRepositoryIntegrationTest {

  @DefaultRepository
  private BaseReportRepository baseReportRepository;

  @DefaultRepository
  private BaseStatisticsRepository baseStatisticsRepository;

  @Test
  public void crossDependency() {
    baseReportRepository.deleteAll();
    baseStatisticsRepository.deleteAll();

    baseReportRepository.set("test:a", TestReport.builder().build());
    baseStatisticsRepository.set("test:b", 100L);

    Collection<String> keys1 = baseReportRepository.keys("test:*".getBytes());
    Collection<String> keys2 = baseReportRepository.keys("test:*".getBytes());

    assertThat(keys1, equalTo(keys2));
  }
}
