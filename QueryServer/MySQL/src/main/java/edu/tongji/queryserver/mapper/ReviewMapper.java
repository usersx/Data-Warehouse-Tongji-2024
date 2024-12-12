package edu.tongji.queryserver.mapper;

import edu.tongji.queryserver.entity.Review;
import java.util.List;

public interface ReviewMapper {
    int deleteByPrimaryKey(byte[] reviewUuid);

    int insert(Review row);

    Review selectByPrimaryKey(byte[] reviewUuid);

    List<Review> selectAll();

    int updateByPrimaryKey(Review row);
}