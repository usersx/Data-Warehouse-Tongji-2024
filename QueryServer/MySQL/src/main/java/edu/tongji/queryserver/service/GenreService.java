package edu.tongji.queryserver.service;

import edu.tongji.queryserver.entity.Movie;
import edu.tongji.queryserver.mapper.GenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenreService {

    @Autowired
    private GenreMapper genreMapper;

    public List<Map<String, Object>> getMovieCountByGenre() {
        return genreMapper.countMoviesByGenre();
    }

    public List<Movie> getMoviesByGenreName(String genreName) {
        return genreMapper.findMoviesByGenreName(genreName);
    }
}
