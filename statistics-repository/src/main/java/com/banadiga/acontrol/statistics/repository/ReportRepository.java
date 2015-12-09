package com.banadiga.acontrol.statistics.repository;

import com.banadiga.acontrol.statistics.module.Report;

import java.util.Collection;

public interface ReportRepository {

  Collection<String> findNames();

  Report findByName(String name);

  void create(Report report);
}
