package com.banadiga.acontrol.statistics.module;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Statistics {
  private long countOfRecords;
  private long countOfSkippedRecords;
}
