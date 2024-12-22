package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Genre;
import edu.tongji.queryserver.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GenreMapper {
    int deleteByPrimaryKey(byte[] genreUuid);

    int insert(Genre row);

    Genre selectByPrimaryKey(byte[] genreUuid);

    List<Genre> selectAll();

    int updateByPrimaryKey(Genre row);

    // 查询每个类别的电影数量
    List<Map<String, Object>> countMoviesByGenre(@Param("genreName") String genreName);

    // 查询属于特定类别的电影列表
    List<Movie> findMoviesByGenreName(@Param("genreName") String genreName);
}