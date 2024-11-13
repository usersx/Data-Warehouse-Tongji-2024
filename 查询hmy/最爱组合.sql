USE dw2024;

-- 查询名称: 按照演员、导演之间的关系进行查询及统计
-- 描述: 如果要拍一部XXX类型的电影，最受关注（评论最多）的演员组合（2人，3人等）是什么？
WITH GenreMovies AS (
    SELECT movie_id
    FROM Genre
    WHERE genre_name = 'Action'
),
MovieReviews AS (
    SELECT movie_id, review_num
    FROM Movie
    WHERE movie_id IN (SELECT movie_id FROM GenreMovies)
),
ActorPairs AS (
    SELECT 
        a1.actor_name AS actor1,
        a2.actor_name AS actor2,
        aa.movie_id,
        mr.review_num
    FROM ActorActor aa
    JOIN Actor a1 ON aa.LEFT_PERSON_ID = a1.actor_uuid
    JOIN Actor a2 ON aa.RIGHT_PERSON_ID = a2.actor_uuid
    JOIN MovieReviews mr ON aa.movie_id = mr.movie_id
),
ActorPairCounts AS (
    SELECT 
        actor1,
        actor2,
        SUM(review_num) AS total_reviews
    FROM ActorPairs
    GROUP BY actor1, actor2
)
SELECT 
    actor1,
    actor2,
    total_reviews
FROM ActorPairCounts
ORDER BY total_reviews DESC
-- 筛选前20个
LIMIT 20;