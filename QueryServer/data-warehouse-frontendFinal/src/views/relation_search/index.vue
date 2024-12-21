<!-- eslint-disable no-unused-vars -->
<!-- eslint-disable vue/singleline-html-element-content-newline -->
<!-- eslint-disable vue/max-attributes-per-line -->
<!-- eslint-disable vue/require-v-for-key -->
<template>
  <div class="app-container">

    <el-row style="height: 50vh">
      <el-col :span="12">
        <el-form ref="form" :model="form" label-width="120px" style="padding-top: 5vh">
          <el-form-item label="关系来源">
            <el-select v-model="form.source" class="m-2" placeholder="Select" size="small"
              style="width: 75px; margin-right: 15px">
              <el-option label="演员" value="actor"></el-option>
              <el-option label="导演" value="director"></el-option>
            </el-select>
          </el-form-item>


          <el-form-item label="合作对象" style="margin-top: 5vh">
            <el-input v-model="form.target" class="m-2" size="small" style="width: 75px; margin-right: 15px"
              :readonly="true" />
          </el-form-item>

          <!-- <el-form-item label="合作次数" style="margin-top: 5vh">
            <span style="margin-left: 10px; margin-right: 15px">大于</span>
            <el-input
              v-model="form.times"
              size="small"
              :min="0"
              controls-position="right"
              style="width: 50px"
              :readonly="true"
            />
            <span style="margin-left: 15px">次</span>
          </el-form-item> -->
        </el-form>
        <div style="text-align: center">
          <el-button type="primary" @click="search(form)" size="small" style="margin-left: 3vh; margin-top: 10px"
            plain>查询</el-button>

          <el-form ref="form" :model="form" label-width="120px" style="padding-top: 5vh">
            <el-form-item label="最受关注(评论最多)的演员组合" label-width="250px">
            </el-form-item>
            <el-form-item label="电影类型">
              <el-input v-model="form.genre" placeholder="请输入电影类型"
                               style="width: 20vw" clearable @keyup.enter="handleSelect" size="small" />
            </el-form-item>
          </el-form>
          <el-button type="primary" @click="searchPopular(form.genre)" size="small" :disabled="!form.genre.trim()"
            style="margin-left: 3vh; margin-top: 10px" plain>查询</el-button>
        </div>
      </el-col>
      <el-col :span="1">
        <el-divider direction="vertical" />
      </el-col>
      <el-col :span="10">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="查询结果" name="search">
            <el-table v-if="isTable1Visible" :data="result1" v-loading="isLoading" element-loading-text="正在为您查询..." stripe style="width: 100%"
              height="600">
              <el-table-column prop="actorName1" label="合作者1" width="100" />
              <el-table-column prop="actorName2" label="合作者2" width="100" />
              <el-table-column prop="collaborationCount" label="合作次数" width="100" />

            </el-table>

            <el-table v-if="isTable2Visible" :data="result2" v-loading="isLoading" element-loading-text="正在为您查询..." stripe style="width: 100%"
                      height="600">
              <el-table-column prop="name1" label="演员" width="100" />
              <el-table-column prop="name2" label="导演" width="100" />
              <el-table-column prop="times" label="合作次数" width="100" />

            </el-table>
<!--            <el-row style="text-align: center; margin-top: 20px">-->
<!--              <el-pagination layout="prev, pager, next, jumper" :current-page.sync="currentPage" :page-size="5"-->
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
export default {
  name: "RelationSearch",
  data() {
    return {
      activeName: "search",
      isLoading: false,
      dialogVisible: false,
      result: [],
      spark_speed: 0,
      neo4j_speed: 0,

      form: {
        source: "actor",
        target: "演员",
        name: "",
        times: 5,
        genre: ''
      },
      rules: {
        genre: [
          { required: true, message: '请输入电影类型', trigger: 'blur' }
        ]
      },


      result1: [],
      result2: [],
      isTable1Visible: false, // 控制表格1显示与否的属性
      isTable2Visible: false,  // 控制表格2显示与否的属性isTable1Visible: false, // 控制表格1显示与否的属性
    };
  },

  mounted() {
    this.echartsInit();
  },

  watch: {
    //监听速度变化，重新渲染页面

    spark_speed: {
      handler(newValue, oldValue) {
        this.spark_speed = newValue;
        this.echartsInit();
      },
    },
    neo4j_speed: {
      handler(newValue, oldValue) {
        this.neo4j_speed = newValue;
        this.echartsInit();
      },
    },
  },

  methods: {
    handleSelect(item) {
      console.log(item);
    },

    handleClick(tab, event) {
      console.log(tab, event);
    },



    search(form) {
      //判断是否有值为空
      // if (form.name == "") {
      //   this.$message.warning("请输入姓名!");
      // } else
      if (form.source === "director" && form.target === "director") {
        this.$message.warning("关系来源和合作对象不能同时为导演!");
      } else {
        this.isLoading = true;
        //neo4j查询总数
        this.$axios
          .get("/neo4j/actors/frequent-actors-number", )
          .then((res) => {
            console.log("pages", res.pages);
            this.totalPage = res.pages;
          })
          .catch((err) => {
            this.$message.error("当前neo4j网络异常，请稍后再试");
          });

        //mysql关系查询
        // this.$axios
        //   .post("/relation/detail", {
        //     source: form.source,
        //     // target: form.target,
        //     // name: form.name,
        //     // times: form.times,
        //     page: 1,
        //     per_page: 5,
        //   })
        //   .then((res) => {
        //     console.log(res);
        //     this.result = res.data;
        //     console.log("mysql结果", this.result);
        //     this.mysql_speed = res.consuming_time;
        //     this.isLoading = false;
        //   })
        //   .catch((err) => {
        //     this.$message.error("当前mysql网络异常，请稍后再试");
        //   });
        this.$axios
          .post("/hive/relation/detail", {
              source: form.source,
              page: 1,
              per_page: 5,


            // target: form.target,
            // name: form.name,
            // times: form.times,

          })
          .then((res) => {
            console.log(res);
            console.log("我传成功啦");
            this.spark_speed = res.consuming_time;
            this.isLoading=false
            // this.result = response.data || [];

          })
          .catch((err) => {
            this.$message.error("当前spark网络异常，请稍后再试");
          });

        if (form.source == "actor") {
          //neo4j关系查询
          this.$axios
            .get("/neo4j/actors/frequent-actors", {
              params: {
                page: 1,
                per_page: 10,
              }

            })
            .then((res) => {
              console.log(res);
              console.log("我跑通啦");
              this.neo4j_speed = res.time;
              this.isTable1Visible = true;
              this.isTable2Visible = false; // 确保其他表格不显示
              this.result1 = res.collaborations || []; // 确保有默认值


            })
            .catch((err) => {
              this.$message.error("当前neo4j网络异常，请稍后再试");
            });


        }
        else if (form.source === "director") {
          this.$axios
            .get("/neo4j/actors/frequent-directors", {
              params: {
                page: 1,
                per_page: 10,
              }

            })
            .then((res) => {
              console.log(res);
              console.log("我跑通啦");
              this.neo4j_speed = res.time;
              this.isTable2Visible = true;
              this.isTable1Visible = false; // 确保其他表格不显示
              this.result2 = res.collaborations || []; // 确保有默认值
            })
            .catch((err) => {
              console.log(err);
              this.$message.error("当前neo4j网络异常，请稍后再试");
            });
        }

        //neo4j关系查询
        // this.$axios
        //   .post("/neo4j/comprehensive/relation", {
        //     source: form.source,
        //     target: form.target,
        //     name: form.name,
        //     times: form.times,
        //     page: 1,
        //     per_page: 5,
        //   })
        //   .then((res) => {
        //     console.log(res);
        //     this.neo4j_speed = res.consuming_time;
        //   })
        //   .catch((err) => {
        //     this.$message.error("当前neo4j网络异常，请稍后再试");
        //   });


      }
    },

    searchPopular(genre) {
      //判断是否有值为空
      // if (form.name == "") {
      //   this.$message.warning("请输入姓名!");
      // } els
      this.isLoading = true;
      //mysql查询总数
      // this.$axios
      //   .get("/relation/popular/count", {
      //     // source: form.source,
      //     //target: form.target,
      //     //name: form.name,
      //     //times: form.times,
      //     params:{
      //     genre:genre,
      //     page: 1,
      //     per_page: 5,
      //     }
      //   })
      //   .then((res) => {
      //     console.log("pages", res.pages);
      //     this.totalPage = res.pages;
      //   })
      //   .catch((err) => {
      //     this.$message.error("当前mysql网络异常，请稍后再试");
      //   });

      // //mysql关系查询
      // this.$axios
      //   .get("/relation/popular", {
      //     // source: form.source,
      //     // target: form.target,
      //     // name: form.name,
      //     // times: form.times,
      //     params: {
      //       genre: genre,
      //       // page: 1,
      //       // per_page: 5,
      //     }
      //   })
      //   .then((res) => {
      //     console.log(res);
      //     this.result = res.data;
      //     console.log("mysql结果", this.result);
      //     this.mysql_speed = res.consuming_time;
      //     this.isLoading = false;
      //   })
      //   .catch((err) => {
      //     this.$message.error("当前mysql网络异常，请稍后再试");
      //   });

      //neo4j关系查询
      this.$axios
        .get("/neo4j/actors/most-attracted-group-2", {
          params: {
            genre: genre
          }
          // source: form.source,
          // target: form.target,
          // name: form.name,
          // times: form.times,
          // page: 1,
          // per_page: 5,
        })
        .then((res) => {
          console.log(res);
          console.log("我跑通啦");
          this.neo4j_speed = res.time;
        })
        .catch((err) => {
          this.$message.error("当前neo4j网络异常，请稍后再试");
        });

      //hive关系查询
      this.$axios
        .get("/hive/relation/popular", {
          params: {
            genre: genre,
          }
        })
        .then((res) => {
          console.log(res);
          console.log("我跑通啦");
          this.spark_speed = res.consuming_time;
          this.isLoading=false
          // this.result = response.data || [];
        })
        .catch((err) => {
          this.$message.error("当前spark网络异常，请稍后再试");
        });
    },


    echartsInit() {
      //使用时只需要把setOption里的对象换成echarts中的options或者自己的参数即可
      console.log("开始初始化");
      this.$echarts.init(document.getElementById("speed")).setOption({
        title: {
          text: "关系查询耗时对比(s)",
        },
        tooltip: {},
        xAxis: {
          data: ["neo4j", "hive"],
        },
        yAxis: {},
        series: [
          {
            name: "查询耗时(s)",
            type: "bar",
            // data: [this.mysql_speed, this.neo4j_speed, this.spark_speed],
            data: [this.neo4j_speed, this.spark_speed],
          },
        ],
      });
    },
  },
};
</script>

<style scoped>
.el-divider--vertical {
  height: 75vh;
}
</style>
