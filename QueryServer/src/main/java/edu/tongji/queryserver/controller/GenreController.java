package edu.tongji.queryserver.controller;

import edu.tongji.queryserver.entity.Movie;
import edu.tongji.queryserver.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    // 获取每个类别的电影数量
    @GetMapping("/movie-count")
    public ResponseEntity<List<Map<String, Object>>> getMovieCountByGenre() {
        List<Map<String, Object>> countByGenre = genreService.getMovieCountByGenre();
        return ResponseEntity.ok(countByGenre);
    }

    // 获取属于特定类别的电影列表
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMoviesByGenreName(@RequestParam String genreName) {
        List<Movie> movies = genreService.getMoviesByGenreName(genreName);
        return ResponseEntity.ok(movies);
    }
}
