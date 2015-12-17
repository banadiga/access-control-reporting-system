package com.banadiga.acontrol.dataanalytic.service;

import com.banadiga.acontrol.core.DefaultService;
import com.banadiga.acontrol.engine.module.GeneralWorkingTime;
import com.banadiga.acontrol.statistics.module.GeneralWorkingTimeReport;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.module.gwt.One;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DefaultService
public class DefaultReportConverterService implements ReportConverterService {

  @Override
  public Report converter(GeneralWorkingTime generalWorkingTime) {
    return GeneralWorkingTimeReport.builder()
        .id(UUID.randomUUID())
        .data(getRandomGeneralWorkingTimeReportData(generalWorkingTime.getData()))
        .build();
  }

  private Collection<One> getRandomGeneralWorkingTimeReportData(Collection<GeneralWorkingTime.OneA> dataA) {
    Collection<One> data = new ArrayList<>();
    dataA.stream().forEach(e -> {

      String key = e.getKey();
      String[] split = key.split("\\|");

      One one = One.builder()
          .startTime(split[0] + ":00")
          .endTime(split[1] + ":00")
          .amount(e.getVal() * 10)
          .build();

      log.info("Key : {}", e.getKey());
      log.info("Strings: {}, one {}", split, one);

      data.add(one);
    });
    return data;
  }
}
