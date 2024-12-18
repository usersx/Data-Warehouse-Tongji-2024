package com.mysql.dwbackened.service;

import com.mysql.dwbackened.dto.MovieDetailDto;
import com.mysql.dwbackened.dto.MovieSearchDto;
import com.mysql.dwbackened.entity.Movie;
import com.mysql.dwbackened.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wyx20
 * @version 1.0
 * @title MovieServiceImpl
 * @description
 * @create 2023/12/25 14:39
 */
@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private DateMapper dateMapper;

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private VersionMapper versionMapper;

    /**
     * 获取符合条件的电影数量
     */
    @Override
    public HashMap<String, Object> getMovieCount(MovieSearchDto movieSearchDto) {
        HashMap<String,Object> result = new HashMap<>();
        Set<String> movieSet = movieMapper.selectByScoreAndTitle(movieSearchDto);
        
        if(!movieSearchDto.getGenre_name().isEmpty()) {
            movieSet.retainAll(genreMapper.selectByGenreName(movieSearchDto));
        }
        
        movieSet.retainAll(dateMapper.selectByTime(movieSearchDto));
        
        if(!movieSearchDto.getActor().isEmpty()) {
            movieSet.retainAll(actorMapper.selectByName(movieSearchDto));
        }

        if (!movieSearchDto.getDirector().isEmpty()) {
            movieSet.retainAll(directorMapper.selectByName(movieSearchDto));
        }

        result.put("pages", movieSet.size() / movieSearchDto.getPer_page());
        return result;
    }

    @Override
    public HashMap<String, Object> getMovieInfo(MovieSearchDto movieSearchDto) {
        long startTime = System.currentTimeMillis();
        int start = (movieSearchDto.getPage() - 1) * movieSearchDto.getPer_page();

        HashMap<String, Object> result = new HashMap<>();
        Set<String> movieSet = movieMapper.selectByScoreAndTitle(movieSearchDto);
        List<MovieDetailDto> movieDetailDtoList = new ArrayList<>();
        
        if (!movieSet.isEmpty()) {
            List<Movie> movieList = movieMapper.getMovieInfo(movieSet, start, movieSearchDto.getPer_page());
            for (Movie movie : movieList) {
                MovieDetailDto movieDetailDto = new MovieDetailDto();
                if (movieSearchDto.getColumns().contains("movieId"))
                    movieDetailDto.setMovieId(movie.getMovieId());
                if (movieSearchDto.getColumns().contains("title"))
                    movieDetailDto.setMovieTitle(movie.getMovieTitle());
                if (movieSearchDto.getColumns().contains("score"))
                    movieDetailDto.setMovieScore(movie.getImdbScore().toString());
                if (movieSearchDto.getColumns().contains("date"))
                    movieDetailDto.setDate(dateMapper.selectDateByMovieId(movie.getMovieId()));
                if (movieSearchDto.getColumns().contains("directors")){
                    movieDetailDto.setDirectors(String.join(", ", directorMapper.selectNameByMovieId(movie.getMovieId())));
                }
                if (movieSearchDto.getColumns().contains("actors")){
                    movieDetailDto.setActors(String.join(", ", actorMapper.selectNameByMovieId(movie.getMovieId())));
                }
                if (movieSearchDto.getColumns().contains("format")){
                    List<String> formats = versionMapper.selectFormatByMovieId(movie.getMovieId());
                    String formattedFormat = formats.stream()
                            .filter(format -> format != null && !format.equals("null"))
                            .collect(Collectors.joining(", "));
                    movieDetailDto.setFormat(formattedFormat);
                }
                if (movieSearchDto.getColumns().contains("edition")){
                    movieDetailDto.setEdition(String.join(", ", versionMapper.selectEditionByMovieId(movie.getMovieId())));
                }
                movieDetailDtoList.add(movieDetailDto);
            }
        }
        
        result.put("data", movieDetailDtoList);
        
        // 统计查询时间
        long queryTimeMillis = System.currentTimeMillis() - startTime;
        double queryTimeSeconds = queryTimeMillis / 1000.0;
        result.put("consuming_time", queryTimeSeconds);
        
        return result;
    }

    @Override
    public HashMap<String, Object> getTitleRecommend(String title, int amount) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("suggestions", movieMapper.getTitleRecommend(title, amount));
        return result;
    }
}
