<template >

  <div>

              <el-container>
                <el-main>
                  <div style="width: 100%" >
                    <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
                    <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
                  </div>
                  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="姓名" prop="cname">
                      <el-input v-model="ruleForm.cname"></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="csex">
                      <el-select v-model="ruleForm.csex" placeholder="选择性别">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item  label="注册时间 ：" prop="cRtime">
                      <h3 style="margin-top: unset">{{ruleForm.cRtime}}</h3>
                    </el-form-item>
                    <el-form-item  label="账户状态 ：" prop="cstatus">
                      <h3 style="margin-top: unset ;" >
                        <span v-if="dis" style="color: green"> {{ruleForm.cstatus}}</span>
                        <span v-if="!dis" style="color: red"> {{ruleForm.cstatus}}</span>
                      </h3>
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
  name: "Company_detail",
  components: {
  },
  data() {
    return {
      ruleForm: {
        cname: '',
        csex: '',
        cRtime: '',
        cstatus: '',
        suserid:this.$store.getters.getUser.userId
      },
      rules: {
        cname: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ],
        csex: [
          { required: true, message: '请输入性别', trigger: 'blur' }
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
          this.$axios.post('/company/companyupper',this.ruleForm,{
            headers: {
              Authorization: localStorage.getItem('token')
            }
          }).then(res => {
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
      this.$router.push('/company')
    },
    get(){
      const sid = this.$store.getters.getUser.userId
      const _this = this
      this.$axios.post("/company/detail",{"cuserid":sid},{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        _this.ruleForm = res.data.data
      }).finally(()=>{

        if(_this.ruleForm.cstatus === "未审核"){

          _this.dis = false
        }else {
          _this.dis = true
        }
          }
      )
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
