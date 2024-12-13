package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Act;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActMapper {
    int deleteByPrimaryKey(@Param("actorUuid") byte[] actorUuid, @Param("movieId") String movieId);

    int insert(Act row);

    List<Act> selectAll();
}