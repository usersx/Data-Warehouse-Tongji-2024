package edu.tongji.queryserver.controller;

import edu.tongji.queryserver.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/director")
public class DirectorController {
    
    @Autowired
    private DirectorService directorService;

    @GetMapping("/movies")
    public ResponseEntity<List<Map<String, Object>>> getDirectorMovies(
            @RequestParam String directorName) {
        return ResponseEntity.ok(directorService.getDirectorMovies(directorName));
    }

    @GetMapping("/movie-count")
    public ResponseEntity<List<Map<String, Object>>> getDirectorMovieCount(
            @RequestParam String directorName) {
        return ResponseEntity.ok(directorService.getDirectorMovieCount(directorName));
    }
} 