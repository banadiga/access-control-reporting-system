package com.banadiga.acontrol.service;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.service.module.GeneralWorkingTime;

@DefaultService
public class RendomReportStorage implements ReportStorage {
  @Override
  public GeneralWorkingTime createGeneralWorkingTime() {
    return GeneralWorkingTime.builder().build();
  }
}
