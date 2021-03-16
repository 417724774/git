<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="auto" class="demo-ruleForm">
          <el-form-item label="标题：" prop="smTitle">
            <el-input autosize type="textarea" v-model="ruleForm.smTitle"></el-input>
          </el-form-item>
          <el-form-item label="正文：" prop="smContent">
            <el-input autosize type="textarea" v-model="ruleForm.smContent"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Company_resume_send",
  components: {
  },
  data() {
    return {
      ruleForm: {
        smContent: '',
        smTitle:'',
        smMan:this.$store.getters.getUser.username,
        smManid:this.$store.getters.getUser.userId,
        smStatus:'未读',
        smPtime:'',
        smAccept: this.$route.params.id
      },
      rules: {
        smContent: [
          { required: true, message: '请输入正文', trigger: 'blur' }
        ],
        smTitle: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
            _this.$axios.post('/student/sendmessage', _this.ruleForm,{
              headers: {
                Authorization: localStorage.getItem('token')
              }
            }).then(res => {
              if (res.data.code === 200) {
                alert("发送成功！")
              } else {
                alert("发生失败！请再次尝试！")
              }
            })
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/company')
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
  height: auto;
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
  margin-top: 30px;
}


</style>
