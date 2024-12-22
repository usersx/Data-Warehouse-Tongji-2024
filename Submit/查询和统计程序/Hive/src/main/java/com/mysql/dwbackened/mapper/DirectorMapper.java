package com.mysql.dwbackened.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysql.dwbackened.dto.DirectorActorCountDto;
import com.mysql.dwbackened.dto.MovieSearchDto;
import com.mysql.dwbackened.entity.Director;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author wyx20
 * @version 1.0
 * @title DirectorMapper
 * @description
 * @create 2023/12/25 19:02
 */
@Mapper
public interface DirectorMapper extends BaseMapper<Director> {

    /**
     * 根据导演名称模糊查询相关电影ID
     */
    @Select("SELECT DISTINCT(direct.movie_id) FROM director " +
            "JOIN direct ON direct.director_uuid = director.director_uuid " +
            "WHERE director_name LIKE CONCAT('%', #{director}, '%')")
    Set<String> selectByName(MovieSearchDto movieSearchDto);

    /**
     * 根据电影ID查询导演名称列表
     */
    @Select("SELECT DISTINCT(director_name) FROM director " +
            "JOIN direct ON director.director_uuid = direct.director_uuid " +
            "WHERE direct.movie_id = #{movieId}")
    List<String> selectNameByMovieId(String movieId);

    /**
     * 获取导演名称推荐
     */
    @Select("SELECT DISTINCT(director_name) FROM director " +
            "WHERE director_name LIKE CONCAT('%', #{director}, '%') " +
            "LIMIT #{amount}")
    List<String> getDirectorRecommend(String director, int amount);

    /**
     * 查询导演与演员合作次数统计
     */
    @Select("SELECT " +
            "    DIRECTOR_ID, " +
            "    ACTOR_ID, " +
            "    COUNT(*) AS COOPERATION_COUNT " +
            "FROM director_actor " +
            "GROUP BY DIRECTOR_ID, ACTOR_ID " +
            "HAVING COUNT(*) > 5")
    List<DirectorActorCountDto> selectActorDirectorCount();

    /**
     * 分页查询导演与演员合作信息
     */
    @Select("SELECT " +
            "    director_id, " +
            "    actor_id, " +
            "    COUNT(*) AS COOPERATION_COUNT " +
            "FROM director_actor " +
            "GROUP BY director_id, actor_id " +
            "HAVING COUNT(*) > 5 " +
            "ORDER BY COOPERATION_COUNT DESC " +
            "LIMIT #{start}, #{perPage}")
    List<DirectorActorCountDto> selectDirectorActorPage(int start, int perPage);

    /**
     * 根据导演UUID查询导演名称
     */
    @Select("SELECT director_name FROM director WHERE director_uuid = #{director_uuid} LIMIT 1")
    String selectNameByActorId(String directorUuid);
}
