<template >

  <div>

              <el-container>
                <el-main>
                  <div style="width: 100%" >
                    <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="margin-right: 93.8%; background-color: #6c6c6c"></el-button>
                    <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c"></el-button>
                  </div>
                  <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="姓名" prop="tname">
                      <el-input v-model="ruleForm.tname"></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="tsex">
                      <el-select v-model="ruleForm.tsex" placeholder="选择性别">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="学部" prop="tcollege">
                      <el-input v-model="ruleForm.tcollege"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话" prop="tphone">
                      <el-input v-model="ruleForm.tphone"></el-input>
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
  name: "Teacher_detail",
  components: {
  },
  data() {
    return {
      ruleForm: {
        tname: '',
        tcollege: '',
        tsex: '',
        tphone: '',
        suserid:this.$store.getters.getUser.userId
      },

      rules: {
        sname: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        ssex: [
          { required: true, message: '请输入性别', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const _this = this
          this.$axios.post('/teacher/teacherupper',this.ruleForm).then(res => {
            if(res.data.code === 200){
              alert("修改个人信息成功！")
            }else {
              alert("修改个人信息失败！请再次尝试！")
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
      this.$router.push('/teacher_index')
    }

  },
  created() {
    const tid = this.$store.getters.getUser.userId
    this.$axios.post("/teacher/teacherinfo",{"tuserid":tid}).then(res=>{
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
  margin-top: 50px;
}


</style>
