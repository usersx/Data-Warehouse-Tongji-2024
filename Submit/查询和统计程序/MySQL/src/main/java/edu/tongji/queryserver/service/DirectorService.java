package edu.tongji.queryserver.service;

import edu.tongji.queryserver.mapper.DirectorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DirectorService {

    @Autowired
    private DirectorMapper directorMapper;

    public List<Map<String, Object>> getDirectorMovies(String directorName) {
        return directorMapper.findMoviesByDirectorName(directorName);
    }

    public List<Map<String, Object>> getDirectorMovieCount(String directorName) {
        return directorMapper.countMoviesByDirectorName(directorName);
    }
} 