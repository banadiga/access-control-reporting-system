package com.banadiga.acontrol.statistics.module.gwt;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class One implements Serializable {
  private String startTime;
  private String endTime;
  private long amount;

}
