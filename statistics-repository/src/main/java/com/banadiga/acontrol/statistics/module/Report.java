package com.banadiga.acontrol.statistics.module;


import com.google.common.base.CaseFormat;

import java.io.Serializable;

public interface Report extends Serializable {

  String getReportName();

  default String getKey() {
    return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, getReportName());
  }
}
