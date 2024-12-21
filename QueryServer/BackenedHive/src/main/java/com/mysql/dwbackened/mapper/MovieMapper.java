package com.mysql.dwbackened.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysql.dwbackened.dto.MovieSearchDto;
import com.mysql.dwbackened.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author wyx20
 * @version 1.0
 * @title MovieMapper
 * @description
 * @create 2023/12/25 9:54
 */
@Mapper
public interface MovieMapper extends BaseMapper<Movie> {

    @Select("SELECT movie_id FROM movie WHERE  movie_title LIKE CONCAT('%', #{title}, '%')")
    Set<String> selectByScoreAndTitle(MovieSearchDto movieSearchDto);

    @Select({
            "<script>",
            "SELECT movie_id, movie_title,  review_num FROM movie WHERE movie_id IN",
            "<foreach item='movieId' collection='movieSet' open='(' separator=',' close=')'>",
            "#{movieId}",
            "</foreach>",
            "LIMIT #{start}, #{pageNum}",
            "</script>"
    })
    List<Movie> getMovieInfo(Set<String> movieSet, int start, int pageNum);

    @Select("SELECT DISTINCT(movie_title) FROM movie WHERE movie_title LIKE CONCAT('%', #{title}, '%') LIMIT #{amount}")
    List<String> getTitleRecommend(String title, int amount);


}
