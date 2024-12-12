package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Actor;
import edu.tongji.queryserver.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActorMapper {
    int deleteByPrimaryKey(byte[] actorUuid);

    int insert(Actor row);

    Actor selectByPrimaryKey(byte[] actorUuid);

    List<Actor> selectAll();

    int updateByPrimaryKey(Actor row);

    // 查询某演员参演的电影数量（通过演员名字）
    int countMoviesByActorName(@Param("actorName") String actorName);

    // 查询某演员参演的电影列表（通过演员名字）
    List<Movie> findMoviesByActorName(@Param("actorName") String actorName);

}