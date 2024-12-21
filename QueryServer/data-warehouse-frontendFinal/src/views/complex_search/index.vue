<!-- eslint-disable no-unused-vars -->
<!-- eslint-disable vue/singleline-html-element-content-newline -->
<!-- eslint-disable vue/max-attributes-per-line -->
<!-- eslint-disable vue/require-v-for-key -->
<template>
  <div class="app-container">
    <el-row>
      <el-col :span="12">
        <el-form ref="form" :model="form" label-width="120px" style="padding-top: 5vh">
          <h2>电影版本查询</h2>
          <el-row>
            <el-col :span="11">
              <el-form-item label="电影名称">
                <el-input
                    v-model="form.title"
                    placeholder="请输入电影名"
                    style="width: 150%"
                    clearable
                    @keyup.enter="searchVersion"
                    size="small"
                ></el-input>
              </el-form-item>
            </el-col>
            <div style="text-align: center">
              <el-button
                  type="primary"
                  @click="searchVersion"
                  size="small"
                  style="margin-left: 3vh"
                  plain
              >查询</el-button>
            </div>
          </el-row>

          <h2>导演电影名称查询</h2>
          <el-row>
            <el-col :span="11">
              <el-form-item label="电影导演">
                <el-input
                    v-model="form.director"
                    placeholder="请输入导演名"
                    style="width: 150%"
                    clearable
                    @keyup.enter="searchDirector"
                    size="small"
                ></el-input>
              </el-form-item>
            </el-col>
            <div style="text-align: center">
              <el-button
                  type="primary"
                  @click="searchDirector"
                  size="small"
                  style="margin-left: 3vh"
                  plain
              >查询</el-button>
            </div>
          </el-row>

          <h2>演员电影名称查询</h2>
          <el-row>
            <el-col :span="11">
              <el-form-item label="电影演员">
                <el-input
                    v-model="form.actor"
                    placeholder="请输入演员名"
                    style="width: 150%"
                    clearable
                    @keyup.enter="searchActor"
                    size="small"
                ></el-input>
              </el-form-item>
            </el-col>
            <div style="text-align: center">
              <el-button
                  type="primary"
                  @click="searchActor"
                  size="small"
                  style="margin-left: 3vh"
                  plain
              >查询</el-button>
            </div>
          </el-row>

          <h2>年份查询电影数量</h2>
          <el-row>
            <el-col :span="11">
              <el-form-item label="电影年份">
                <el-input
                    v-model="form.year"
                    placeholder="请输入年份（1931-2022）"
                    style="width: 150%"
                    clearable
                    @keyup.enter="searchDate"
                    size="small"
                ></el-input>
              </el-form-item>
            </el-col>
            <div style="text-align: center">
              <el-button
                  type="primary"
                  @click="searchDate"
                  size="small"
                  style="margin-left: 3vh"
                  plain
              >查询</el-button>
            </div>
          </el-row>

          <h2>电影类别查询</h2>
          <el-row>
            <el-col :span="11">
              <el-form-item label="电影类别">
                <el-input
                    v-model="form.genre"
                    placeholder="请输入电影类别"
                    style="width: 150%"
                    clearable
                    @keyup.enter="searchGenre"
                    size="small"
                ></el-input>
              </el-form-item>
            </el-col>
            <div style="text-align: center">
              <el-button
                  type="primary"
                  @click="searchGenre"
                  size="small"
                  style="margin-left: 3vh"
                  plain
              >查询</el-button>
            </div>
          </el-row>

        </el-form>


      </el-col>
      <el-col :span="1">
        <el-divider direction="vertical" />
      </el-col>
      <el-col :span="10">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="查询结果" name="search_res">


            <el-table v-if="isTable1Visible" :data="result1" v-loading="isLoading" element-loading-text="正在为您查询..." stripe style="width: 100%"
                      height="600">
              <el-table-column prop="edition" label="Edition"></el-table-column>
              <el-table-column prop="language" label="Language"></el-table-column>
              <el-table-column prop="movie_title" label="Movie Title"></el-table-column>
            </el-table>

            <el-table v-if="isTable2Visible" :data="result2" v-loading="isLoading" element-loading-text="正在为您查询..." stripe style="width: 100%"
                      height="600">
              <el-table-column prop="director_name" label="director_name"></el-table-column>
              <el-table-column prop="movie_title" label="movie_title"></el-table-column>
              <el-table-column prop="imdb_score" label="imdb_score"></el-table-column>
            </el-table>

            <el-table v-if="isTable3Visible" :data="result3" v-loading="isLoading" element-loading-text="正在为您查询..." stripe style="width: 100%"
                      height="600">
              <el-table-column prop="movieId" label="movieId"></el-table-column>
              <el-table-column prop="movieTitle" label="movieTitle"></el-table-column>
              <el-table-column prop="imdbScore" label="imdbScore"></el-table-column>
              <el-table-column prop="reviewNum" label="reviewNum"></el-table-column>
            </el-table>

            <el-table v-if="isTable4Visible" :data="result4" v-loading="isLoading" element-loading-text="正在为您查询..." stripe style="width: 100%"
                      height="450">
              <el-table-column prop="year" label="year"></el-table-column>
              <el-table-column prop="movie_count" label="movie_count"></el-table-column>

            </el-table>

            <el-table v-if="isTable5Visible" :data="result5" v-loading="isLoading" element-loading-text="正在为您查询..." stripe style="width: 100%"
                      height="600">
              <el-table-column prop="imdbScore" label="imdbScore"></el-table-column>
              <el-table-column prop="movieId" label="movieId"></el-table-column>
              <el-table-column prop="movieTitle" label="movieTitle"></el-table-column>
              <el-table-column prop="reviewNum" label="reviewNum"></el-table-column>

            </el-table>

<!--            <el-row style="text-align: center; margin-top: 20px">-->
<!--              <el-pagination layout="prev, pager, next, jumper" :current-page.sync="currentPage" :page-size="10"-->
<!--                :page-count="totalPage" @current-change="getNewPage(form)" small />-->
<!--            </el-row>-->
          </el-tab-pane>

          <el-tab-pane label="耗时对比" name="speed">
            <div id="speed" style="width: 400px; height: 400px"></div>
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import { Message } from 'element-ui';
import my_axios from "@/utils/my-axios";
import {data} from "autoprefixer";

export default {
  name: "ComplexSearch",
  data() {
    return {
      activeName: "search_res",
      isLoading: false,
      totalPage: 0,
      currentPage: 1,
      mysql_speed: 0,
      spark_speed: 0,
      percentageColors: 'your-value',
      form: {
        title: "",
        min_score: 0,
        max_score: 5.0,
        year: null,
        month: null,
        season: null,
        day: null,
        weekday: null,

        genre: "",
        director: "",
        actor: "",
        columns: [],
        month_season: "",
        day_weekday: "",
      },
      columns: {
        title: false,
        asin: false,
        score: false,
        edition: false,
        format: false,
        data: false,
        directors: false,
        actors: false,
      },
      result1: [],
      result2: [],
      result3: [],
      result4: [],
      result5: [],
      test: "",
      errorMessage: '',
      myChart: null,
      pageSize: 10, // 每页条目数
      isTable1Visible: false, // 控制表格1显示与否的属性
      isTable2Visible: false,  // 控制表格2显示与否的属性isTable1Visible: false, // 控制表格1显示与否的属性
      isTable3Visible: false, // 控制表格2显示与否的属性isTable1Visible: false, // 控制表格1显示与否的属性
      isTable4Visible: false, // 控制表格2显示与否的属性
      isTable5Visible: false, // 确保其他表格不显示

    };
  },

  mounted() {
    this.echartsInit();
  },

  watch: {
    //监听速度变化，重新渲染页面
    mysql_speed: {
      handler(newValue, oldValue) {
        this.mysql_speed = newValue;
        this.echartsInit();
      },
    },
    spark_speed: {
      handler(newValue, oldValue) {
        this.spark_speed = newValue;
        this.echartsInit();
      },
    },
  },

  methods: {
    // handleSelect(item) {
    //   console.log(item);
    // },
    //
    handleClick(tab, event) {
      console.log(tab, event);
    },



    async searchVersion(){
      this.isTable1Visible = true;
      this.isTable2Visible = false; // 确保其他表格不显示
      this.isTable3Visible = false; // 确保其他表格不显示
      this.isTable4Visible = false; // 确保其他表格不显示
      this.isTable5Visible = false; // 确保其他表格不显示
      // 表单验证
      if (!this.form.title.trim()) {
        Message.warning("请输入电影名称!");
        return;
      }

      this.isLoading = true;
      this.errorMessage = '';
      this.result = [];

      try {
        const params = {
          movieTitle: this.form.title.trim()
        };

        const movieSearchDto = {
          movieTitle: this.form.title.trim(),
          min_score: 0,
          max_score: 5.0,
          year: null,
          month: null,
          season: null,
          day: null,
          weekday: null,
          genre: "",
          director: "",
          actor: "",
          columns: [],
          month_season: "",
          day_weekday: "",
        };

        // 测量 MySQL 查询时间
        const startTime = performance.now();
        // 调用mysql查询总数（假设这部分逻辑是必要的）
        await my_axios.get("/api/movie/version-count", { params })
            .then((res) => {

              // 检查响应是否为数组并且包含 version_count 字段

              if (Array.isArray(res)) {
                let totalVersionCount = res.reduce((acc, item) => acc + (item.version_count || 0), 0);
                console.log("总数:", totalVersionCount);
                // 这里可以处理分页信息
              } else {
                console.warn("未找到有效的数据111");
              }
            })
            .catch((err) => {
              this.errorMessage = "获取总版本数失败，请稍后再试";
              Message.error(this.errorMessage);
              throw err;
            });
        // 调用mysql查询
        const response = await my_axios.get("/api/movie/versions", {params});
        console.log("这是mysql的结果", response);
        console.log("data", response.data);


        // 计算并设置 mysql 查询耗时
        const endTime = performance.now();
        this.mysql_speed = (endTime - startTime).toFixed(2); // 单位为毫秒
        console.log("MySQL 查询耗时 (ms):", this.mysql_speed);

        this.result1 = response || [];


        // 调用hive查询并测量耗时
        const hiveStartTime = performance.now();
        try {
          const hiveResponse = await my_axios.post("/hive/movie/detail", movieSearchDto);
          console.log("这是Hive的结果", hiveResponse);
          console.log("Hive data", hiveResponse.data);
        } catch (err) {
          this.errorMessage = "获取Hive数据失败，请稍后再试";
          Message.error(this.errorMessage);
          throw err; // 抛出错误以便可以在调用栈中进一步处理
        }
        const hiveEndTime = performance.now();
        this.hive_speed = (hiveEndTime - hiveStartTime).toFixed(2); // 单位为毫秒
        console.log("Hive 查询耗时 (ms):", this.hive_speed);






      } catch (error) {
        if (error.response) {
          // 请求已发出，但服务器响应的状态码不在 2xx 范围内
          this.errorMessage = `请求失败，状态码：${error.response.status}`;
        } else {
          // 某种原因在设置请求时触发了错误
          this.errorMessage = '请求失败，请检查网络连接';
        }
        Message.error(this.errorMessage);
        console.error('Request failed:', error);
      } finally {
        this.isLoading = false;
      }
    },
    async searchDirector() {
      this.isTable2Visible = true;
      this.isTable1Visible = false; // 确保其他表格不显示
      this.isTable3Visible = false; // 确保其他表格不显示
      this.isTable4Visible = false; // 确保其他表格不显示
      this.isTable5Visible = false; // 确保其他表格不显示
      // 表单验证
      if (!this.form.director.trim()) {
        Message.warning("请输入导演名称!");
        return;
      }

      this.isLoading = true;
      this.errorMessage = '';
      this.result = [];

      try {
        const params = {
          directorName: this.form.director.trim() // 修改为导演名字参数
        };

        // 测量 MySQL 查询时间
        const startTime = performance.now();
        // 调用导演电影数量查询（可选）
        await my_axios.get("/api/director/movie-count", {params})
            .then((res) => {

              // 可以处理分页信息或展示总电影数给用户
              if (Array.isArray(res)) {
                let totalDirectorCount = res.reduce((acc, item) => acc + (item.movie_count|| 0), 0);
                console.log("总数:", totalDirectorCount);
                // 这里可以处理分页信息
              } else {
                console.warn("未找到有效的数据111");
              }
            })
            .catch((err) => {
              this.errorMessage = "获取导演电影总数失败，请稍后再试";
              Message.error(this.errorMessage);
              throw err;
            });

        // 调用导演电影列表查询
        const response = await my_axios.get("/api/director/movies", {params});
        console.log("这是导演的电影列表结果", response);
        console.log("data", response.data);

        // 计算并设置 mysql 查询耗时
        const endTime = performance.now();
        this.mysql_speed = (endTime - startTime).toFixed(2); // 单位为毫秒
        console.log("MySQL 查询耗时 (ms):", this.mysql_speed);
        this.result2 = response || [];
        // 如果需要对返回的数据进行处理，可以在这里进行
        // 注意：这里的逻辑取决于返回数据的实际结构

      } catch (error) {
        if (error.response) {
          // 请求已发出，但服务器响应的状态码不在 2xx 范围内
          this.errorMessage = `请求失败，状态码：${error.response.status}`;
        } else {
          // 某种原因在设置请求时触发了错误
          this.errorMessage = '请求失败，请检查网络连接';
        }
        Message.error(this.errorMessage);
        console.error('Request failed:', error);
      } finally {
        this.isLoading = false;
      }
    },
    async searchActor() {
      this.isTable3Visible = true;
      this.isTable2Visible = false; // 确保其他表格不显示
      this.isTable1Visible = false; // 确保其他表格不显示
      this.isTable4Visible = false; // 确保其他表格不显示
      this.isTable5Visible = false; // 确保其他表格不显示
      // 表单验证
      if (!this.form.actor.trim()) {
        Message.warning("请输入演员名称!");
        return;
      }

      this.isLoading = true;
      this.errorMessage = '';
      this.result = [];

      try {
        const params = {
          actorName: this.form.actor.trim() // 修改为演员名字参数
        };
        // 测量 MySQL 查询时间
        const startTime = performance.now();
        // 调用演员参演电影数量查询（可选）
        await my_axios.get("/api/actors/movie-count", {params})
            .then((res) => {
              console.log("演员参演电影总数:", res.data);
              // 可以处理分页信息或展示总电影数给用户
            })
            .catch((err) => {
              this.errorMessage = "获取演员参演电影总数失败，请稍后再试";
              Message.error(this.errorMessage);
              throw err;
            });

        // 调用演员参演电影列表查询
        const response = await my_axios.get("/api/actors/movies", {params});
        console.log("这是演员的电影列表结果", response);
        console.log("data", response.data);

        // 计算并设置 mysql 查询耗时
        const endTime = performance.now();
        this.mysql_speed = (endTime - startTime).toFixed(2); // 单位为毫秒
        console.log("MySQL 查询耗时 (ms):", this.mysql_speed);

        this.result3 = response || [];

        // 如果需要对返回的数据进行处理，可以在这里进行
        // 注意：这里的逻辑取决于返回数据的实际结构

      } catch (error) {
        if (error.response) {
          // 请求已发出，但服务器响应的状态码不在 2xx 范围内
          this.errorMessage = `请求失败，状态码：${error.response.status}`;
        } else {
          // 某种原因在设置请求时触发了错误
          this.errorMessage = '请求失败，请检查网络连接';
        }
        Message.error(this.errorMessage);
        console.error('Request failed:', error);
      } finally {
        this.isLoading = false;
      }
    },
    async searchDate() {
      this.isTable4Visible = true;
      this.isTable2Visible = false; // 确保其他表格不显示
      this.isTable3Visible = false; // 确保其他表格不显示
      this.isTable1Visible = false; // 确保其他表格不显示
      this.isTable5Visible = false; // 确保其他表格不显示
      // 表单验证
      if (!this.form.year || isNaN(this.form.year)) {
        Message.warning("请输入有效的年份!");
        return;
      }

      this.isLoading = true;
      this.errorMessage = '';
      this.result = [];

      try {
        const params = {
          year: parseInt(this.form.year, 10) // 修改为年份参数，并确保其为整数
        };
        // 测量 MySQL 查询时间
        const startTime = performance.now();
        // 调用按年份统计电影数量接口
        const response = await my_axios.get("/api/time/year", {params});
        console.log("这是按年份统计的电影数量结果", response);
        console.log("data", response.data);

        // 计算并设置 mysql 查询耗时
        const endTime = performance.now();
        this.mysql_speed = (endTime - startTime).toFixed(2); // 单位为毫秒
        console.log("MySQL 查询耗时 (ms):", this.mysql_speed);

        this.result4 = response || [];

        // 调用Hive查询并测量耗时
        const startTimeHive = performance.now();
        const hiveResponse = await my_axios.post("/hive/date/year", params);
        console.log("这是按年份统计的电影数量结果 (Hive)", hiveResponse);
        console.log("data (Hive)", hiveResponse.data);

        // 计算并设置 hive 查询耗时
        const endTimeHive = performance.now();
        this.hive_speed = (endTimeHive - startTimeHive).toFixed(2); // 单位为毫秒
        console.log("Hive 查询耗时 (ms):", this.hive_speed);

        // 如果需要对返回的数据进行处理，可以在这里进行
        // 注意：这里的逻辑取决于返回数据的实际结构

      } catch (error) {
        if (error.response) {
          // 请求已发出，但服务器响应的状态码不在 2xx 范围内
          this.errorMessage = `请求失败，状态码：${error.response.status}`;
        } else {
          // 某种原因在设置请求时触发了错误
          this.errorMessage = '请求失败，请检查网络连接';
        }
        Message.error(this.errorMessage);
        console.error('Request failed:', error);
      } finally {
        this.isLoading = false;
      }
    },
    async searchGenre() {
      this.isTable5Visible = true;
      this.isTable2Visible = false; // 确保其他表格不显示
      this.isTable3Visible = false; // 确保其他表格不显示
      this.isTable4Visible = false; // 确保其他表格不显示
      this.isTable1Visible = false; // 确保其他表格不显示
      // 表单验证
      if (!this.form.genre.trim()) {
        Message.warning("请输入电影类别");
        return;
      }

      this.isLoading = true;
      this.errorMessage = '';
      this.result5 = [];

      try {
        const params = {
          genreName: this.form.genre.trim() // 修改为导演名字参数
        };

        // 测量 MySQL 查询时间
        const startTime = performance.now();
        // 调用导演电影数量查询（可选）
        await my_axios.get("/api/genres/movie-count", {params})
            .then((res) => {

              // 可以处理分页信息或展示总电影数给用户
              if (Array.isArray(res)) {
                let totalGenreCount = res.reduce((acc, item) => acc + (item.movie_count|| 0), 0);
                console.log("总数:", totalGenreCount);
                // 这里可以处理分页信息
              } else {
                console.warn("未找到有效的数据111");
              }
            })
            .catch((err) => {
              this.errorMessage = "获取类别电影总数失败，请稍后再试";
              Message.error(this.errorMessage);
              throw err;
            });

        // 调用导演电影列表查询
        const response = await my_axios.get("/api/genres/movies", {params});
        console.log("这是按类别电影列表结果", response);
        console.log("data", response.data);

        // 计算并设置 mysql 查询耗时
        const endTime = performance.now();
        this.mysql_speed = (endTime - startTime).toFixed(2); // 单位为毫秒
        console.log("MySQL 查询耗时 (ms):", this.mysql_speed);
        this.result5 = response || [];
        // 如果需要对返回的数据进行处理，可以在这里进行
        // 注意：这里的逻辑取决于返回数据的实际结构

      } catch (error) {
        if (error.response) {
          // 请求已发出，但服务器响应的状态码不在 2xx 范围内
          this.errorMessage = `请求失败，状态码：${error.response.status}`;
        } else {
          // 某种原因在设置请求时触发了错误
          this.errorMessage = '请求失败，请检查网络连接';
        }
        Message.error(this.errorMessage);
        console.error('Request failed:', error);
      } finally {
        this.isLoading = false;
      }
    },


    echartsInit() {
      //使用时只需要把setOption里的对象换成echarts中的options或者自己的参数即可
      console.log("开始初始化");

      this.$echarts.init(document.getElementById("speed")).setOption({
        title: {
          text: "组合查询耗时对比(ms)",
        },
        tooltip: {},
        xAxis: {
          data: ["mysql", "hive"],
        },
        yAxis: {},
        series: [
          {
            name: "查询耗时(s)",
            type: "bar",
            // data: [this.mysql_speed, this.spark_speed],

            data: [this.mysql_speed, this.spark_speed],
          },
        ],
      });
    },
  },





};
</script>

<style scoped>
.el-divider--vertical {
  height: 100vh;
}
</style>
