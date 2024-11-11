package edu.tongji.queryserver.entity;

public class Actor {
    private byte[] actorUuid;

    private String actorName;

    public byte[] getActorUuid() {
        return actorUuid;
    }

    public void setActorUuid(byte[] actorUuid) {
        this.actorUuid = actorUuid;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
}