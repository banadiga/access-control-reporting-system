package com.banadiga.acontrol.statistics.module;

import com.banadiga.acontrol.statistics.module.gwt.One;

import java.util.Collection;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralWorkingTimeReport implements Report {
  private static final String NAME = "GeneralWorkingTimeReport";

  private UUID id;

  private Collection<One> data;

  @Override
  public String getReportName() {
    return NAME;
  }
}
