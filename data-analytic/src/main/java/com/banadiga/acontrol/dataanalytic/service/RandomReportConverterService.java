package com.banadiga.acontrol.dataanalytic.service;


import com.banadiga.acontrol.engine.module.GeneralWorkingTime;
import com.banadiga.acontrol.statistics.module.GeneralWorkingTimeReport;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.module.gwt.One;

import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

@Service
public class RandomReportConverterService implements ReportConverterService {

  @Override
  public Report converter(GeneralWorkingTime generalWorkingTime) {
    return GeneralWorkingTimeReport.builder()
        .id(UUID.randomUUID())
        .data(getRandomGeneralWorkingTimeReportData())
        .build();
  }

  private Collection<One> getRandomGeneralWorkingTimeReportData() {
    final Random random = new Random();
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

    Collection<One> data = new ArrayList<>();
    for (long i = 8; i < 15; i++) {
      for (long j = 14; j < 22; j++) {
        Time time1 = new Time(i * 60 * 60 * 1000);
        Time time2 = new Time(Math.max(i, j) * 60 * 60 * 1000);
        data.add(One.builder()
            .startTime(simpleDateFormat.format(time1))
            .endTime(simpleDateFormat.format(time2))
            .amount(random.nextInt(40)).build());
      }
    }
    return data;
  }
}
