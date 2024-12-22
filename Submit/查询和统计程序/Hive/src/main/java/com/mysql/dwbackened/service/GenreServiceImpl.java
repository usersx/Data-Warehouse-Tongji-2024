package com.mysql.dwbackened.service;

import com.mysql.dwbackened.mapper.GenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreMapper genreMapper;

    @Override
    public HashMap<String, Object> getGenreRecommend(String genre, int amount) {
        HashMap<String, Object> result=new HashMap<>();
        result.put("suggestions",genreMapper.getGenreRecommend(genre,amount));
        return result;
    }

}
