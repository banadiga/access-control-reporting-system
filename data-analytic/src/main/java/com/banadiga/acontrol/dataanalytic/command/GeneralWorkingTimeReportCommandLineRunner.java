package com.banadiga.acontrol.dataanalytic.command;

import com.banadiga.acontrol.core.DefaultService;
import com.banadiga.acontrol.dataanalytic.service.ReportConverterService;
import com.banadiga.acontrol.engine.service.DataAnalyticService;
import com.banadiga.acontrol.engine.module.GeneralWorkingTime;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.service.ReportService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GeneralWorkingTimeReportCommandLineRunner implements CommandLineRunner {

  private final DataAnalyticService reportStorage;
  private final ReportService reportService;
  private final ReportConverterService reportConverter;

  @DefaultService
  public GeneralWorkingTimeReportCommandLineRunner(DataAnalyticService reportStorage, ReportService reportService, ReportConverterService reportConverter) {
    this.reportStorage = reportStorage;
    this.reportService = reportService;
    this.reportConverter = reportConverter;
  }

  @Override
  public void run(String... args) throws Exception {
    GeneralWorkingTime generalWorkingTime = reportStorage.getGeneralWorkingTime();
    log.info("General working time: {}", generalWorkingTime);

    Report report = reportConverter.converter(generalWorkingTime);
    log.info("Report {}: {}", report.getKey(), report);

    reportService.create(report);
  }
}
