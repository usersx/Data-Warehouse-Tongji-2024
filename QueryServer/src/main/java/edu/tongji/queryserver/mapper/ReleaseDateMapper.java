package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.ReleaseDate;
import java.util.List;

public interface ReleaseDateMapper {
    int deleteByPrimaryKey(byte[] dateUuid);

    int insert(ReleaseDate row);

    ReleaseDate selectByPrimaryKey(byte[] dateUuid);

    List<ReleaseDate> selectAll();

    int updateByPrimaryKey(ReleaseDate row);
}