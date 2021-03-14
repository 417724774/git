<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
        </div>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="110px" class="demo-ruleForm">
          <el-form-item label="职位性质：" prop="tmType">
            <el-select v-model="ruleForm.tmType" placeholder="选择类型">
              <el-option label="宣传" value="宣传"></el-option>
              <el-option label="通知" value="通知"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="标题：" prop="tmTitle">
            <el-input v-model="ruleForm.tmTitle"></el-input>
          </el-form-item>
          <el-form-item label="正文：" prop="tmContent">
            <el-input autosize type="textarea" v-model="ruleForm.tmContent"></el-input>
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
  name: "Teacher_message_add",
  components: {
  },
  data() {
    return {
      ruleForm: {
        tmContent: '',
        tmTitle: '',
        tmType: '',
        tmMan:this.$store.getters.getUser.username,
        tmUserid:this.$store.getters.getUser.userId,
        tmPtime: ''
      },
      rules: {
        tmTitle: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        tmType: [
          { required: true, message: '请选择类型', trigger: 'blur' },

        ],
        tmContent: [
          { required: true, message: '请输入正文', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          const _this = this
          this.$axios.post('/teacher/messageadd',this.ruleForm,{
            headers: {
              Authorization: localStorage.getItem('token')
            }
          }).then(res => {
            if(res.data.code === 200){
              alert("添加成功！")
            }else {
              alert("添加失败！请再次尝试！")
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
