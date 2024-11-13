package edu.tongji.queryserver.mapper;

import java.util.List;
import java.util.Map;

import edu.tongji.queryserver.entity.ReleaseDate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReleaseDateMapper {
    int deleteByPrimaryKey(byte[] dateUuid);

    int insert(ReleaseDate row);

    ReleaseDate selectByPrimaryKey(byte[] dateUuid);

    List<ReleaseDate> selectAll();

    int updateByPrimaryKey(ReleaseDate row);

    List<Map<String, Object>> countMoviesByYear(Integer year);

    List<Map<String, Object>> countMoviesByYearAndMonth(Integer year, Integer month);

    List<Map<String, Object>> countMoviesByYearAndQuarter(Integer year, Integer quarter);

    List<Map<String, Object>> countMoviesByWeekday(Integer weekday);
}