<template>

  <div>
    <el-container>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

          <el-form-item label="姓名" prop="seName">
            <el-input v-model="ruleForm.seName"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="seSex">
            <el-select v-model="ruleForm.seSex" placeholder="选择性别">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="毕业院校" prop="seSchool">
            <el-input v-model="ruleForm.seSchool"></el-input>
          </el-form-item>
          <el-form-item label="目标岗位" prop="seIntention">
            <el-input  v-model="ruleForm.seIntention"></el-input >
          </el-form-item>
          <el-form-item label="居住地" prop="seAddress">
            <el-input v-model="ruleForm.seAddress"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="seLocation">
            <el-input v-model="ruleForm.seLocation"></el-input>
          </el-form-item>
          <el-form-item label="邮编" prop="sePostcode">
            <el-input v-model="ruleForm.sePostcode"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="sePhone">
            <el-input v-model="ruleForm.sePhone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="seEmail">
            <el-input v-model="ruleForm.seEmail"></el-input>
          </el-form-item>
          <el-form-item label="证书" prop="seCerdificate">
            <el-input autosize type="textarea" v-model="ruleForm.seCerdificate"></el-input >
          </el-form-item>
          <el-form-item label="自我介绍" prop="seIntroduction">
            <el-input autosize type="textarea" style="width: 700px" v-model="ruleForm.seIntroduction"></el-input >
          </el-form-item>
          <el-form-item label="工作经验" prop="seExprience">
            <el-input autosize type="textarea" style="width: 700px" v-model="ruleForm.seExprience"></el-input >
          </el-form-item>
          <el-form-item label="教育经历" prop="seEducation">
            <el-input autosize type="textarea" style="width: 700px" v-model="ruleForm.seEducation"></el-input >
          </el-form-item>
          <el-form-item label="培训经历" prop="seTrain">
            <el-input autosize type="textarea" style="width: 700px" v-model="ruleForm.seTrain"></el-input >
          </el-form-item>


          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">完善确认</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
            <el-button @click="backindex">返回</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>

</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        seName:'',
        seSex: '',
        seEmail:'',
        seAddress: '',
        seLocation: '',
        sePostcode: '',
        seIntroduction:'',
        seExprience: '',
        seEducation: '',
        seTrain: '',
        seCerdificate:'',
        seIntention:'',
        seSchool:''
      },

      rules: {
        seName: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 1, max: 11, message: '长度在 1 到 11 个字符', trigger: 'blur' }
        ],
        seSex: [
          { required: true, message: '请选择性别', trigger: 'blur' },

        ],
        seAddress: [
          { required: true, message: '请输入居住地', trigger: 'blur' }
        ],
        seEmail: [
        { type:'email', required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        seLocation: [
          { required: true, message: '请输入联系地址', trigger: 'blur' },
        ],
        sePostcode: [
          { required: true, message: '请输入邮编', trigger: 'blur' }
        ],
        seExprience: [
          { required: true, message: '请输入工作经历', trigger: 'blur' }
        ],
        seEducation: [
          { required: true, message: '请输入教育经历', trigger: 'change' }
        ],
        seIntroduction: [
          { required: true, message: '请输入自我介绍', trigger: 'change' },
          { min: 1, max: 130, message: '长度在 1 到 130 个字符', trigger: 'blur' }
        ],
        seCerdificate: [
          { required: true, message: '请输入证书', trigger: 'blur' }
        ],
        seIntention: [
          { required: true, message: '请输入目标岗位', trigger: 'blur' }
        ],
        seSchool: [
          { required: true, message: '请输入毕业院校', trigger: 'blur' }
        ]
      },
      back:true
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          const _this = this
          this.$axios.post('/stuempinfo/stuemupdate',this.ruleForm).then(res => {
            if(res.data.code === 200){
              alert("完善简历成功！")
            }else {
              alert("完善失败！请重新尝试！")
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
    backindex(){
      this.$emit("back",this.back)
    }

  },
  created() {
    const sid = this.$store.getters.getUser.userId
    this.$axios.post("/stuempinfo/studentresume",{"seStuid":sid}).then(res=>{
      //console.log(res.data.data)
      this.ruleForm = res.data.data
    })
  }
}
</script>

<style scoped>


.el-main {
  /*background-color: #E9EEF3;*/
  color: #333;
  text-align: center;
}

body > .el-container {
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

}

.h1{
  margin-left: 35px;
}

</style>