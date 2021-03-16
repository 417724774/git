<template>

  <div>
    <div class="header">
      <div class="header-left">
        <img class="headerlogo" src="../assets/headerlogo.png" alt="">
      </div>
      <div class="header-right">
        <div class="avator1">
          <el-avatar title="头像" class="avator" >
            <img class="headerlogo" src="../assets/admin.jpg" alt="">
          </el-avatar>
          <el-dropdown class="dropdown">
        <span class="el-dropdown-link" >
          {{ user.username }}
        </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click.native="logout">注销</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </div>
    </div>
    <el-container>
      <el-aside width="200px">
        <el-col :span="12">
          <el-menu
              :uniqueOpened="true"
              default-active="1"
              class="el-menu-vertical-demo"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b">
            <el-submenu index="1">
              <template #title>
                <i class="el-icon-user"></i>
                <span>用户管理</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="1-1" @click.native="choose('administrator_adduser')" >添加用户</el-menu-item>
                <el-menu-item index="1-2" @click.native="choose('administrator_userlist')" >修改用户</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

          </el-menu>
        </el-col>
      </el-aside>
      <el-container class="containor">

        <el-main style="height: 769px">
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
  name: "Administrator",
  components: { Sheader,Saside,Logs
  },
  data(){
    return {
      user:{
        username: this.$store.getters.getUser.username
      }

    }
  },
  computed:{

  },
  methods: {
    choose(data){
      this.$router.push('/'+data)
    },
    logout(){
      const _this = this
      _this.$axios.get('/user/logout',{
        headers: {
          Authorization: localStorage.getItem("token")
        }
      }).then((res) => {
        _this.$store.commit('REMOVE_INFO')
        _this.$router.push('/login')
      }).catch((e)=>{
        console.log(e.toString())
      });
    }
  },
  created() {
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
.header {

  background-image: url("../assets/headerbackground.png");
  height: 150px;
}

.header-left {
  width: 50%;
  position: absolute;
  height: 98px;
}

.header-right {
  width: 50%;
  height: 98px;
  margin-left: auto;
  text-align: right;
}

.headerlogo {
  max-width: 145px;
  max-height: 150px;
}

.avator {
  width: 100px;
  height: 100px;
  margin-top: 10px;
}
.avator1 {
  width: 100px;
  height: 150px;
  margin-left: 85%;
  text-align: center;

}
.el-dropdown-link {
  cursor: pointer;
  color: #ffffff;
}
.el-menu-vertical-demo{
  width: 199px;
  height: auto;
  text-align: left;
}
.el-aside {
  background-color: #545c64;
  color: #333;
  text-align: center;
  line-height: 200px;
  max-width: 100%;
}

</style>
