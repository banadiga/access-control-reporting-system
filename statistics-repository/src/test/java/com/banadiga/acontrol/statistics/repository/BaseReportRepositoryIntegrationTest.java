package com.banadiga.acontrol.statistics.repository;

import com.banadiga.acontrol.DefaultRepository;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.module.TestReport;
import com.banadiga.acontrol.test.IntegrationTestContext;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTestContext
public class BaseReportRepositoryIntegrationTest extends BaseRedisRepositoryIntegrationTest<String, Report> {
  private static final String KEY = "keys:key";
  private static final byte[] KEY_PATTERN = "keys:*".getBytes();
  private static final Report VALUE = TestReport.builder().build();

  @DefaultRepository
  private BaseReportRepository baseReportRepository;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Override
  protected String getKey() {
    return KEY;
  }

  @Override
  protected byte[] getKeyPattern() {
    return KEY_PATTERN;
  }

  @Override
  protected Report getValue() {
    return VALUE;
  }

  @Override
  protected Repository<String, Report> getRepository() {
    return baseReportRepository;
  }
}