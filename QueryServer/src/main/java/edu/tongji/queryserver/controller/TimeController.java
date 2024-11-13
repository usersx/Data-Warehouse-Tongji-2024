package edu.tongji.queryserver.controller;

import edu.tongji.queryserver.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/time")
public class TimeController {
    
    @Autowired
    private TimeService timeService;

    @GetMapping("/year")
    public ResponseEntity<List<Map<String, Object>>> getMovieCountByYear(
            @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(timeService.getMovieCountByYear(year));
    }

    @GetMapping("/month")
    public ResponseEntity<List<Map<String, Object>>> getMovieCountByMonth(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {
        return ResponseEntity.ok(timeService.getMovieCountByYearAndMonth(year, month));
    }

    @GetMapping("/quarter")
    public ResponseEntity<List<Map<String, Object>>> getMovieCountByQuarter(
            @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(timeService.getMovieCountByYearAndQuarter(year));
    }

    @GetMapping("/weekday")
    public ResponseEntity<List<Map<String, Object>>> getMovieCountByWeekday() {
        return ResponseEntity.ok(timeService.getMovieCountByWeekday());
    }
}
