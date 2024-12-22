package com.mysql.dwbackened.service;

import java.util.HashMap;


public interface ActorService {
    HashMap<String, Object> getActorRecommend(String actor, int amount);

    HashMap<String, Object> selectActorRelationCount(int page, int perPage);

    HashMap<String, Object> selectActorRelationInfo(int page, int perPage);

    HashMap<String, Object> getGenreRelationInfo(String genre);
}
