package com.banadiga.acontrol.statistics.service;

import com.banadiga.acontrol.statistics.module.Statistics;
import com.banadiga.acontrol.statistics.repository.StatisticsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BaseStatisticsServiceTest {

  private static final Long COUNT = 1L;
  private static final String COUNT_KEY = "Statistics:Count";

  private BaseStatisticsService statisticsService;

  @Mock
  private StatisticsRepository statisticsRepository;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    statisticsService = new BaseStatisticsService(statisticsRepository);
  }

  @Test
  public void findByName() {
    Mockito.when(statisticsRepository.get(COUNT_KEY)).thenReturn(Optional.of(COUNT));

    Statistics actual = statisticsService.get();

    Mockito.verify(statisticsRepository).get(COUNT_KEY);
    assertThat(actual.getCountOfRecords(), is(COUNT));
  }

  @Test
  public void canNotfindByName() {
    Mockito.when(statisticsRepository.get(COUNT_KEY)).thenReturn(Optional.ofNullable(null));

    Statistics actual = statisticsService.get();

    Mockito.verify(statisticsRepository).get(COUNT_KEY);
    assertThat(actual.getCountOfRecords(), is(0L));
  }

  @Test
  public void count() {
    Mockito.doNothing().when(statisticsRepository).set(COUNT_KEY, COUNT);

    statisticsService.count(COUNT);

    Mockito.verify(statisticsRepository).set(COUNT_KEY, COUNT);
  }

  @Test
  public void delete() {
    Mockito.doNothing().when(statisticsRepository).deleteAll();

    statisticsService.delete();

    Mockito.verify(statisticsRepository).deleteAll();
  }
}