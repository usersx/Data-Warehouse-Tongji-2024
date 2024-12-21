package com.mysql.dwbackened.mapper;

import com.mysql.dwbackened.dto.ActorRelationCountDto;
import com.mysql.dwbackened.dto.ActorRelationInfoDto;
import com.mysql.dwbackened.dto.MovieSearchDto;
import com.mysql.dwbackened.entity.Actor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author wyx20
 * @version 1.0
 * @title ActorMapper
 * @description
 * @create 2023/12/25 19:08
 */
@Mapper
public interface ActorMapper extends BaseMapper<Actor> {
    @Select("SELECT DISTINCT(act.movie_id) FROM actor JOIN act ON act.actor_uuid = actor.actor_uuid WHERE actor_name LIKE CONCAT('%', #{actor}, '%')")
    Set<String> selectByName(MovieSearchDto movieSearchDto);

    @Select("SELECT DISTINCT(actor_name) FROM actor JOIN act ON actor.actor_uuid = act.actor_uuid WHERE act.movie_id = #{movieId}")
    List<String> selectNameByMovieId(String movieId);

    @Select("SELECT DISTINCT(actor_name) FROM actor WHERE actor_name LIKE CONCAT('%', #{actor}, '%') LIMIT #{amount}")
    List<String> getActorRecommend(String actor, int amount);

    @Select("SELECT actor_name FROM actor WHERE actor_uuid = #{personId} LIMIT 1")
    String selectNameByActorId(String personId);

    @Select("SELECT " +
            "    LEFT_PERSON_ID, " +
            "    RIGHT_PERSON_ID, " +
            "    COUNT(*) AS COOPERATION_COUNT " +
            "FROM " +
            "    actor_actor " +
            "GROUP BY " +
            "    LEFT_PERSON_ID, RIGHT_PERSON_ID " +
            "HAVING " +
            "    COUNT(*) > 5")
    List<ActorRelationCountDto> selectActorRelationCount();

    @Select("SELECT " +
            "    a.LEFT_PERSON_ID, " +
            "    a.RIGHT_PERSON_ID, " +
            "    SUM(m.review_num) AS reviewCount, " +
            "    COUNT(*) AS COOPERATION_COUNT " +
            "FROM " +
            "    actor_actor a " +
            "    JOIN movie m ON a.MOVIE_ID = m.movie_id " +
            "    JOIN genre g ON a.MOVIE_ID = g.movie_id " +
            "WHERE " +
            "    g.genre_name LIKE CONCAT('%', #{genre}, '%') " +
            "GROUP BY " +
            "    a.LEFT_PERSON_ID, a.RIGHT_PERSON_ID " +
            "ORDER BY " +
            "    reviewCount DESC " +
            "LIMIT 1")
    List<ActorRelationCountDto> getGenreRelationInfo(String genre);

    /**
     * 分页查询演员合作关系
     */
    @Select("SELECT " +
            "    LEFT_PERSON_ID, " +
            "    RIGHT_PERSON_ID, " +
            "    COUNT(*) AS COOPERATION_COUNT " +
            "FROM actor_actor " +
            "GROUP BY LEFT_PERSON_ID, RIGHT_PERSON_ID " +
            "HAVING COUNT(*) > 5 " +
            "ORDER BY COOPERATION_COUNT DESC " +
            "LIMIT 0, 50")
    List<ActorRelationCountDto> selectActorRelationPage(int start, int perPage);
}
