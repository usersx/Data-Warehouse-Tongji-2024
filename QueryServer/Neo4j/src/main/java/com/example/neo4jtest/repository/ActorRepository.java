package com.example.neo4jtest.repository;

import com.example.neo4jtest.dto.ActorCollaboration;
import com.example.neo4jtest.dto.ActorDirectorCollaboration;
import com.example.neo4jtest.dto.ActorDoubleCollaboration;
import com.example.neo4jtest.dto.ActorTripleCollaboration;
import com.example.neo4jtest.entity.Actor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActorRepository extends Neo4jRepository<Actor, String> {

    // 1. 查找演员间的高频合作组合 (按合作次数降序)
    @Query("MATCH (a1:Actor)-[:ACTED]->(m:Movie)<-[:ACTED]-(a2:Actor) " +
            "WHERE a1.actor_uuid < a2.actor_uuid " +
            "RETURN a1.actor_name AS actorName1, a2.actor_name AS actorName2, COUNT(DISTINCT m) AS collaborationCount " +
            "ORDER BY collaborationCount DESC " +
            "SKIP ($start - 1) * $perPage " +
            "LIMIT $perPage")
    List<ActorCollaboration> findFrequentCollaborators(int start, int perPage);

    // 2. 查找合作次数超过5次的演员组合数量
    @Query("MATCH (a1:Actor)-[:ACTED]->(m:Movie)<-[:ACTED]-(a2:Actor) " +
            "WHERE a1.actor_uuid < a2.actor_uuid " +
            "WITH a1, a2, COUNT(DISTINCT m) AS collaborationCount " +
            "WHERE collaborationCount > 5 " +
            "RETURN COUNT(*) AS frequentCollaborationCount")
    Integer findFrequentCollaboratorsNum();

    // 3. 查找与演员有高频合作的导演 (按合作次数降序)
    @Query("MATCH (d:Director)-[:DIRECT]->(m:Movie)<-[:ACTED]-(a:Actor) " +
            "RETURN a.actor_name AS actorName, d.director_name AS directorName, COUNT(DISTINCT m) AS collaborationCount " +
            "ORDER BY collaborationCount DESC " +
            "SKIP ($start - 1) * $perPage " +
            "LIMIT $perPage")
    List<ActorDirectorCollaboration> findFrequentDirectors(int start, int perPage);

    // 4. 在指定流派下，查找评论数最多的演员双人组合
    @Query("MATCH (a1:Actor)-[:ACTED]->(m:Movie)-[:HAS_GENRE]->(g:Genre {genre_name: $genre})<-[:HAS_GENRE]-(m)<-[:ACTED]-(a2:Actor) " +
            "WHERE a1.actor_uuid < a2.actor_uuid AND m.movie_review_num IS NOT NULL " +
            "RETURN a1.actor_name AS actorName1, a2.actor_name AS actorName2, SUM(m.movie_review_num) AS totalReviews " +
            "ORDER BY totalReviews DESC " +
            "LIMIT 1")
    ActorDoubleCollaboration findMostReviewedCollaboration(String genre);

    // 5. 在指定流派下，查找评论数最多的三人组合
    @Query("MATCH (a1:Actor)-[:ACTED]->(m1:Movie)-[:HAS_GENRE]->(g:Genre {genre_name: $genre})<-[:HAS_GENRE]-(m1)<-[:ACTED]-(a2:Actor), " +
            "(a2)-[:ACTED]->(m2:Movie)-[:HAS_GENRE]->(g)<-[:HAS_GENRE]-(m2)<-[:ACTED]-(a3:Actor) " +
            "WHERE a1.actor_uuid < a2.actor_uuid AND a2.actor_uuid < a3.actor_uuid " +
            "AND m1.movie_review_num IS NOT NULL AND m2.movie_review_num IS NOT NULL " +
            "RETURN a1.actor_name AS actorName1, a2.actor_name AS actorName2, a3.actor_name AS actorName3, " +
            "SUM(m1.movie_review_num + m2.movie_review_num) AS totalReviews " +
            "ORDER BY totalReviews DESC " +
            "LIMIT 1")
    ActorTripleCollaboration findMostReviewedTripleCollaboration(String genre);
}