package com.banadiga.acontrol.reporting.api.controller;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.reporting.api.Application;
import com.banadiga.acontrol.statistics.service.StatisticsService;
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
public class StatisticsControllerIntegrationTest {

  private static final int TEST_COUNT = 100;
  private static final String COUNT_OF_RECORDS_KEY = "countOfRecords";
  private static final String COUNT_OF_SKIPPED_RECORDS_KEY = "countOfSkippedRecords";

  @Value("${local.server.port}")
  private int port;

  @DefaultService
  private StatisticsService statisticsService;

  @Before
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  @Ignore
  public void emptyStatistics() {
    statisticsService.delete();
    given()
      .expect()
        .statusCode(equalTo(HttpStatus.SC_OK))
        .body(COUNT_OF_RECORDS_KEY, is(0))
        .body(COUNT_OF_SKIPPED_RECORDS_KEY, is(0))
      .when()
        .get(StatisticsController.STATISTICS_PATH);
  }

  @Test
  public void countOfRecords() {
    statisticsService.count(TEST_COUNT);
    given()
      .expect()
        .statusCode(equalTo(HttpStatus.SC_OK))
        .body(COUNT_OF_RECORDS_KEY, is(TEST_COUNT))
      .when()
        .get(StatisticsController.STATISTICS_PATH);
  }

  @Test
  public void countOfSkippedRecords() {
    given()
        .expect()
        .statusCode(equalTo(HttpStatus.SC_OK))
        .body(COUNT_OF_SKIPPED_RECORDS_KEY, is(0))
        .when()
        .get(StatisticsController.STATISTICS_PATH);
  }
}