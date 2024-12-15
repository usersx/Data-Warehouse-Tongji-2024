package com.example.neo4jtest.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class Genre {

    @Id
    @Property("genre_uuid")
    private String genreUuid;

    @Property("genre_name")
    private String genreName;

    public Genre() {
    }

    public Genre(String genreUuid, String genreName) {
        this.genreUuid = genreUuid;
        this.genreName = genreName;
    }

    // Getter and Setter
    public String getGenreUuid() {
        return genreUuid;
    }

    public void setGenreUuid(String genreUuid) {
        this.genreUuid = genreUuid;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}