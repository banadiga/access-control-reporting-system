package com.banadiga.acontrol.dataanalytic.service;

import com.banadiga.acontrol.service.module.GeneralWorkingTime;
import com.banadiga.acontrol.statistics.module.Report;

import org.springframework.stereotype.Service;

@Service
public class DefaultReportConverterService implements ReportConverterService {

  @Override
  public Report converter(GeneralWorkingTime generalWorkingTime) {
    return null;
  }
}
