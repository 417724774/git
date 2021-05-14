<template>

  <div>
    <el-container>
      <el-header style="height: 100px">
        <img class="hlogo" src="../assets/logo.png" alt="WYU UNIVERSITY!">
      </el-header>
      <div style="text-align: right">
        <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back">返回登录</el-button>
      </div>
      <el-main>
        <h1 class="h1">毕业生就业管理系统</h1>

        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" >
          <el-form-item label="用户名" prop="cuserid">
            <el-input v-model="ruleForm.cuserid"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="cpassword">
            <el-input type="password" v-model="ruleForm.cpassword"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="cname">
            <el-input v-model="ruleForm.cname"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="csex">
            <el-select v-model="ruleForm.csex" placeholder="选择性别">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="电话" prop="cphone">
            <el-input v-model="ruleForm.cphone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="cemail">
            <el-input v-model="ruleForm.cemail"></el-input>
          </el-form-item>
          <el-form-item label="公司名称" prop="cunit">
            <el-input v-model="ruleForm.cunit"></el-input>
          </el-form-item>
          <el-form-item label="公司地址" prop="cadress">
            <el-input v-model="ruleForm.cadress"></el-input>
          </el-form-item>
          <el-form-item label="公司性质" prop="cproperty">
            <el-input v-model="ruleForm.cproperty"></el-input>
          </el-form-item>
          <el-form-item label="公司规模" prop="csize">
            <el-select v-model="ruleForm.csize" placeholder="请选择公司规模">
              <el-option label="100人" value="100"></el-option>
              <el-option label="200人" value="200"></el-option>
              <el-option label="500人" value="500"></el-option>
              <el-option label="1000人" value="1000"></el-option>
              <el-option label="5000人" value="5000"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="公司简介" prop="cintroduction">
            <el-input type="textarea" autosize v-model="ruleForm.cintroduction"></el-input>
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
            <el-button type="primary" @click="submitForm('ruleForm')">申请注册</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
      <el-footer>版权所有©五邑大学-梁卓林</el-footer>
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
        cuserid:'',
        csex:'',
        cname: '',
        cpassword: '',
        cphone: '',
        cunit: '',
        cproperty: '',
        csize: '',
        cintroduction: '',
        cemail:'',
        crtime:this.$store.getters.getDate,
        cadress:'',
        code:''
      },
      rules: {
        cuserid: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 5, max: 11, message: '长度在 5 到 11 个字符', trigger: 'blur' }
        ],
        cname: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        cpassword: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        csex: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        cphone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
          { min: 11, max: 11, message: '长度在 11 个字符', trigger: 'blur' }
        ],
        cunit: [
          { required: true, message: '请输入公司名称', trigger: 'blur' }
        ],
        cproperty: [
          { required: true, message: '请输入公司性质', trigger: 'blur' }
        ],
        csize: [
          { required: true, message: '请选择公司规模', trigger: 'change' }
        ],
        cintroduction: [
          { required: true, message: '请填写公司简介', trigger: 'blur' }
        ],
        cemail: [
          { type:'email', required: true, message: '请填写邮箱', trigger: 'blur' }
        ],
        cadress: [
          {  required: true, message: '请填写公司地址', trigger: 'blur' }
        ],
        code: [
          { required: true, message: "请输入验证码", trigger: "blur" }
        ]
      },
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
          _this.$axios.post('/company/register',this.ruleForm).then(res => {
            if(res.data.code === 200){
              alert("申请注册成功！请等待邮件通知审核结果后方登录！")
              _this.send()
              //跳转
              _this.$router.push("/login")
            }else {
              alert("申请注册失败！请再次尝试！"+res.data.data)
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
    },
    back(){
      this.$router.push("/login")
    },
    send() {
      const _this = this
      _this.$axios.post('/company/sendemail',_this.ruleForm).then(res=>{
        if (res.data.code === 200){
          _this.$notify({
            title: '发送邮件成功！',
            type: 'success'
          })
        }
      })
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
  height: 50px;
}

.el-main {
  /*background-color: #E9EEF3;*/
  color: #333;
  text-align: center;
  height: 762px;
}

.el-container {
  margin: unset;
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
.el-footer{
  margin-top: unset;
}
.back{
}

</style>