package com.banadiga.acontrol.dataimport.service;

import com.banadiga.acontrol.DefaultService;
import com.banadiga.acontrol.dataimport.module.Event;
import com.banadiga.acontrol.engine.service.DateTimeService;

import java.io.Serializable;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DefaultService
public class TextVisitConverterService implements VisitConverterService, Serializable {

  private DateTimeService dateTimeService;

  @DefaultService
  public TextVisitConverterService(DateTimeService dateTimeService) {
    this.dateTimeService = dateTimeService;
  }

  @Override
  public Event converter(Object object) {
    String line = (String) object;
    String[] parts = line.split(",");

    Event event = Event.builder()
        .id(UUID.fromString(parts[0]).toString())
        .user(UUID.fromString(parts[1]).toString())
        .start(dateTimeService.getTimestamp(parts[2]))
        .end(dateTimeService.getTimestamp(parts[3]))
        .build();

    log.info("Convert {} -> {}", object, event);
    return event;
  }
}
