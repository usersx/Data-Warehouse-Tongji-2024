package edu.tongji.queryserver.controller;

import edu.tongji.queryserver.entity.Movie;
import edu.tongji.queryserver.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    // 获取演员参演电影数量（通过名字）
    @GetMapping("/movie-count")
    public ResponseEntity<Integer> getMovieCountByActorName(@RequestParam String actorName) {
        int count = actorService.getMovieCountByActorName(actorName);
        return ResponseEntity.ok(count);
    }

    // 获取演员参演电影列表（通过名字）
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMoviesByActorName(@RequestParam String actorName) {
        List<Movie> movies = actorService.getMoviesByActorName(actorName);
        return ResponseEntity.ok(movies);
    }
}