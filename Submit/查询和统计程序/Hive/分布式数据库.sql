CREATE DATABASE dw2024;

create external table if not exists dw2024.act
(
    movie_id   string,
    actor_uuid string
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = '\"'
)
STORED AS TEXTFILE
LOCATION '/dw2024/Act'
tblproperties ("skip.header.line.count"="1");

create external table if not exists dw2024.actor
(
    actor_uuid string,
    actor_name string
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = "\""
)
STORED AS TEXTFILE
LOCATION '/dw2024/Actor/'
tblproperties ("skip.header.line.count"="1");

create external table if not exists dw2024.direct
(
    movie_id      string,
    director_uuid string
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = '\"'
)
STORED AS TEXTFILE
LOCATION '/dw2024/Direct'
tblproperties ("skip.header.line.count"="1");
create external table if not exists dw2024.director
(
    director_uuid string,
    director_name string
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = '\"'
)
STORED AS TEXTFILE
LOCATION '/dw2024/Director'
tblproperties ("skip.header.line.count"="1");

create external table if not exists dw2024.genre
(
    genre_uuid string comment '使用UUID作为genre的ID',
    movie_id   string comment '电影ID',
    genre_name string
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = '\"'
)
STORED AS TEXTFILE
LOCATION '/dw2024/Genre'
tblproperties ("skip.header.line.count"="1");

create external table if not exists dw2024.movie
(
    movie_id    string,
    movie_title string,
    imdb_score  float,
    review_num  int
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = '\"'
)
STORED AS TEXTFILE
LOCATION '/dw2024/Movie'
tblproperties ("skip.header.line.count"="1");

create external table if not exists dw2024.releasedate
(
    date_uuid string,
    movie_id  string,
    year      int,
    month     int,
    day       int,
    weekday   int comment '周一为0'
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = '\"'
)
STORED AS TEXTFILE
LOCATION '/dw2024/ReleaseDate'
tblproperties ("skip.header.line.count"="1");

create external table if not exists dw2024.version
(
    version_uuid string,
    movie_id     string,
    edition      string,
    language     string
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = '\"'
)
STORED AS TEXTFILE
LOCATION '/dw2024/Version'
tblproperties ("skip.header.line.count"="1");

CREATE EXTERNAL TABLE IF NOT EXISTS dw2024.actor_actor (
    LEFT_PERSON_ID INT,
    RIGHT_PERSON_ID INT,
    MOVIE_ID STRING
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = '\"'
)
STORED AS TEXTFILE
LOCATION '/dw2024/ActorActor'
tblproperties ("skip.header.line.count"="1");

CREATE EXTERNAL TABLE IF NOT EXISTS dw2024.director_actor (
    MOVIE_ID STRING,
    ACTOR_ID INT,
    DIRECTOR_ID INT
)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = '\"'
)
STORED AS TEXTFILE
LOCATION '/dw2024/DirectorActor'
tblproperties ("skip.header.line.count"="1");
