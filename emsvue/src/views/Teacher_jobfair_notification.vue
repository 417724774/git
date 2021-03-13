<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="auto" class="demo-ruleForm">
          <el-form-item label="主题：" prop="tnTitle">
            <span style="font-size: 25px">{{ this.$route.params.name }}</span>
          </el-form-item>
          <el-form-item label="提醒：" prop="man">
            <el-select v-model="ruleForm.man" placeholder="请选择">
              <el-option v-if="dis" label="已参与公司" value="1"></el-option>
              <el-option label="未参与公司" value="2"></el-option>
              <el-option v-if="dis" label="全部公司" value="3"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="正文：" prop="tnContent">
            <el-input autosize type="textarea" v-model="ruleForm.tnContent"></el-input>
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
  name: "Teacher_jobfair_notification",
  components: {
  },
  data() {
    return {
      ruleForm: {
        tnContent: '',

        tnMan:this.$store.getters.getUser.username,
        tnManid:this.$store.getters.getUser.userId,
        tnStatus:'未读',
        tnJfid: this.$route.params.id,
        tnTitle:this.$route.params.name,
        tnPtime:'',
        man: ''
      },
      man: '',
      rules: {
        man: [
          { required: true, message: '请选择', trigger: 'blur' },
        ],
        tnContent: [
          { required: true, message: '请输入正文', trigger: 'blur' }
        ]
      },
      dis:''
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          console.log(this.ruleForm.man)
          if (this.ruleForm.man === '1') {

            _this.$axios.post('/teacher/sendnotification1', _this.ruleForm).then(res => {

              if (res.data.code === 200) {
                alert("发送成功！")
              } else {
                alert("发生失败！请再次尝试！")
              }

            })

          }
          if (this.ruleForm.man === '2') {

            _this.$axios.post('/teacher/sendnotification2', _this.ruleForm).then(res => {

              if (res.data.code === 200) {
                alert("发送成功！")
              } else {
                alert("发生失败！请再次尝试！")
              }
            })
          }
          if (this.ruleForm.man === '3') {

            _this.$axios.post('/teacher/sendnotification3', _this.ruleForm).then(res => {

              if (res.data.code === 200) {
                alert("发送成功！")
              } else {
                alert("发生失败！请再次尝试！")
              }
            })
          }
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
      this.$router.push('/teacher_index')
    }
  },
  created() {
    const _this = this
    _this.$axios.get('/jion/anyonejion?jfid='+_this.$route.params.id).then(res=>{

      if(res.data.data > 0){

        _this.dis = true

      }else {

        _this.dis = false
      }

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
  margin-top: 30px;
}


</style>
