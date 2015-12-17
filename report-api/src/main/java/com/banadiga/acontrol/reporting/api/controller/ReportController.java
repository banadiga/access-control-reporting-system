package com.banadiga.acontrol.reporting.api.controller;

import com.banadiga.acontrol.core.DefaultService;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.service.ReportService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = ReportController.REPORT_PATH)
public class ReportController {
  public static final String REPORT_PATH = "/reports/";

  private final ReportService reportService;

  @DefaultService
  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Collection<String> reports() {
    Collection<String> names = reportService.findNames();
    log.info("Retrieve names: {}", names);
    return names;
  }

  @RequestMapping(value = "{name}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Report report(@PathVariable @NotNull String name) {
    Report report = reportService.findByName(name);
    log.info("Retrieve report by mame {}: {}", name, report);
    return report;
  }
}
