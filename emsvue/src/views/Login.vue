<template>

  <div>
    <el-container>
      <el-header style="height: 100px">
        <img class="hlogo" src="../assets/logo.png" alt="WYU UNIVERSITY!">
      </el-header>
      <el-main>
        <h1 class="h1">毕业生就业管理系统</h1>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="userId">
            <el-input v-model="ruleForm.userId"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
            <el-link class="register" href="/company_register">企业注册</el-link>
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
        userId: '202100',
        password: '10010',
        radio1:''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
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
            if(res.data.data === 200) {
              //跳转
              switch (res.data.data.type) {
                case "学生": {
                  _this.$router.push("/student");
                  break;
                }

                case "企业": {
                  _this.$router.push("/company");
                  break;
                }

                case "教师": {

                  _this.$router.push("/Teacher");
                  break;
                }
              }
            }else {
              alert(res.data.msg)
            }

          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
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
  height: 790px;
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
  margin: 0 auto;
}

.h1{
  margin-left: 35px;
}
.register{
  margin-left: 20px;
}

</style>