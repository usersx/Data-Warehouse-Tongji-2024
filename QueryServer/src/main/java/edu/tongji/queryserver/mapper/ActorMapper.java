package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Actor;
import java.util.List;

public interface ActorMapper {
    int deleteByPrimaryKey(byte[] actorUuid);

    int insert(Actor row);

    Actor selectByPrimaryKey(byte[] actorUuid);

    List<Actor> selectAll();

    int updateByPrimaryKey(Actor row);
}