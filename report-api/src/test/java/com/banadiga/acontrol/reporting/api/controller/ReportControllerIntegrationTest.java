package com.banadiga.acontrol.reporting.api.controller;

import com.banadiga.acontrol.core.DefaultService;
import com.banadiga.acontrol.reporting.api.ApiIntegrationTest;
import com.banadiga.acontrol.statistics.module.GeneralWorkingTimeReport;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.service.ReportService;
import com.jayway.restassured.RestAssured;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ApiIntegrationTest
public class ReportControllerIntegrationTest {

  @Value("${local.server.port}")
  private int port;

  @DefaultService
  private ReportService reportService;

  @Before
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  public void emptyStatistics() {
    reportService.deleteAll();
    given()
      .expect()
        .body("size()", is(0))
        .statusCode(equalTo(HttpStatus.SC_OK))
      .when()
        .get(ReportController.REPORT_PATH);
  }

  @Test
  public void report() {
    Report report = GeneralWorkingTimeReport.builder().build();
    reportService.create(report);
    given()
      .expect()
        .statusCode(equalTo(HttpStatus.SC_OK))
      .when()
        .get(ReportController.REPORT_PATH + report.getKey());
  }

  @Test
  public void notExistingReport() {
    reportService.deleteAll();
    given()
        .expect()
        .statusCode(equalTo(HttpStatus.SC_NOT_FOUND))
        .when()
        .get(ReportController.REPORT_PATH + "not-existing-report");
  }

  @Test
  public void reports() {
    reportService.deleteAll();
    reportService.create(GeneralWorkingTimeReport.builder().build());
    given()
      .expect()
        .body("size()", is(1))
        .statusCode(equalTo(HttpStatus.SC_OK))
      .when()
        .get(ReportController.REPORT_PATH);
  }
}