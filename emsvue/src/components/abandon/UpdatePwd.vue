<template>
  <div>
    <el-container>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="新密码" prop="spassword">
            <el-input type="password" v-model="ruleForm.spassword"></el-input>
          </el-form-item>
          <el-form-item :rules="rules" label="确认新密码" prop="spassword1">
            <el-input type="password" v-model="spassword1"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
            <el-button @click="backindex">返回</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "UpdatePwd",
  data() {
    return {
      ruleForm: {
        spassword: '',
        suserid:this.$store.getters.getUser.userId
      },
      back:true,
      spassword1:'',
      rules: {
        spassword: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        spassword1: [
          { required: true, message: '请再次输入密码', trigger: 'blur' },
          { }
        ]
      }
    };
  },
  methods:{
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.ruleForm.spassword !==this.spassword1){
            alert("两次密码不相同！")
            return false
          }else {
            const _this = this
            this.$axios.post('/student/studentuppwd',this.ruleForm).then(res => {
              if(res.data.code === 200){
                alert("修改密码成功！")
              }else {
                alert("修改密码失败！请重新修改！")
              }
            })
          }

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.spassword1 = '';
    },
    backindex(){
      this.$emit("back",this.back)
    }
  }
}
</script>

<style scoped>
.el-main {
  /*background-color: #E9EEF3;*/
  color: #333;
  text-align: center;
}

body > .el-container {
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}


.demo-ruleForm{
  max-width: 500px;
  margin-left: 32%;

}

.h1{
  margin-left: 35px;
}
</style>