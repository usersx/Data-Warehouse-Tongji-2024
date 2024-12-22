package edu.tongji.queryserver.entity;

public class Director {
    private byte[] directorUuid;

    private String directorName;

    public byte[] getDirectorUuid() {
        return directorUuid;
    }

    public void setDirectorUuid(byte[] directorUuid) {
        this.directorUuid = directorUuid;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
}