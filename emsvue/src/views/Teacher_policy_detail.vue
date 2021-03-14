<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="auto" class="demo-ruleForm">

          <el-form-item label="标题：" prop="ptitle">
            <el-input autosize style="width: 500px" type="textarea" v-model="ruleForm.ptitle"></el-input>
          </el-form-item>
          <el-form-item label="发布时间：" prop="pdate">
            <div class="block">
              <el-date-picker
                  v-model="ruleForm.pdate"
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="来源：" prop="psource">
            <el-input style="width: 500px" v-model="ruleForm.psource"></el-input>
          </el-form-item>
          <el-form-item label="正文：" prop="pcontent">
            <el-input autosize style="width: 500px" type="textarea" v-model="ruleForm.pcontent"></el-input>
          </el-form-item>
          <el-form-item >
            <el-button v-if="!dis" type="primary" @click="submitForm('ruleForm')">修改</el-button>
            <el-button v-if="!dis" @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Teacher_policy_detail",
  components: {
  },
  data() {
    return {
      disabledDate(time) {
        return time.getTime() > Date.now()
      },
      ruleForm: {
        ptitle:'',
        pdate:'',
        psource:'',
        pcontent:'',
        pman: this.$store.getters.getUser.username,
        pupdate: this.$store.getters.getDate
      },
      rules: {
        ptitle: [
          { required: true, message: '请输入政策标题', trigger: 'blur' },
          { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
        ],
        pdate: [
          { required: true, message: '请选择发布日期', trigger: 'blur' },

        ],
        psource: [
          { required: true, message: '请输入来源', trigger: 'blur' }
        ],
        pcontent: [
          { required: true, message: '请输入正文', trigger: 'blur' }
        ]
      },
      filtedjf:[],
      dis:''
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          const _this = this
          this.$axios.post('/teacher/policyupdate',this.ruleForm,{
            headers: {
              Authorization: localStorage.getItem('token')
            }
          }).then(res => {
            if(res.data.code === 200){
              alert("修改成功！")
            }else {
              alert("修改失败！请再次尝试！")
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
      this.$router.push('/teacher')
    },
    filter(){
      const _this = this
      const pid = _this.$route.params.pid
      const pman = _this.$store.getters.getUser.username
      _this.$axios.post('/teacher/policyfilter',{pman:pman},{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        _this.filtedjf = res.data.data
        for (const resKey in _this.filtedjf) {
          if(pid === _this.filtedjf[resKey]){
            _this.dis = false
            return
          }
        }
        _this.dis = true

      })
    },
    get(){
      const _this = this
      const pid = _this.$route.params.pid
      _this.$axios.get('/teacher/policydetail?pid='+pid,{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        if(res.data.code === 200){
          _this.ruleForm = res.data.data
        }else {
          alert("查询失败！")
        }
      })
    }
  },
  created() {
    this.get()
    this.filter()
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
