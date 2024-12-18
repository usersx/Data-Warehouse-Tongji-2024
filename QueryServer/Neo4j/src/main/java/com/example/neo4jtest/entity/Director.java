package com.example.neo4jtest.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class Director {

    @Id
    @Property("director_uuid")
    private String directorUuid;

    @Property("director_name")
    private String directorName;

    public Director() {
    }

    public Director(String directorUuid, String directorName) {
        this.directorUuid = directorUuid;
        this.directorName = directorName;
    }

    // Getter and Setter
    public String getDirectorUuid() {
        return directorUuid;
    }

    public void setDirectorUuid(String directorUuid) {
        this.directorUuid = directorUuid;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
}