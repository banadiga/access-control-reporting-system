package com.banadiga.acontrol.dataanalytic.service;


import com.banadiga.acontrol.engine.module.GeneralWorkingTime;
import com.banadiga.acontrol.statistics.module.Report;

public interface ReportConverterService {

  Report converter(GeneralWorkingTime generalWorkingTime);
}
