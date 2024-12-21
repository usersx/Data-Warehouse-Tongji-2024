package com.mysql.dwbackened.service;

import java.util.HashMap;


public interface GenreService {
    HashMap<String, Object> getGenreRecommend(String genre, int amount);

}
