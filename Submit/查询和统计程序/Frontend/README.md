# Introduction

本文件为2024年数据仓库课程项目的前端，通过构建VUE网页显示查询数据及速度对比。。主要分为组合查询和关系查询


原模版为vue-admin-template非常具有参考意义。
**Live demo:** http://panjiachen.github.io/vue-admin-template

主要用的方法是在utils中封装了一个数据查询专用的axios服务，以避免和原项目的axios服务相冲突，并在views中通过e-charts等实现了可视化的显示。


## evironment

安装依赖
npm install

还需安装core.js包
npm install --save core-js

npm run dev

This will automatically open http://localhost:9528

并保持另外三个后端项目的运行
