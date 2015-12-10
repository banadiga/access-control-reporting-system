package com.banadiga.acontrol.reporting.api.controller;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.reporting.api.Application;
import com.banadiga.acontrol.statistics.module.GeneralWorkingTimeReport;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.service.ReportService;
import com.jayway.restassured.RestAssured;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
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
  @Ignore
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
  @Ignore
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
    reportService.create(GeneralWorkingTimeReport.builder().build());
    given()
      .expect()
        .body("size()", is(1))
        .statusCode(equalTo(HttpStatus.SC_OK))
      .when()
        .get(ReportController.REPORT_PATH);
  }
}