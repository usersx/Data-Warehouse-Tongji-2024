package edu.tongji.queryserver.entity;

public class Act {
    private byte[] actorUuid;

    private String movieId;

    public byte[] getActorUuid() {
        return actorUuid;
    }

    public void setActorUuid(byte[] actorUuid) {
        this.actorUuid = actorUuid;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}