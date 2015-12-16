package com.banadiga.acontrol.dataimport.module;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event implements Serializable {
  private String id;
  private String user;
  private Timestamp start;
  private Timestamp end;
}
