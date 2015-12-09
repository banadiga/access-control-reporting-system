package com.banadiga.acontrol.dataanalytic.command;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.dataanalytic.service.ReportConverterService;
import com.banadiga.acontrol.service.ReportStorage;
import com.banadiga.acontrol.service.module.GeneralWorkingTime;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.repository.ReportRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GeneralWorkingTimeReportCommandLineRunner implements CommandLineRunner {

  private final ReportStorage reportStorage;
  private final ReportRepository reportRepository;
  private final ReportConverterService reportConverter;

  @DefaultService
  public GeneralWorkingTimeReportCommandLineRunner(ReportStorage reportStorage, ReportRepository statisticsRepository, ReportConverterService reportConverter) {
    this.reportStorage = reportStorage;
    this.reportRepository = statisticsRepository;
    this.reportConverter = reportConverter;
  }

  @Override
  public void run(String... args) throws Exception {
    GeneralWorkingTime generalWorkingTime = reportStorage.createGeneralWorkingTime();
    log.info("General working time: {}", generalWorkingTime);

    Report report = reportConverter.converter(generalWorkingTime);
    log.info("Report {}: {}", report.getKey(), report);

    reportRepository.create(report);
  }
}
