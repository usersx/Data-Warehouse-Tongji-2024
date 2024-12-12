package edu.tongji.queryserver.entity;

public class ActorActor {
    private String movieId;

    private byte[] leftPersonId;

    private byte[] rightPersonId;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public byte[] getLeftPersonId() {
        return leftPersonId;
    }

    public void setLeftPersonId(byte[] leftPersonId) {
        this.leftPersonId = leftPersonId;
    }

    public byte[] getRightPersonId() {
        return rightPersonId;
    }

    public void setRightPersonId(byte[] rightPersonId) {
        this.rightPersonId = rightPersonId;
    }
}