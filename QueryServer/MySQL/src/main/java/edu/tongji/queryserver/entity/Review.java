package edu.tongji.queryserver.entity;

public class Review {
    private byte[] reviewUuid;

    private String movieId;

    private String helpfulness;

    private String profileName;

    private Float score;

    private Long time;

    private String userId;

    private String summary;

    private String text;

    public byte[] getReviewUuid() {
        return reviewUuid;
    }

    public void setReviewUuid(byte[] reviewUuid) {
        this.reviewUuid = reviewUuid;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getHelpfulness() {
        return helpfulness;
    }

    public void setHelpfulness(String helpfulness) {
        this.helpfulness = helpfulness;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}