<template>

  <div>
    <Sheader v-on:choose="choose">
    </Sheader>
    <el-container>
      <Saside v-on:choose="choose"></Saside>
      <el-container class="containor">
        <Logs></Logs>
        <el-main style="height: 682px">
          <div id="app">
            <router-view></router-view>

          </div>
        </el-main>

        <el-footer class="footer">版权所有©五邑大学-梁卓林</el-footer>
      </el-container>

    </el-container>
  </div>

</template>

<script>
import Sheader from "../components/Sheader";
import Saside from "../components/Saside";
import Logs from "../components/Logs";
export default {
  name: "Student",
  components: { Sheader,Saside,Logs
  },
  data(){
    return {
      blogs:["生活就像海洋，只有意志坚强的人才能到达彼岸",
        "最值得学习的博客项目eblog",
        "博客项目eblog讲解视频上线啦，长达17个小时！！",
        "真正理解Mysql的四种隔离级别@"],
      isRouterAlive:true

    }
  },
  computed:{

  },
  methods: {
    choose(data){
      this.$router.push('/'+data)
    }
  }
}
</script>

<style scoped>
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  /*line-height: 160px;*/
  padding: unset;
  margin-top: 16px;
}
body > .el-container {
  margin-bottom: 40px;
}
.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}
.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.containor{
  background: #E9EEF3;
}
.footer{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 34px;
  max-height: 34px;
}


</style>
