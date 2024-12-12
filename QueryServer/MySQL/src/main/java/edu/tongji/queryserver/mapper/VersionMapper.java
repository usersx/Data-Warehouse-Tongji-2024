package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Version;
import java.util.List;

public interface VersionMapper {
    int deleteByPrimaryKey(byte[] versionUuid);

    int insert(Version row);

    Version selectByPrimaryKey(byte[] versionUuid);

    List<Version> selectAll();

    int updateByPrimaryKey(Version row);
}