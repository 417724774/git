<template>
  <div style="width: 100%" >
    <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
    <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>

    <div style="clear: both;size: auto" >

      <el-avatar size="large" fit="scale-down" class="avator"  :src="avatarUrl" :key="avatarUrl"></el-avatar>

    </div>
    <div style="margin-top: 50px">
      <el-upload
          class="upload-demo"
          action="http://localhost:8080/user/materialPicture"
          :limit="1"
          :headers="headers"
          :before-upload="beforeUpload"
          :on-success="handleAvatarSuccess"
          :data="{userid:this.$store.getters.getUser.userId,avatar:this.$store.getters.getUser.avatar}"
      >
        <el-button align="buttom" >更换头像</el-button>
        <template #tip>
          <div class="el-upload__tip">只能上传 jpg/png 文件，且不超过 2M</div>
        </template>
      </el-upload>
    </div>

  </div>
</template>

<script>
export default {
  name: "Company_avatar",
  data(){
    return{
      avatarUrl: this.$store.getters.getUser.avatar,
      headers: {Authorization: localStorage.getItem('token')}
    }
  },
  methods:{

    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/company')
    },
    handleAvatarSuccess (response) {
      let self = this
      let resp = response
      if (resp.code === 200) {
          alert('上传头像成功！')
          const userInfo = this.$store.getters.getUser
          userInfo.avatar = resp.url
          this.$store.commit("SET_USERINFO",userInfo)
          this.$router.replace( "/test");
        return
      } else {
        self.$message.error(resp.msg)//显示后台信息
      }
    },
    beforeUpload (file) {
      const isLt2M = file.size < 1024 * 1024 * 2
      // console.log('大小' + isLt2M)
      if (!isLt2M) {
        this.$message.error('上传文件大小不能超过 2MB!')
      }
    }
  },
  created() {
  }
}
</script>

<style scoped>
.avator {
  width: 300px;
  height: 300px;
  margin-top: 50px;
}
</style>