package com.banadiga.acontrol.reporting.api.controller;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.statistics.module.Report;
import com.banadiga.acontrol.statistics.repository.ReportRepository;

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

@RestController
@Slf4j
@RequestMapping(value = "/reports")
public class ReportController {

  private final ReportRepository reportRepository;

  @DefaultService
  public ReportController(ReportRepository reportRepository) {
    this.reportRepository = reportRepository;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Collection<String> reports() {
    Collection<String> names = reportRepository.findNames();
    log.info("Retrieve names: {}", names);
    return names;
  }

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Report report(@PathVariable @NotNull String name) {
    Report report = reportRepository.findByName(name);
    log.info("Retrieve report by mame {}: {}", name, report);
    return report;
  }
}
