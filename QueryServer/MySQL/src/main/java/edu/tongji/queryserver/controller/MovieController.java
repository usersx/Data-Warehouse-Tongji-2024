package edu.tongji.queryserver.controller;

import edu.tongji.queryserver.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "电影查询接口", description = "提供基于电影名称的查询")
@RestController
@RequestMapping("/api/movie")
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @Operation(summary = "查询电影的所有版本")
    @GetMapping("/versions")
    public ResponseEntity<List<Map<String, Object>>> getMovieVersions(
            @RequestParam String movieTitle) {
        return ResponseEntity.ok(movieService.getMovieVersions(movieTitle));
    }

    @Operation(summary = "统计电影版本数量")
    @GetMapping("/version-count")
    public ResponseEntity<List<Map<String, Object>>> getMovieVersionCount(
            @RequestParam String movieTitle) {
        return ResponseEntity.ok(movieService.getMovieVersionCount(movieTitle));
    }
}
