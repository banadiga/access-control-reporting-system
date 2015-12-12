package com.banadiga.acontrol.statistics.service;

import com.banadiga.acontrol.statistics.exception.ResourceNotFoundException;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.module.TestReport;
import com.banadiga.acontrol.statistics.repository.ReportRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BaseReportServiceTest {

  private final static String REPORT_PREFIX = "Reposrts:";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private BaseReportService reportService;

  @Mock
  private ReportRepository reportRepository;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    reportService = new BaseReportService(reportRepository);
  }

  @Test
  public void findByName() {
    Report report = TestReport.builder().build();

    Mockito.when(reportRepository.get(REPORT_PREFIX + report.getKey())).thenReturn(Optional.of(report));

    Report actual = reportService.findByName(report.getKey());

    Mockito.verify(reportRepository).get(REPORT_PREFIX + report.getKey());
    assertThat(actual, equalTo(report));
  }

  @Test
  public void canNotFindByName() {
    thrown.expect(ResourceNotFoundException.class);
    thrown.expectMessage(containsString("not found"));
    Report report = TestReport.builder().build();

    Mockito.when(reportRepository.get(REPORT_PREFIX + report.getKey())).thenReturn(Optional.ofNullable(null));

    try {
      reportService.findByName(report.getKey());
    } finally {
      Mockito.verify(reportRepository).get(REPORT_PREFIX + report.getKey());
    }
  }

  @Test
  public void create() {
    Report report = TestReport.builder().build();

    Mockito.doNothing().when(reportRepository).set(REPORT_PREFIX + report.getKey(), report);

    reportService.create(report);

    Mockito.verify(reportRepository).set(REPORT_PREFIX + report.getKey(), report);
  }

  @Test
  public void deleteAll() {
    Mockito.doNothing().when(reportRepository).deleteAll();

    reportService.deleteAll();

    Mockito.verify(reportRepository).deleteAll();
  }
}