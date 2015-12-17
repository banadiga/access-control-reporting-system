package com.banadiga.acontrol.statistics.service;

import com.banadiga.acontrol.core.DefaultRepository;
import com.banadiga.acontrol.core.DefaultService;
import com.banadiga.acontrol.statistics.exception.ResourceNotFoundException;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.repository.ReportRepository;

import java.util.Collection;
import java.util.Optional;

@DefaultService
public class BaseReportService implements ReportService {
  private final static String REPORT_PREFIX = "Reposrts:";
  private final static String REPORTS_PREFIX = REPORT_PREFIX + "*";

  private ReportRepository reportRepository;

  @DefaultRepository
  public BaseReportService(ReportRepository reportRepository) {
    this.reportRepository = reportRepository;
  }

  @Override
  public Collection<String> findNames() {
    return reportRepository.keys(REPORTS_PREFIX.getBytes());
  }

  @Override
  public Report findByName(String name) {
    Optional<Report> report = reportRepository.get(REPORT_PREFIX + name);
    report.orElseThrow(() -> new ResourceNotFoundException("Repoert with name " + name + " not found"));
    return report.get();
  }

  @Override
  public void create(Report report) {
    reportRepository.set(REPORT_PREFIX + report.getKey(), report);
  }

  @Override
  public void deleteAll() {
    reportRepository.deleteAll();
  }
}
