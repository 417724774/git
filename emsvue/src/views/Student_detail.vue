<template >

  <div>

              <el-container>
                <el-main>
                  <div style="width: 100%" >
                    <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
                    <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
                  </div>
                  <el-form :model="ruleForm"  ref="ruleForm" label-width="100px" class="demo-ruleForm" >
                    <el-form-item label="学号：" prop="" >
                      <el-input readonly v-model="this.$store.getters.getUser.userId"></el-input>
                    </el-form-item>
                    <el-form-item label="姓名：" prop="sname">
                      <el-input readonly v-model="ruleForm.sname"></el-input>
                    </el-form-item>
                    <el-form-item label="专业：" prop="smajor">
                      <el-input readonly v-model="ruleForm.smajor"></el-input>
                    </el-form-item>
                    <el-form-item label="性别：" prop="ssex">
                      <el-input readonly v-model="ruleForm.ssex"></el-input>
                    </el-form-item>
                    <el-form-item label="学制：" prop="ses">
                      <el-input readonly v-model="ruleForm.ses"></el-input>
                    </el-form-item>
                    <el-form-item label="学部：" prop="scollege">
                      <el-input readonly v-model="ruleForm.scollege"></el-input>
                    </el-form-item>
                    <el-form-item label="班级：" prop="sclass">
                      <el-input readonly v-model="ruleForm.sclass"></el-input>
                    </el-form-item>
                    <el-form-item label="毕业日期：" prop="syog">
                      <el-input readonly v-model="ruleForm.syog"></el-input >
                    </el-form-item>
                  </el-form>
                </el-main>
              </el-container>

  </div>

</template>

<script>
export default {
  name: "Student_detail",
  components: {
  },
  data() {
    return {
      ruleForm: {
      }
    };
  },
  methods: {
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/student')
    }

  },
  created() {
    const _this = this
    const sid = this.$store.getters.getUser.userId
    _this.$axios.post("/student/studentinfo",{"suserid":sid},{
      headers: {
        Authorization: localStorage.getItem('token')
      }
    }).then(res=>{
      _this.ruleForm = res.data.data
      _this.ruleForm.syog = res.data.data.sYog
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
