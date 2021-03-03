<template>

  <div>
    <Header v-on:persondis="getPersonDis">
    </Header>
    <el-container>
      <el-aside width="200px">
        <el-col :span="12">
          <el-menu
              :uniqueOpened="true"
              default-active="2"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b">
            <el-submenu index="1">
              <template #title>
                <i class="el-icon-user"></i>
                <span>个人简历</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="1-1">简历管理</el-menu-item>
                <el-menu-item index="1-2">发布简历</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template #title>
                <i class="el-icon-office-building"></i>
                <span>求职信息</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="2-1">企业信息</el-menu-item>
                <el-menu-item index="2-2">就业政策</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="3">
              <template #title>
                <i class="el-icon-tickets"></i>
                <span>就业信息</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="3-1">资料登记</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

          </el-menu>
        </el-col>
      </el-aside>
      <el-container class="containor">
        <div class="block">
          <el-carousel height="70px">
            <el-carousel-item v-for="item in 4" :key="item">
              <h3 class="small">{{ blogs[item-1] }}</h3>
            </el-carousel-item>
          </el-carousel>
        </div>
        <el-main>
          <PersonDetail v-if="!person" v-on:back="getPersonDis"></PersonDetail>
          <UpdatePwd>123</UpdatePwd>
        </el-main>
        <el-footer class="footer">版权所有©五邑大学-梁卓林</el-footer>
      </el-container>
    </el-container>
  </div>

</template>

<script>
import Header from "../components/Header";
import PersonDetail from "../components/PersonDetail";
import UpdatePwd from "../components/UpdatePwd";
export default {
  name: "Student",
  components: {Header,PersonDetail,UpdatePwd},
  data(){
    return {
      blogs:["生活就像海洋，只有意志坚强的人才能到达彼岸",
            "最值得学习的博客项目eblog",
            "博客项目eblog讲解视频上线啦，长达17个小时！！",
            "真正理解Mysql的四种隔离级别@"],
      person: true
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    getPersonDis(data){
      this.person = data;
      console.log(this.person+"____"+data)
    }
  }

}
</script>

<style scoped>
.el-header{
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  /*background-color: #D3DCE6;*/
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 688px;
  max-height: 689px;
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
.el-menu-vertical-demo{
  width: 199px;
  height: 808px;
  max-height: 808px;
  text-align: left;
}
.footer{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 34px;
  margin-top: 16px;
  max-height: 34px;
}
.containor{
  background: #E9EEF3;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 20px;
  opacity: 0.75;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.block{

}

</style>
