<template>

  <div>
    <Cheader v-on:choose="choose">
    </Cheader>
    <el-container>
      <Caside v-on:choose="choose"></Caside>
      <el-container class="containor">
        <Logs></Logs>
        <el-main>
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
import Cheader from "../components/Cheader";
import Caside from "../components/Caside";
import Logs from "../components/Logs";
export default {
  name: "Company",
  components: { Cheader,Caside,Logs
  },
  data(){
    return {
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
  height: 688px;
  max-height: 689px;
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
  /*margin-top: 16px;*/
  max-height: 34px;
}


</style>
