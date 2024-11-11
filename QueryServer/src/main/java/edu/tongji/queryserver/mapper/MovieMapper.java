package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Movie;
import java.util.List;

public interface MovieMapper {
    int deleteByPrimaryKey(String movieId);

    int insert(Movie row);

    Movie selectByPrimaryKey(String movieId);

    List<Movie> selectAll();

    int updateByPrimaryKey(Movie row);
}