package com.example.neo4jtest.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Movie {

    @Id
    @Property("movie_id")
    private String movieId;

    @Property("movie_title")
    private String movieTitle;

    @Property("movie_review_num")
    private Integer movieReviewNum;

    // 演员关系：Movie <-[:ACTED]- Actor (方向为演员指向电影)
    @Relationship(type = "ACTED", direction = Relationship.Direction.INCOMING)
    private List<Actor> actors;

    // 导演关系：Movie <-[:DIRECT]- Director (方向为导演指向电影)
    @Relationship(type = "DIRECT", direction = Relationship.Direction.INCOMING)
    private List<Director> directors;

    // 类型关系：Movie -[:HAS_GENRE]-> Genre (电影指向类型)
    @Relationship(type = "HAS_GENRE", direction = Relationship.Direction.OUTGOING)
    private List<Genre> genres;

    public Movie() {
    }

    public Movie(String movieId, String movieTitle, Integer movieReviewNum) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieReviewNum = movieReviewNum;
    }

    // Getter and Setter
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Integer getMovieReviewNum() {
        return movieReviewNum;
    }

    public void setMovieReviewNum(Integer movieReviewNum) {
        this.movieReviewNum = movieReviewNum;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}