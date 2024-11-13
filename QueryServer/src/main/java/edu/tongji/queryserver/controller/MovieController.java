package edu.tongji.queryserver.controller;

import edu.tongji.queryserver.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    
    @Autowired
    private MovieService movieService;

    @GetMapping("/versions")
    public ResponseEntity<List<Map<String, Object>>> getMovieVersions(
            @RequestParam String movieTitle) {
        return ResponseEntity.ok(movieService.getMovieVersions(movieTitle));
    }

    @GetMapping("/version-count")
    public ResponseEntity<List<Map<String, Object>>> getMovieVersionCount(
            @RequestParam String movieTitle) {
        return ResponseEntity.ok(movieService.getMovieVersionCount(movieTitle));
    }
}
