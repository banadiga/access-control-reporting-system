package com.banadiga.acontrol.engine.module;


import java.util.Collection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralWorkingTime {

  private Collection<OneA> data;

  @Data
  @Builder
  public static class OneA {
    private String key;
    private long val;
  }
}
