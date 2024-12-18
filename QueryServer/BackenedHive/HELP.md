# 分布式数据库后端
下载docker
配置docker path环境

docker pull apache/hive:4.0.0-alpha-2


docker run -d -p 10000:10000 -p 10002:10002 --env SERVICE_NAME=hiveserver2 -v E:\ETL-RUN\Data\table:/dw2024 --name hive7 apache/hive:${HIVE_VERSION}

Dbeaver 连接
jdbc:hive2://localhost:10000



