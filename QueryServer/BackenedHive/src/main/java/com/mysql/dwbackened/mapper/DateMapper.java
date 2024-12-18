package com.mysql.dwbackened.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysql.dwbackened.dto.MovieSearchDto;
import com.mysql.dwbackened.entity.ReleaseDate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author wyx20
 * @version 1.0
 * @title DateMapper
 * @description
 * @create 2023/12/25 8:50
 */
@Mapper
@Repository
public interface DateMapper extends BaseMapper<ReleaseDate> {
    /**
     * 统计指定年份的电影数量
     */
    @Select("SELECT COUNT(date_uuid) FROM releasedate WHERE year = #{year}")
    int selectByYear(String year);

    /**
     * 根据时间条件筛选电影
     */
    @Select("SELECT movie_id FROM releasedate WHERE " +
            "(#{year} IS NULL OR year = #{year}) AND " +
            "(#{month} IS NULL OR month = #{month}) AND " +
            "(#{weekday} IS NULL OR weekday = #{weekday})")
    Set<String> selectByTime(MovieSearchDto movieSearchDto);

    /**
     * 获取电影的发布日期，格式化为 YYYY-MM-DD
     */
    @Select("SELECT CONCAT(year, '-', LPAD(month, 2, '0'), '-', LPAD(day, 2, '0')) " +
            "FROM releasedate WHERE movie_id = #{movieId}")
    String selectDateByMovieId(String movieId);
}
