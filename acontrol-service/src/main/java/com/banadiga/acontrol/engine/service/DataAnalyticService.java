package com.banadiga.acontrol.engine.service;

import com.banadiga.acontrol.engine.module.GeneralWorkingTime;

import java.io.IOException;

public interface DataAnalyticService {
  GeneralWorkingTime getGeneralWorkingTime() throws IOException;
}
