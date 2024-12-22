package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.DirectorActor;
import java.util.List;

public interface DirectorActorMapper {
    int insert(DirectorActor row);

    List<DirectorActor> selectAll();
}