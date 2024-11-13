package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface MovieMapper {
    int deleteByPrimaryKey(String movieId);

    int insert(Movie row);

    Movie selectByPrimaryKey(String movieId);

    List<Movie> selectAll();

    int updateByPrimaryKey(Movie row);

    // 添加新的查询方法
    List<Map<String, Object>> findVersionsByMovieTitle(String movieTitle);
    List<Map<String, Object>> countVersionsByMovieTitle(String movieTitle);
}