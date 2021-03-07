<template>

  <div class="header">
    <div class="header-left">
      <img class="headerlogo" src="../assets/headerlogo.png" alt="">
    </div>

    <div class="header-right">
      <div class="avator1">
        <el-avatar class="avator" :src="user.avator"></el-avatar>
<!--        <el-link class="username" v-if="dis" style="color: #ffffff" href="">{{ user.name }}</el-link>-->
        <el-dropdown class="dropdown" >
        <span class="el-dropdown-link" >
          {{ user.username }}
        </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click.native="choose('student_detail')" >个人中心</el-dropdown-item>
              <el-dropdown-item @click.native="choose('student_uppwd')">修改密码</el-dropdown-item>
              <el-dropdown-item @click.native="logout">注销</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "Sheader",
  props:{

  },
  data(){
    return {
      user:{
        username:'',
        avator:'',
      },
      persondis:false
    }
  },
  methods:{
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
    },
    choose(data){
      this.$emit('choose',data)
    }
  },
    created(){
      if(this.$store.getters.getUser.username !== null){
        this.user.username = this.$store.getters.getUser.username
        this.user.avator = this.$store.getters.getUser.avator
      }
    }

}
</script>

<style scoped>

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
.el-icon-arrow-down {
  font-size: 12px;
}
.dropdown {

}
/*.username {*/

/*}*/

</style>