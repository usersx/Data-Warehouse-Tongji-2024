package edu.tongji.queryserver.service;

import edu.tongji.queryserver.mapper.ReleaseDateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TimeService {

    @Autowired
    private ReleaseDateMapper releaseDateMapper;

    public List<Map<String, Object>> getMovieCountByYear(Integer year) {
        return releaseDateMapper.countMoviesByYear(year);
    }

    public List<Map<String, Object>> getMovieCountByYearAndMonth(Integer year, Integer month) {
        return releaseDateMapper.countMoviesByYearAndMonth(year, month);
    }

    public List<Map<String, Object>> getMovieCountByYearAndQuarter(Integer year, Integer quarter) {
        return releaseDateMapper.countMoviesByYearAndQuarter(year, quarter);
    }

    public List<Map<String, Object>> getMovieCountByWeekday(Integer weekday) {
        return releaseDateMapper.countMoviesByWeekday(weekday);
    }
} 