package com.example.neo4jtest.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class Actor {

    @Id
    @Property("actor_uuid")
    private String actorUuid;

    @Property("actor_name")
    private String actorName;

    public Actor() {
    }

    public Actor(String actorUuid, String actorName) {
        this.actorUuid = actorUuid;
        this.actorName = actorName;
    }

    // Getter and Setter
    public String getActorUuid() {
        return actorUuid;
    }

    public void setActorUuid(String actorUuid) {
        this.actorUuid = actorUuid;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
}
