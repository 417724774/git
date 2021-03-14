<template>

  <div class="header">
    <div class="header-left">
      <img class="headerlogo" src="../assets/headerlogo.png" alt="">
    </div>

    <div class="header-right">
      <div class="avator1">
        <el-link :underline="false">
          <el-avatar title="头像" class="avator" @click.native="avatar" :src="avatarUrl"></el-avatar>
        </el-link>
<!--        <el-link class="username" v-if="dis" style="color: #ffffff" href="">{{ user.name }}</el-link>-->
        <el-dropdown class="dropdown" >
        <span class="el-dropdown-link" >
          {{ user.username }}
        </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click.native="choose('teacher_detail')" >个人中心</el-dropdown-item>
              <el-dropdown-item @click.native="choose('teacher_uppwd')">修改密码</el-dropdown-item>
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
  name: "Theader",
  props:{

  },
  data(){
    return {
      user:{
        username:'',
        avator:'',
      },
      persondis:false,
      avatarUrl: this.$store.getters.getUser.avatar
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
        // clearInterval(this.timer)
      }).catch((e)=>{
        console.log(e.toString())
      });
    },
    choose(data){
      this.$emit('choose',data)
    },
    avatar(){
      this.$router.push('/teacher_avatar')
    }
  },
    created(){
      if(this.$store.getters.getUser.username !== null){
        this.user.username = this.$store.getters.getUser.username
        this.user.avator = this.$store.getters.getUser.avator
      }
      // if(this.timer){
      //   clearInterval(this.timer)
      // }else{
      //   this.timer=setInterval(()=>{
      //     //获取数据
      //     const _this = this
      //     const num =
      //         _this.$axios.get('/teacher/messagelogs').then(res=>{
      //
      //
      //           console.log(1)
      //           // console.log(res.data.data[Math.floor(Math.random() * (3 - 0 + 1)) + 0].tmTitle)
      //
      //         })
      //   },3000)
      // }
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