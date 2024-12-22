package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Director;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface DirectorMapper {
    int deleteByPrimaryKey(byte[] directorUuid);

    int insert(Director row);

    Director selectByPrimaryKey(byte[] directorUuid);

    List<Director> selectAll();

    int updateByPrimaryKey(Director row);

    // 添加新的查询方法
    List<Map<String, Object>> findMoviesByDirectorName(String directorName);
    List<Map<String, Object>> countMoviesByDirectorName(String directorName);
}