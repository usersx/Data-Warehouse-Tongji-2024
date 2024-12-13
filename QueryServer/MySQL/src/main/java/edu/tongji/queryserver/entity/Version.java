package edu.tongji.queryserver.entity;

public class Version {
    private byte[] versionUuid;

    private String movieId;

    private String edition;

    private String language;

    public byte[] getVersionUuid() {
        return versionUuid;
    }

    public void setVersionUuid(byte[] versionUuid) {
        this.versionUuid = versionUuid;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}