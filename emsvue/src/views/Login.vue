<template>

  <div>
    <el-container>
      <el-header style="height: 100px">
        <img class="hlogo" src="../assets/logo.png" alt="WYU UNIVERSITY!">
      </el-header>
      <el-main>
        <h1 class="h1">毕业生就业管理信息系统</h1>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="userId">
            <el-input v-model="ruleForm.userId"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item >
            <el-input v-model="ruleForm.code" placeholder="- - - -" prop="code">
              <template slot="prepend">验证码</template>
              <template slot="append">
                <div class="login-code" @click="refreshCode">
                  <Identify :identifyCode="identifyCode"></Identify>
                </div>
              </template>
            </el-input>
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
import Identify from "../components/Identify";
export default {
  components:{Identify},
  name: "Login",
  data() {
    return {
      ruleForm: {
        userId: '311700',
        password: '123456',
        code:''
      },
      rules: {
        userId: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
        ],
        code: [
          { required: true, message: "请输入验证码", trigger: "blur" }
        ]
      },
      dis:true,
      identifyCodes: '1234567890abcdefghijklmnopqrstuvwxyz',
      identifyCode: ''
    };
  },
  methods: {
    refreshCode () {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode (o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[this.randomNum(0, this.identifyCodes.length)]
      }
    },
    randomNum (min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    submitForm(formName) {
      if (this.ruleForm.code.toLowerCase() !== this.identifyCode.toLowerCase()) {
        this.$message.error('请填写正确验证码')
        this.refreshCode()
        return
      }
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
            if(res.data.code === 200) {
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

                  _this.$router.push("/teacher");
                  break;
                }

                case "管理员": {

                  _this.$router.push("/administrator");
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

  },
  mounted() {
    // 初始化验证码
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)
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
  margin-top: 10px;
  height: 80%;

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