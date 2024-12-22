package com.mysql.dwbackened.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysql.dwbackened.dto.MovieSearchDto;
import com.mysql.dwbackened.entity.Genre;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;


@Mapper
public interface GenreMapper extends BaseMapper<Genre> {
    /**
     * 根据类型名称查询电影ID列表
     */
    @Select("SELECT movie_id FROM genre WHERE genre_name LIKE CONCAT('%', #{genre_name}, '%')")
    Set<String> selectByGenreName(MovieSearchDto movieSearchDto);

    /**
     * 获取类型名称推荐
     */
    @Select("SELECT DISTINCT(genre_name) FROM genre WHERE genre_name LIKE CONCAT('%', #{genre}, '%') LIMIT #{amount}")
    List<String> getGenreRecommend(String genre, int amount);
}
