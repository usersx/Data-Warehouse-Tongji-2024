package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Direct;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DirectMapper {
    int deleteByPrimaryKey(@Param("directorUuid") byte[] directorUuid, @Param("movieId") String movieId);

    int insert(Direct row);

    List<Direct> selectAll();
}