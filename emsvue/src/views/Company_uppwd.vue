<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="新密码" prop="cpassword">
            <el-input type="password" v-model="ruleForm.cpassword"></el-input>
          </el-form-item>
          <el-form-item :rules="rules" label="确认新密码" prop="cpassword1">
            <el-input type="password" v-model="cpassword1"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Company_uppwd",
  components: {
  },
  data() {
    return {
      ruleForm: {
        cpassword: '',
        cuserid:this.$store.getters.getUser.userId
      },
      cpassword1:'',
      rules: {
        cpassword: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        cpassword1: [
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
          if(this.ruleForm.cpassword !==this.cpassword1){
            alert("两次密码不相同！")
            return false
          }else {
            const _this = this
            this.$axios.post('/company/companyuppwd',this.ruleForm).then(res => {
              if(res.data.code === 200){
                alert("修改密码成功！")
              }else {
                alert("修改密码失败！请再次尝试！")
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
      this.cpassword1 = '';
    },
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/company_index')
    }
  }

}
</script>

<style scoped>
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  /*line-height: 160px;*/
  height: 688px;
  max-height: 689px;
  padding: unset;
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
.demo-ruleForm{
  max-width: 500px;
  margin-left: 32%;
  margin-top: 100px;
}


</style>
