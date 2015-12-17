package com.banadiga.acontrol.engine.service;

import com.banadiga.acontrol.core.DefaultService;

import org.joda.time.format.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

@DefaultService
public class JodaDateTimeService implements DateTimeService, Serializable {

  private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

  @Override
  public Timestamp getTimestamp(String string) {
    return new Timestamp(DateTimeFormat.forPattern(DATE_TIME_FORMATTER).parseDateTime(string).getMillis());
  }
}
