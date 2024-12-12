package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.ActorActor;
import java.util.List;

public interface ActorActorMapper {
    int insert(ActorActor row);

    List<ActorActor> selectAll();
}