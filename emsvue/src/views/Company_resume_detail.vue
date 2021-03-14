<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
        </div>
        <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">

          <el-form-item label="姓名：" prop="seName" style="text-align: left">
            <el-input readonly v-model="ruleForm.seName"></el-input>
          </el-form-item>
          <el-form-item label="性别：" prop="seSex" style="text-align: left">
            <el-input readonly v-model="ruleForm.seSex"></el-input>
          </el-form-item>
          <el-form-item label="毕业院校：" prop="seSchool" style="text-align: left">
            <el-input readonly v-model="ruleForm.seSchool"></el-input>
          </el-form-item>
          <el-form-item label="目标岗位：" prop="seIntention" style="text-align: left">
            <el-input readonly v-model="ruleForm.seIntention"></el-input >
          </el-form-item>
          <el-form-item label="联系地址：" prop="seLocation" style="text-align: left">
            <el-input readonly v-model="ruleForm.seLocation"></el-input>
          </el-form-item>
          <el-form-item label="专业：" prop="seMajor" style="text-align: left">
            <el-input readonly v-model="ruleForm.seMajor"></el-input>
          </el-form-item>
          <el-form-item label="联系电话：" prop="sePhone" style="text-align: left">
            <el-input readonly v-model="ruleForm.sePhone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱：" prop="seEmail" style="text-align: left">
            <el-input readonly v-model="ruleForm.seEmail"></el-input>
          </el-form-item>
          <el-form-item label="证书：" prop="seCerdificate" style="text-align: left">
            <el-input readonly autosize type="textarea" v-model="ruleForm.seCerdificate"></el-input >
          </el-form-item>
          <el-form-item label="自我介绍：" prop="seIntroduction" style="text-align: left">
            <el-input readonly autosize type="textarea" style="width: 700px" v-model="ruleForm.seIntroduction"></el-input >
          </el-form-item>
          <el-form-item label="工作经验：" prop="seExprience" style="text-align: left">
            <el-input readonly autosize type="textarea" style="width: 700px" v-model="ruleForm.seExprience"></el-input >
          </el-form-item>
          <el-form-item label="教育经历：" prop="seEducation" style="text-align: left">
            <el-input readonly autosize type="textarea" style="width: 700px" v-model="ruleForm.seEducation"></el-input >
          </el-form-item>
          <el-form-item label="培训经历：" prop="seTrain" style="text-align: left">

            <el-input readonly autosize type="textarea" style="width: 700px" v-model="ruleForm.seTrain"></el-input >
          </el-form-item>

        </el-form>
      </el-main>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Company_resume_detail",
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
      this.$router.push('/company')
    }

  },
  created() {
    const sid = this.$route.params.id
    this.$axios.post("/stuempinfo/studentresume",{"seStuid":sid},{
      headers: {
        Authorization: localStorage.getItem('token')
      }
    }).then(res=>{
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
  clear: both;
}


</style>
