USE dw2024;

-- 查询名称: 按照演员、导演之间的关系进行查询及统计
-- 描述: 经常合作的导演和演员有哪些
SELECT 
    d.director_name,
    a.actor_name,
    COUNT(*) AS collaboration_count
FROM 
    Director d
JOIN 
    DirectorActor da ON d.director_uuid = da.DIRECTOR_id
JOIN 
    Actor a ON a.actor_uuid = da.ACTOR_id
GROUP BY 
    d.director_name, a.actor_name
HAVING 
    COUNT(*) > 1 -- 考虑至少合作过一次的导演和演员对
ORDER BY 
    collaboration_count DESC;