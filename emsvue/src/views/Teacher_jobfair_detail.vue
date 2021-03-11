<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="110px" class="demo-ruleForm">

          <el-form-item label="招聘会标题：" prop="jfTitle">
            <el-input  v-model="ruleForm.jfTitle"></el-input>
          </el-form-item>
          <el-form-item label="举办日期：" prop="jfDate">
            <div class="block">
              <el-date-picker
                  v-model="ruleForm.jfDate"
                  value-format="yyyy-MM-dd"
                  type="date"
                  placeholder="选择日期">
              </el-date-picker>
            </div>
          </el-form-item>
          <el-form-item label="联系人：" prop="jfMan">
            <el-input v-model="ruleForm.jfMan"></el-input>
          </el-form-item>
          <el-form-item label="联系电话：" prop="jfPhone">
            <el-input v-model="ruleForm.jfPhone"></el-input>
          </el-form-item>
          <el-form-item label="举办地点：" prop="jfAdress">
            <el-input v-model="ruleForm.jfAdress"></el-input>
          </el-form-item>
          <el-form-item label="正文：" prop="jfBody">
            <el-input autosize type="textarea" v-model="ruleForm.jfBody"></el-input>
          </el-form-item>
          <el-button v-if="!dis" type="primary" @click="submitForm('ruleForm')">修改</el-button>
          <el-button v-if="!dis" @click="resetForm('ruleForm')">重置</el-button>
        </el-form>
      </el-main>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Teacher_jobfair_detail",
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
        jfTitle: [
          { required: true, message: '请输入招聘会标题', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        jfDate: [
          { required: true, message: '请选择举办日期', trigger: 'blur' },

        ],
        jfAdress: [
          { required: true, message: '请输入举办地点', trigger: 'blur' }
        ],
        jfMan: [
          { required: true, message: '请输入联系人', trigger: 'blur' }
        ],
        jfPhone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
          { min: 1, max: 11, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        jfBody: [
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
          _this.$axios.post('/teacher/jobfairsaveupadate',this.ruleForm).then(res => {
            if(res.data.code === 200){
              alert("修改成功！")
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
    filter(){
      const _this = this
      const jfid = _this.$route.params.id
      const jftuserid = _this.$store.getters.getUser.userId
      _this.$axios.post('/teacher/jobfairfilter',{jfTuserid:jftuserid}).then(res=>{
        _this.filtedjf = res.data.data
        for (const resKey in _this.filtedjf) {
          if(jfid === _this.filtedjf[resKey]){
            _this.dis = false
            return
          }
        }
        _this.dis = true

      })
    },
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/teacher_index')
    },
    get(){
      const _this = this
      const jfid = _this.$route.params.id
      _this.$axios.get("/teacher/jobfairdetail?jfid="+jfid).then(res=>{

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
