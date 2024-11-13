USE dw2024;

-- 查询名称: 按照用户评价进行查询及统计
-- 描述: 用户评分3分以上的电影有哪些
SELECT movie_id, profile_name, score, time, summary, text
FROM Review
WHERE score >= 3;