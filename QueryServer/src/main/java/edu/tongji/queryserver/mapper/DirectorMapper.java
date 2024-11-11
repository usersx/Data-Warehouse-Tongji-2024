package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Director;
import java.util.List;

public interface DirectorMapper {
    int deleteByPrimaryKey(byte[] directorUuid);

    int insert(Director row);

    Director selectByPrimaryKey(byte[] directorUuid);

    List<Director> selectAll();

    int updateByPrimaryKey(Director row);
}