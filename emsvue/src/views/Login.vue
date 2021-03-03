<template>

  <div>
    <el-container>
      <el-header style="height: 100px">
        <img class="hlogo" src="../assets/logo.png" alt="WYU UNIVERSITY!">
      </el-header>
      <el-main>
        <h1 class="h1">毕业生就业管理系统</h1>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item prop="radio1">
            <el-radio-group v-model="ruleForm.radio1"  class="radio1">
              <el-radio-button label="学生"@click.native="choose1"></el-radio-button>
              <el-radio-button label="教师"@click.native="choose2"></el-radio-button>
              <el-radio-button label="企业" @click.native="choose3"></el-radio-button>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="用户名" prop="userId">
            <el-input v-model="ruleForm.userId"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
            <el-link class="register" v-show="!dis" href="/register">注册</el-link>
          </el-form-item>
        </el-form>
      </el-main>
      <el-footer>版权所有©五邑大学-梁卓林</el-footer>
    </el-container>
  </div>

</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        userId: '311700',
        password: '123456',
        radio1:''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
        ],
        radio1: [
          { required: true, message: '请选择身份', trigger: 'change' }
        ]
      },
      dis:true
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          const _this = this

          this.$axios.post('/user/login',this.ruleForm).then(res => {

            const jwt = res.headers['authorization']
            const userInfo = res.data.data
            //共享全局信息
            _this.$store.commit("SET_TOKEN",jwt)
            _this.$store.commit("SET_USERINFO",userInfo)

            //获取
            //console.log(_this.$store.getters.getUserInfo)

            //跳转
            _this.$router.push("/student")
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    choose3(){
        this.dis = false
    },choose2(){
      this.dis = true
    },choose1(){
      this.dis = true
    }

  }
}
</script>

<style scoped>

.el-header{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
  margin-top: 300px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  /*background-color: #E9EEF3;*/
  color: #333;
  text-align: center;
  line-height: 160px;
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

.hlogo{
  height: 100%;

}

.demo-ruleForm{
  max-width: 500px;
  margin-left: 650px;
}

.h1{
  margin-left: 35px;
}

.radio1{
  margin: 0 auto;
}
.register{
  margin-left: 20px;
}

</style>