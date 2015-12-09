package com.banadiga.acontrol.reporting.api.controller;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.statistics.module.Statistics;
import com.banadiga.acontrol.statistics.repository.StatisticsRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class StatisticsController {

  public static final String STATISTICS_PATH = "/statistics";

  private final StatisticsRepository statisticsRepository;

  @DefaultService
  public StatisticsController(StatisticsRepository statisticsRepository) {
    this.statisticsRepository = statisticsRepository;
  }

  @RequestMapping(value = STATISTICS_PATH, method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Statistics statistics() {
    Statistics statistics = statisticsRepository.get();
    log.info("Retrieve statistics: {}", statistics);
    return statistics;
  }
}
