package com.banadiga.acontrol.reporting.api.controller;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.statistics.module.Statistics;
import com.banadiga.acontrol.statistics.service.StatisticsService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = StatisticsController.STATISTICS_PATH)
public class StatisticsController {
  public static final String STATISTICS_PATH = "/statistics/";

  private final StatisticsService statisticsService;

  @DefaultService
  public StatisticsController(StatisticsService statisticsService) {
    this.statisticsService = statisticsService;
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Statistics statistics() {
    Statistics statistics = statisticsService.get();
    log.info("Retrieve statistics: {}", statistics);
    return statistics;
  }
}
