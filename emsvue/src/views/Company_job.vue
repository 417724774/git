<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="margin-right: 1580px; background-color: #6c6c6c"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c"></el-button>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

          <el-form-item label="职位类型" prop="seName">
            <el-input v-model="ruleForm.seName"></el-input>
          </el-form-item>
          <el-form-item label="职位性质" prop="seSex">
            <el-select v-model="ruleForm.seSex" placeholder="选择性质">
              <el-option label="全职" value="全职"></el-option>
              <el-option label="实习" value="实习"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="有效时间" prop="seSchool">
            <div class="block">
              <el-date-picker
                  v-model="value1"
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="目标岗位" prop="seIntention">
            <el-input  v-model="ruleForm.seIntention"></el-input >
          </el-form-item>
          <el-form-item label="工作地点" prop="seAddress">
            <el-input v-model="ruleForm.seAddress"></el-input>
          </el-form-item>
          <el-form-item label="薪酬" prop="seLocation">
            <el-input v-model="ruleForm.seLocation"></el-input>
          </el-form-item>
          <el-form-item label="年龄要求" prop="sePostcode">
            <el-input v-model="ruleForm.sePostcode"></el-input>
          </el-form-item>
          <el-form-item label="职位描述" prop="seIntroduction">
            <el-input autosize type="textarea" style="width: 700px" v-model="ruleForm.seIntroduction"></el-input >
          </el-form-item>
          <el-form-item label="基本要求" prop="seExprience">
            <el-input autosize type="textarea" style="width: 700px" v-model="ruleForm.seExprience"></el-input >
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
  name: "Company_job",
  components: {
  },
  data() {
    return {
      disabledDate(time) {
        return time.getTime() > Date.now()
      },
      value1: '',
      ruleForm: {
      },
      rules: {
        seName: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 1, max: 11, message: '长度在 1 到 11 个字符', trigger: 'blur' }
        ],
        seSex: [
          { required: true, message: '请选择性别', trigger: 'blur' },

        ],
        seAddress: [
          { required: true, message: '请输入居住地', trigger: 'blur' }
        ],
        seEmail: [
          { type:'email', required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        seLocation: [
          { required: true, message: '请输入联系地址', trigger: 'blur' },
        ],
        sePostcode: [
          { required: true, message: '请输入邮编', trigger: 'blur' }
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
        seIntention: [
          { required: true, message: '请输入目标岗位', trigger: 'blur' }
        ],
        seSchool: [
          { required: true, message: '请输入毕业院校', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          const _this = this
          this.$axios.post('/stuempinfo/stuemupdate',this.ruleForm).then(res => {
            if(res.data.code === 200){
              alert("完善简历成功！")
            }else {
              alert("完善失败！请再次尝试！")
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
      this.$router.push('/student_index')
    }

  },
  created() {
    const sid = this.$store.getters.getUser.userId
    this.$axios.post("/stuempinfo/studentresume",{"seStuid":sid}).then(res=>{
      //console.log(res.data.data)
      this.ruleForm = res.data.data
    })
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
  margin-top: 30px;
}


</style>
