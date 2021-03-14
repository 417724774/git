<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

          <el-form-item label="审核状态：" prop="cjStatus">
            <h3 style="margin-top: unset ;" >
              <span v-if="dis" style="color: green"> {{ruleForm.cjStatus}}</span>
              <span v-if="!dis" style="color: red"> {{ruleForm.cjStatus}}</span>
            </h3>
          </el-form-item>
          <el-form-item label="职位类型：" prop="cjType">
            <el-input v-model="ruleForm.cjType"></el-input>
          </el-form-item>
          <el-form-item label="职位性质：" prop="cjProperty">
            <el-select v-model="ruleForm.cjProperty" placeholder="选择性质">
              <el-option label="全职" value="全职"></el-option>
              <el-option label="实习" value="实习"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="有效时间：" prop="cjDeadline">
            <div class="block">
              <el-date-picker
                  v-model="ruleForm.cjDeadline"
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="工作地点：" prop="cjAdress">
            <el-input v-model="ruleForm.cjAdress"></el-input>
          </el-form-item>
          <el-form-item label="薪酬：" prop="cjSalary">
            <el-input v-model="ruleForm.cjSalary"></el-input>
          </el-form-item>
          <el-form-item  label="年龄要求：" prop="cjAge">
            <el-input style="width: 80px;margin-right: 2%" v-model="ruleForm.cjAge"></el-input> 岁以上
          </el-form-item>
          <el-form-item label="职位描述：" prop="cjDescription">
            <el-input autosize type="textarea" style="width: 600px" v-model="ruleForm.cjDescription"></el-input >
          </el-form-item>
          <el-form-item label="基本要求：" prop="cjDemand">
            <el-input autosize type="textarea" style="width: 600px" v-model="ruleForm.cjDemand"></el-input >
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
  name: "Company_job_detail",
  components: {
  },
  data() {
    return {
      disabledDate(time) {
        return time.getTime() > Date.now()
      },
      ruleForm: {
      },
      rules: {
        cjType: [
          { required: true, message: '请输入职位类型', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        cjDeadline: [
          { required: true, message: '请选择有效时间', trigger: 'blur' },

        ],
        cjAdress: [
          { required: true, message: '请输入工作地点', trigger: 'blur' }
        ],
        cjSalary: [
          { required: true, message: '请输入薪酬', trigger: 'blur' }
        ],
        cjAge: [
          { required: true, message: '请输入年龄要求', trigger: 'blur' },
        ],
        cjDescription: [
          { required: true, message: '请输入职位描述', trigger: 'blur' }
        ],
        cjDemand: [
          { required: true, message: '请输入基本要求', trigger: 'blur' }
        ],
        cjProperty: [
          { required: true, message: '请输入职位性质', trigger: 'change' }
        ]
      },
      currentPage: 1,
      total: 0,
      pageSize: 5,
      dis:''
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          _this.ruleForm.cjPtime = _this.$store.getters.getDate
          this.$axios.post('/company/jobsaveorupdate',this.ruleForm,{
            headers: {
              Authorization: localStorage.getItem('token')
            }
          }).then(res => {
            if(res.data.code === 200){
              alert("完善招聘信息成功！")
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
      this.$router.push('/company')
    },
    get(){
      const sid = this.$route.params.id
      this.$axios.post("/company/jobdetail",sid,{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        //console.log(res.data.data)
        this.ruleForm = res.data.data

        if(this.ruleForm.cjStatus === "未审核"||this.ruleForm.cjStatus === "未通过"){

          this.dis = false
        }
        else {
          this.dis = true
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
  height:auto;
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
  clear: both;
}


</style>
