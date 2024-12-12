package edu.tongji.queryserver.service;

import edu.tongji.queryserver.entity.Movie;
import edu.tongji.queryserver.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    @Autowired
    private ActorMapper actorMapper;

    public int getMovieCountByActorName(String actorName) {
        return actorMapper.countMoviesByActorName(actorName);
    }

    public List<Movie> getMoviesByActorName(String actorName) {
        return actorMapper.findMoviesByActorName(actorName);
    }
}
