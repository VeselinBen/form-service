package com.example.formservice.service;

import com.example.formservice.model.Statistics;

import java.util.List;
import java.util.UUID;

public interface StatisticsService {
    Statistics createStatistics(Statistics statistics);
    Statistics getStatisticsById(UUID id);
    List<Statistics> getAllStatistics();
    Statistics updateStatistics(UUID id, Statistics statistics);
    void deleteStatistics(UUID id);
}
