package edu.tongji.queryserver.entity;

public class ReleaseDate {
    private byte[] dateUuid;

    private String movieId;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer weekday;

    public byte[] getDateUuid() {
        return dateUuid;
    }

    public void setDateUuid(byte[] dateUuid) {
        this.dateUuid = dateUuid;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }
}