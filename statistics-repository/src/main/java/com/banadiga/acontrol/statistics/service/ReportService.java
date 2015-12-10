package com.banadiga.acontrol.statistics.service;

import com.banadiga.acontrol.statistics.module.Report;

import java.util.Collection;

public interface ReportService {

  Collection<String> findNames();

  Report findByName(String name);

  void create(Report report);

  void deleteAll();
}
