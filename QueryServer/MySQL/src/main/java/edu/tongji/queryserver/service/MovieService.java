package edu.tongji.queryserver.service;

import edu.tongji.queryserver.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    @Autowired
    private MovieMapper movieMapper;

    public List<Map<String, Object>> getMovieVersions(String movieTitle) {
        return movieMapper.findVersionsByMovieTitle(movieTitle);
    }

    public List<Map<String, Object>> getMovieVersionCount(String movieTitle) {
        return movieMapper.countVersionsByMovieTitle(movieTitle);
    }
} 