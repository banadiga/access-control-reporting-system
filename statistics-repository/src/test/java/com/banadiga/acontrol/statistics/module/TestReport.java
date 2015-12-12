package com.banadiga.acontrol.statistics.module;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestReport implements Report {

  @Override
  public String getReportName() {
    return "TestReport";
  }
}
