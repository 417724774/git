<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

          <el-form-item label="姓名" prop="seName">
            <el-input readonly v-model="studentinfo.sname"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="seSex">
            <el-input readonly v-model="studentinfo.ssex"></el-input>
          </el-form-item>
          <el-form-item label="专业" prop="seMajor">
            <el-input readonly v-model="ruleForm.seMajor = studentinfo.smajor"></el-input>
          </el-form-item>
          <el-form-item label="毕业院校" prop="seSchool">
            <el-input readonly v-model="ruleForm.seSchool = '五邑大学'" ></el-input>
          </el-form-item>
          <el-form-item label="目标岗位" prop="seIntention">
            <el-input  v-model="ruleForm.seIntention"></el-input >
          </el-form-item>
          <el-form-item label="联系地址" prop="seLocation">
            <el-input v-model="ruleForm.seLocation"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="sePhone">
            <el-input v-model="ruleForm.sePhone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="seEmail">
            <el-input v-model="ruleForm.seEmail"></el-input>
          </el-form-item>
          <el-form-item label="证书" prop="seCerdificate">
            <el-input autosize type="textarea" v-model="ruleForm.seCerdificate"></el-input >
          </el-form-item>
          <el-form-item label="自我介绍" prop="seIntroduction">
            <el-input autosize type="textarea" style="width: 700px" v-model="ruleForm.seIntroduction"></el-input >
          </el-form-item>
          <el-form-item label="工作经验" prop="seExprience">
            <el-input autosize type="textarea" style="width: 700px" v-model="ruleForm.seExprience"></el-input >
          </el-form-item>
          <el-form-item label="教育经历" prop="seEducation">
            <el-input autosize type="textarea" style="width: 700px" v-model="ruleForm.seEducation"></el-input >
          </el-form-item>
          <el-form-item label="培训经历" prop="seTrain">
            <el-input autosize type="textarea" style="width: 700px" v-model="ruleForm.seTrain"></el-input >
          </el-form-item>


          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">完善确认</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Student_resume",
  components: {
  },
  data() {
    return {
      ruleForm: {},
      studentinfo: {},

      rules: {
        seEmail: [
          { type:'email', required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        seLocation: [
          { required: true, message: '请输入联系地址', trigger: 'blur' },
        ],
        seExprience: [
          { required: true, message: '请输入工作经历', trigger: 'blur' }
        ],
        seEducation: [
          { required: true, message: '请输入教育经历', trigger: 'change' }
        ],
        seIntroduction: [
          { required: true, message: '请输入自我介绍', trigger: 'change' },
          { min: 1, max: 130, message: '长度在 1 到 130 个字符', trigger: 'blur' }
        ],
        seCerdificate: [
          { required: true, message: '请输入证书', trigger: 'blur' }
        ],
        seIntention: [
          { required: true, message: '请输入目标岗位', trigger: 'blur' }
        ],
        sePhone: [
          { required: true, message: '请输入联系电话', trigger: 'change' },
          { min: 11, max: 11, message: '请输入 11 位手机号码', trigger: 'blur' }
        ],
        seTrain: [
          { required: true, message: '请输入培训经历', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          const _this = this
          _this.ruleForm.seStuid = _this.$store.getters.getUser.userId
          this.$axios.post('/stuempinfo/stuemupdate',this.ruleForm,{
            headers: {
              Authorization: localStorage.getItem('token')
            }
          }).then(res => {
            if(res.data.code === 200){

              this.$notify({
                title: '完善简历成功！',
                type: 'success'
              })
            }else {
              this.$notify.error({
                title: '完善失败！请再次尝试！'
              })
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
      this.$router.back()
    },
    close(){
      this.$router.push('/student')
    },
    get(){
      const sid = this.$store.getters.getUser.userId
      const _this = this
      _this.$axios.post("/stuempinfo/studentresume",{"seStuid":sid},{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        //console.log(res.data.data)
        if(res.data.code === 200){
          _this.ruleForm = res.data.data
        }else {
          console.log(res.data.data)
        }
      })

      _this.$axios.post("/student/studentinfo",{"suserid":sid},{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        _this.studentinfo = res.data.data
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
  margin: 0 auto;
  margin-top: 30px;
}


</style>
