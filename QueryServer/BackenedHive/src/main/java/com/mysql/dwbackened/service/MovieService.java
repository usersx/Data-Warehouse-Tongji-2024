package com.mysql.dwbackened.service;



import com.mysql.dwbackened.dto.MovieSearchDto;

import java.util.HashMap;


public interface MovieService {
    HashMap<String, Object> getMovieCount(MovieSearchDto movieSearchDto);

    HashMap<String, Object> getMovieInfo(MovieSearchDto movieSearchDto);

    HashMap<String, Object> getTitleRecommend(String title, int amount);
}
