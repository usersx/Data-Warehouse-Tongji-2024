package com.mysql.dwbackened.service;

import java.util.HashMap;


public interface DirectorService {
    HashMap<String, Object> getDirectorRecommend(String director, int amount);

    HashMap<String, Object> selectDirectorActorCount(int page, int perPage);

    HashMap<String, Object> selectDirectorActorInfo(int page, int perPage);
}
