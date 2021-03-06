<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="margin-right: 1600px; background-color: #6c6c6c"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c"></el-button>
        </div>
        <el-form :model="ruleForm"  ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="工作性质" prop="swPorperty">
            <el-select v-model="ruleForm.swPorperty" placeholder="请选择">
              <el-option label="实习" value="实习"></el-option>
              <el-option label="全职" value="全职"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="公司名称" prop="swName">
            <el-input v-model="ruleForm.swName"></el-input>
          </el-form-item>
          <el-form-item label="工作地点" prop="swAddress">
            <el-input v-model="ruleForm.swAddress"></el-input>
          </el-form-item>
          <el-form-item label="合同性质" prop="swAgreement">
            <el-input v-model="ruleForm.swAgreement"></el-input>
          </el-form-item>
          <el-form-item label="薪资" prop="swSalary">
            <el-input v-model="ruleForm.swSalary"></el-input>
          </el-form-item>
          <el-form-item label="合同期限" prop="swDeadline">
            <el-input style="width: 100px" v-model="ruleForm.swDeadline"></el-input>个月
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登记</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Student_register",
  components: {
  },
  data() {
    return {
      ruleForm: {
        swName: '',
        swAddress: '',
        swAgreement: '',
        swSalary: '',
        swPorperty: '',
        swDeadline: '',
      }
    };
  },
  methods:{
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
            const _this = this
            this.$axios.post('/studentworkupdate',this.ruleForm).then(res => {
              if(res.data.code === 200){
                alert("登记成功！")
              }else {
                alert("登记失败！请重新登记！")
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
  created(){
    const sid = this.$store.getters.getUser.userId
    this.$axios.post('/studentworkinfo',{swStuid:sid}).then(res => {
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
  margin-top: 100px;
}


</style>
