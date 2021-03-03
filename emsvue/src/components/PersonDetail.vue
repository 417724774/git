<template>

  <div>
    <el-container>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="suserid">
            <el-input v-model="ruleForm.suserid"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="spassword">
            <el-input type="password" v-model="ruleForm.spassword"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="sname">
            <el-input v-model="ruleForm.sname"></el-input>
          </el-form-item>
          <el-form-item label="专业" prop="smajor">
            <el-input v-model="ruleForm.smajor"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="ssex">
            <el-select v-model="ruleForm.ssex" placeholder="选择性别">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="学制" prop="ses">
            <el-input v-model="ruleForm.ses"></el-input>
          </el-form-item>
          <el-form-item label="学部" prop="scollege">
            <el-input v-model="ruleForm.scollege"></el-input>
          </el-form-item>
          <el-form-item label="班级" prop="sclass">
            <el-input v-model="ruleForm.sclass"></el-input>
          </el-form-item>
          <el-form-item label="毕业日期" prop="syog">
            <el-input  v-model="ruleForm.syog"></el-input >
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
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
  name: "Login",
  data() {
    return {
      ruleForm: {
        suserid:'',
        sname: '',
        spassword: '',
        smajor: '',
        ssex: '',
        ses: '',
        sclass: '',
        scollege: '',
        syog:''
      },

      rules: {
        suserid: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 5, max: 11, message: '长度在 5 到 11 个字符', trigger: 'blur' }
        ],
        sname: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        spassword: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        smajor: [
          { required: true, message: '请输入专业电话', trigger: 'blur' },
        ],
        ssex: [
          { required: true, message: '请输入性别', trigger: 'blur' }
        ],
        ses: [
          { required: true, message: '请输入学制', trigger: 'blur' }
        ],
        sclass: [
          { required: true, message: '请选择班级', trigger: 'change' }
        ],
        syog: [
          { required: true, message: '请选择毕业日期', trigger: 'blur' }
        ]
      },
      back:true
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          const _this = this
          this.$axios.post('/company/register',this.ruleForm).then(res => {
            if(res.data.code === 200){
              alert("申请注册成功！")
              //跳转
              _this.$router.push("/login")
            }else {
              alert("申请注册失败！请重新注册！")
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
    backindex(){
      this.$emit("back",this.back)
    }

  },
  created() {
    const sid = this.$store.getters.getUser.userId
    this.$axios.post("/student/studentinfo",{"suserid":sid}).then(res=>{
      this.ruleForm = res.data.data
      this.ruleForm.syog = res.data.data.sYog
      this.ruleForm.spassword = 'e10adc3949ba59abbe56e057f20f883e'
    })
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