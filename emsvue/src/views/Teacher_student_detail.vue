<template >

  <div>

              <el-container>
                <el-main>
                  <div style="width: 100%" >
                    <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
                    <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
                  </div>
                  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="学号：" prop="suserid">
                      {{ ruleForm.suserid }}
                    </el-form-item>
                    <el-form-item label="姓名：" prop="sname">
                      <el-input v-model="ruleForm.sname"></el-input>
                    </el-form-item>
                    <el-form-item label="专业：" prop="smajor">
                      <el-input v-model="ruleForm.smajor"></el-input>
                    </el-form-item>
                    <el-form-item label="性别：" prop="ssex">
                      <el-select v-model="ruleForm.ssex" placeholder="选择性别">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="学制：" prop="ses">
                      <el-input v-model="ruleForm.ses"></el-input>
                    </el-form-item>
                    <el-form-item label="学部：" prop="scollege">
                      <el-input v-model="ruleForm.scollege"></el-input>
                    </el-form-item>
                    <el-form-item label="班级：" prop="sclass">
                      <el-input v-model="ruleForm.sclass"></el-input>
                    </el-form-item>
                    <el-form-item label="毕业日期：" prop="syog">
                      <el-input  v-model="ruleForm.syog"></el-input >
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
  name: "Teacher_student_detail",
  components: {
  },
  data() {
    return {
      ruleForm: {
        sname: '',
        smajor: '',
        ssex: '',
        ses: '',
        sclass: '',
        scollege: '',
        syog:'',
        suserid:this.$route.params.id
      },

      rules: {
        sname: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
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
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          const _this = this
          this.$axios.post('/student/studentupper',this.ruleForm,{
            headers: {
              Authorization: localStorage.getItem('token')
            }
          }).then(res => {
            if(res.data.code === 200){
              alert("修改学生信息成功！")
            }else {
              alert("修改学生信息失败！请再次尝试！")
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
    }

  },
  created() {
    const sid = this.$route.params.id
    this.$axios.post("/student/studentinfo",{"suserid":sid},{
      headers: {
        Authorization: localStorage.getItem('token')
      }
    }).then(res=>{
      this.ruleForm = res.data.data
      this.ruleForm.syog = res.data.data.sYog
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
  margin-top: 50px;
}


</style>
