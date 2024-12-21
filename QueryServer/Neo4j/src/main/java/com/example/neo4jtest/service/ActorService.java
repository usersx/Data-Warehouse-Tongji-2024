package com.example.neo4jtest.service;

import com.example.neo4jtest.dto.*;
import com.example.neo4jtest.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<ActorCollaboration> getFrequentCollaborators(Integer page, Integer perPage) {
        int skip = (page - 1) * perPage; // 计算 SKIP 值
        return actorRepository.findFrequentCollaborators(skip, perPage);
    }

    public List<ActorDirectorCollaboration> getFrequentDirector(Integer page, Integer perPage) {
        int skip = (page - 1) * perPage; // 计算 SKIP 值
        return actorRepository.findFrequentDirectors(skip, perPage);
    }

    public Integer getFrequentCollaboratorNumber() {
        return actorRepository.findFrequentCollaboratorsNum();
    }

    public Collaboration2and3ResultDTO getCollaboration2and3(String genre) {
        double startTime = System.currentTimeMillis(); // 获取开始时间（毫秒）

        ActorDoubleCollaboration doubleCollab = actorRepository.findMostReviewedCollaboration(genre);
        ActorTripleCollaboration tripleCollab = actorRepository.findMostReviewedTripleCollaboration(genre);

        double endTime = System.currentTimeMillis(); // 结束时间（毫秒）

        double executionTimeInSeconds = (endTime - startTime) / 1000.0; // 将毫秒转换为秒

        return new Collaboration2and3ResultDTO(doubleCollab, tripleCollab, executionTimeInSeconds);
    }

    public Collaboration2ResultDTO getCollaboration2(String genre) {
        double startTime = System.currentTimeMillis(); // 获取开始时间

        ActorDoubleCollaboration doubleCollab = actorRepository.findMostReviewedCollaboration(genre);

        double endTime = System.currentTimeMillis(); // 结束时间

        double executionTimeInSeconds = (endTime - startTime) / 1000.0;

        return new Collaboration2ResultDTO(doubleCollab, executionTimeInSeconds);
    }
}
