<template>
  <div class="movie-query">
    <h2>电影查询</h2>
    <form @submit.prevent="search">
      <div>
        <label for="movieTitle">电影名称:</label>
        <input id="movieTitle" v-model="form.title" type="text" required />
      </div>
      <button type="submit">查询</button>
    </form>

    <div v-if="isLoading" class="loading">加载中...</div>

    <table id="movieTable">
      <thead>
      <tr>
        <th>Index</th>
        <th>Edition</th>
        <th>Language</th>
        <th>Movie Title</th>

      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import { Message } from 'element-ui';
import my_axios from "@/utils/my-axios";

export default {
  data() {
    return {
      form: {
        title: ''
      },
      rawResponse: null, // 存储原始 API 响应
      isLoading: false,
      errorMessage: '',
      myChart: null
    };
  },
  computed: {
    // 格式化响应数据以便于显示
    formattedResponse() {
      if (this.rawResponse === null) return '';
      try {
        // 尝试将 JSON 对象转换为格式化的字符串
        return JSON.stringify(this.rawResponse, null, 2);
      } catch (e) {
        // 如果不是 JSON，则直接返回原始文本
        return this.rawResponse;
      }
    }
  },
  methods: {
    async search() {
      // 表单验证
      if (!this.form.title.trim()) {
        Message.warning("请输入电影名称!");
        return;
      }

      this.isLoading = true;
      this.errorMessage = '';
      this.rawResponse = null;

      try {
        const params = {
          movieTitle: this.form.title.trim()
        };

        // 调用mysql查询总数（假设这部分逻辑是必要的）
        await my_axios.get("/api/movie/version-count", { params })
            .then((res) => {
              console.log("总页数:", res.data);
              // 这里可以处理分页信息
            })
            .catch((err) => {
              this.errorMessage = "获取总页数失败，请稍后再试";
              Message.error(this.errorMessage);
              throw err;
            });

        // 调用mysql查询
        const response = await my_axios.get("/api/movie/versions", { params });
        console.log("这是mysql的结果", response);
        console.log("data", response.data);
        this.result = response.data;
        console.log("test", this.result);
        if (this.columns["title"] === true) {
          for (let i = 0; i < this.result.length; i++) {
            console.log("标题", this.result[i].movieTitle);
            this.result[i].movieTitle = this.result[i].movieTitle.replace(/"/g, "");
            console.log("标题后", this.result[i].movieTitle);
          }
        }
        this.mysql_speed = response.consuming_time;
        console.log("mysql速度", this.mysql_speed);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    },


  }
};
</script>

<style scoped>
.movie-query {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.movie-query h2 {
  text-align: center;
}

.movie-query form div {
  margin-bottom: 15px;
}

.movie-query label {
  display: inline-block;
  width: 100px;
  text-align: right;
  margin-right: 10px;
}

.movie-query input {
  padding: 5px;
  width: calc(100% - 115px);
}

.movie-query button {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.movie-query button:hover {
  background-color: #45a049;
}

.loading {
  font-style: italic;
  color: gray;
}

.api-response {
  background-color: #f4f4f4;
  border: 1px solid #ddd;
  padding: 10px;
  border-radius: 4px;
  white-space: pre-wrap;
  overflow-x: auto; /* 确保长内容可滚动 */
  margin-top: 20px;
}

.error {
  color: red;
}
</style>
