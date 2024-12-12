package edu.tongji.queryserver.entity;

public class DirectorActor {
    private String movieId;

    private byte[] actorId;

    private byte[] directorId;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public byte[] getActorId() {
        return actorId;
    }

    public void setActorId(byte[] actorId) {
        this.actorId = actorId;
    }

    public byte[] getDirectorId() {
        return directorId;
    }

    public void setDirectorId(byte[] directorId) {
        this.directorId = directorId;
    }
}