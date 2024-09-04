package com.example.formservice.service.impl;

import com.example.formservice.handler.error.exception.ResourceNotFoundException;
import com.example.formservice.model.Statistics;
import com.example.formservice.repository.StatisticsRepository;
import com.example.formservice.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Override
    public Statistics createStatistics(Statistics statistics) {
        try {
            return statisticsRepository.save(statistics);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to create statistics. Possible data integrity violation.");
        }
    }

    @Override
    public Statistics getStatisticsById(UUID id) {
        return statisticsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Statistics not found with id: " + id));
    }

    @Override
    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }

    @Override
    public Statistics updateStatistics(UUID id, Statistics statistics) {
        if (!statisticsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Statistics not found with id: " + id);
        }
        statistics.setId(id);
        try {
            return statisticsRepository.save(statistics);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to update statistics. Possible data integrity violation.");
        }
    }

    @Override
    public void deleteStatistics(UUID id) {
        if (!statisticsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Statistics not found with id: " + id);
        }
        try {
            statisticsRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Failed to delete statistics. Possible data integrity violation.");
        }
    }
}
