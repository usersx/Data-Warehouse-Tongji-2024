package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Genre;
import java.util.List;

public interface GenreMapper {
    int deleteByPrimaryKey(byte[] genreUuid);

    int insert(Genre row);

    Genre selectByPrimaryKey(byte[] genreUuid);

    List<Genre> selectAll();

    int updateByPrimaryKey(Genre row);
}