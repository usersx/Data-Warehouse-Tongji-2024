package edu.tongji.queryserver.controller;

import edu.tongji.queryserver.service.TimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "时间查询接口", description = "提供基于时间维度的电影查询")
@RestController
@RequestMapping("/api/time")
public class TimeController {
    
    @Autowired
    private TimeService timeService;

    @Operation(summary = "按年份统计电影数量")
    @GetMapping("/year")
    public ResponseEntity<List<Map<String, Object>>> getMovieCountByYear(
            @RequestParam(required = false) Integer year) {
        return ResponseEntity.ok(timeService.getMovieCountByYear(year));
    }

    @Operation(summary = "按年月统计电影数量")
    @GetMapping("/month")
    public ResponseEntity<List<Map<String, Object>>> getMovieCountByMonth(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {
        return ResponseEntity.ok(timeService.getMovieCountByYearAndMonth(year, month));
    }

    @Operation(summary = "按季度统计电影数量")
    @GetMapping("/quarter")
    public ResponseEntity<List<Map<String, Object>>> getMovieCountByQuarter(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer quarter) {
        return ResponseEntity.ok(timeService.getMovieCountByYearAndQuarter(year, quarter));
    }

    @Operation(summary = "按星期统计电影数量", description = "统计每个星期的电影数量，weekday: 0-周一, 1-周二, 2-周三, 3-周四, 4-周五, 5-周六, 6-周日")
    @GetMapping("/weekday")
    public ResponseEntity<List<Map<String, Object>>> getMovieCountByWeekday(
            @RequestParam(required = false) Integer weekday) {
        return ResponseEntity.ok(timeService.getMovieCountByWeekday(weekday));
    }
}
