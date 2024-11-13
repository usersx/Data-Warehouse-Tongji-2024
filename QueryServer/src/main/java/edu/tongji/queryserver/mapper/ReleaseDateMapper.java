package edu.tongji.queryserver.mapper;

import java.util.List;

import edu.tongji.queryserver.entity.ReleaseDate;

public interface ReleaseDateMapper {
    int deleteByPrimaryKey(byte[] dateUuid);

    int insert(ReleaseDate row);

    ReleaseDate selectByPrimaryKey(byte[] dateUuid);

    List<ReleaseDate> selectAll();

    int updateByPrimaryKey(ReleaseDate row);
}