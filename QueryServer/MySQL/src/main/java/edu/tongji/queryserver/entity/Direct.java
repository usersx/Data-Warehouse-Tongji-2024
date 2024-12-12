package edu.tongji.queryserver.entity;

public class Direct {
    private byte[] directorUuid;

    private String movieId;

    public byte[] getDirectorUuid() {
        return directorUuid;
    }

    public void setDirectorUuid(byte[] directorUuid) {
        this.directorUuid = directorUuid;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}