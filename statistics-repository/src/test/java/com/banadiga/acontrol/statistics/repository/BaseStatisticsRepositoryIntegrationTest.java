package com.banadiga.acontrol.statistics.repository;

import com.banadiga.acontrol.core.DefaultRepository;
import com.banadiga.acontrol.test.IntegrationTestContext;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTestContext
public class BaseStatisticsRepositoryIntegrationTest extends BaseRedisRepositoryIntegrationTest<String, Long> {

  private static final String KEY = "Statistics:key";
  private static final byte[] KEY_PATTERN = "Statistics:*".getBytes();
  private static final Long VALUE = 123L;

  @DefaultRepository
  private BaseStatisticsRepository baseStatisticsRepository;

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
  protected Long getValue() {
    return VALUE;
  }

  @Override
  protected Repository<String, Long> getRepository() {
    return baseStatisticsRepository;
  }
}