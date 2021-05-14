<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="auto" class="demo-ruleForm">
          <span style="font-size: 28px;text-align: center">修改用户</span>
          <el-form-item label="用户名：" prop="userId" style="margin-top: 50px">
            <el-input v-model="ruleForm.userId"></el-input>
          </el-form-item>
          <el-form-item label="密码：" prop="password">
            <el-input type="password" placeholder="123456" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item label="姓名：" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="身份：" prop="type">
            <el-select v-model="ruleForm.type" placeholder="选择身份">
              <el-option label="学生" value="学生"></el-option>
              <el-option label="教师" value="教师"></el-option>
              <el-option label="企业" value="企业"></el-option>
              <el-option label="管理员" value="管理员"></el-option>
            </el-select>
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
  name: "Administrator_updateuser",
  components: {
  },
  data() {
    return {
      ruleForm: {
      },
      rules:{
        userId: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        username: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        type: [
          { required: true, message: '请先择身份', trigger: 'blur' },
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          _this.$axios.post('/user/update',_this.ruleForm,{
            headers: {
              Authorization: localStorage.getItem('token')
            }
          }).then(res=>{

            if(res.data.code === 200){

              this.$notify({
                title: '修改成功！',
                type: 'success'
              })

            }else {
              this.$notify.error({
                title: '修改失败！'
              })

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
      this.$router.push('/administrator')
    },
    get(){
      const _this = this
      const id = _this.$route.params.id
      _this.$axios.get('/user/userdetail?id='+id,{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        if(res.data.code === 200){
          _this.ruleForm = res.data.data
        }else {
          console.log('查询失败！')
        }
      })
    }
  },
  created() {
    this.get()
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
