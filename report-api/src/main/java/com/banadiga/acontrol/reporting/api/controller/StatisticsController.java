package com.banadiga.acontrol.reporting.api.controller;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.statistics.module.Statistics;
import com.banadiga.acontrol.statistics.repository.StatisticsRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class StatisticsController {

  private final StatisticsRepository statisticsRepository;

  @DefaultService
  public StatisticsController(StatisticsRepository statisticsRepository) {
    this.statisticsRepository = statisticsRepository;
  }

  @RequestMapping(value = "/statistics", method = RequestMethod.GET)
  @ResponseBody
  public Statistics statistics() {
    return statisticsRepository.getStatistics();
  }
}
