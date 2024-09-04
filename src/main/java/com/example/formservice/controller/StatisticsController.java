package com.example.formservice.controller;

import com.example.formservice.handler.successful.SuccessResponseHandler;
import com.example.formservice.handler.successful.SuccessfulResponse;
import com.example.formservice.model.Statistics;
import com.example.formservice.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/forms/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final SuccessResponseHandler successResponseHandler;

    @PostMapping
    public ResponseEntity<SuccessfulResponse<Statistics>> createStatistics(@RequestBody Statistics statistics) {
        Statistics createdStatistics = statisticsService.createStatistics(statistics);
        return successResponseHandler.created(createdStatistics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Statistics>> getStatisticsById(@PathVariable UUID id) {
        Statistics statistics = statisticsService.getStatisticsById(id);
        return successResponseHandler.ok(statistics);
    }

    @GetMapping
    public ResponseEntity<SuccessfulResponse<List<Statistics>>> getAllStatistics() {
        List<Statistics> statistics = statisticsService.getAllStatistics();
        return successResponseHandler.ok(statistics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Statistics>> updateStatistics(@PathVariable UUID id, @RequestBody Statistics statistics) {
        Statistics updatedStatistics = statisticsService.updateStatistics(id, statistics);
        return successResponseHandler.ok(updatedStatistics);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Void>> deleteStatistics(@PathVariable UUID id) {
        statisticsService.deleteStatistics(id);
        return successResponseHandler.noContent();
    }
}
