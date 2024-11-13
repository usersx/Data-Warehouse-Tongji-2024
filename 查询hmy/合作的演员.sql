USE dw2024;

-- 查询名称: 按照演员、导演之间的关系进行查询及统计
-- 描述: 经常合作的演员有哪些
SELECT LEFT_PERSON_ID, RIGHT_PERSON_ID, COUNT(*) as collaboration_count
FROM ActorActor
GROUP BY LEFT_PERSON_ID, RIGHT_PERSON_ID
ORDER BY collaboration_count DESC;
