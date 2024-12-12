package edu.tongji.queryserver.entity;

public class Genre {
    private byte[] genreUuid;

    private String movieId;

    private String genreName;

    public byte[] getGenreUuid() {
        return genreUuid;
    }

    public void setGenreUuid(byte[] genreUuid) {
        this.genreUuid = genreUuid;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}