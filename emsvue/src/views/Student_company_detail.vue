<template >

  <div>

              <el-container>

                <el-main>
                  <div style="width: auto" >

                    <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="margin-right: 93.8%; background-color: #6c6c6c"></el-button>
                    <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c"></el-button>
                  </div>
                  <div align="center">
                  <el-form :model="ruleForm"  ref="ruleForm" label-width="100px" class="demo-ruleForm" style="margin: 10px" >
                    <el-form-item label="公司名称" prop="cunit" >
                      <el-input readonly  v-model="ruleForm.cunit"></el-input>
                    </el-form-item>
                    <el-form-item label="公司性质" prop="cproperty">
                      <el-input readonly v-model="ruleForm.cproperty"></el-input>
                    </el-form-item>
                    <el-form-item label="公司规模" prop="csize">
                      <el-input readonly v-model="ruleForm.csize"></el-input>
                    </el-form-item>
                    <el-form-item label="公司简介" prop="cintroduction">
                      <el-input readonly v-model="ruleForm.cintroduction"></el-input>
                    </el-form-item>
                    <el-form-item label="公司邮箱" prop="cemail">
                      <el-input readonly v-model="ruleForm.cemail"></el-input>
                    </el-form-item>
                    <el-form-item label="公司地址" prop="caddress">
                      <el-input readonly v-model="ruleForm.caddress"></el-input>
                    </el-form-item>
                    <el-form-item label="人事姓名" prop="cname">
                      <el-input readonly v-model="ruleForm.cname"></el-input >
                    </el-form-item>
                    <el-form-item label="招聘信息" prop="job">
                      <el-input type="textarea" readonly autosize v-model="job"></el-input >
                    </el-form-item>
                  </el-form>
                    <el-button v-if="dis" style="background-color: #49e363;border: none" type="info" round align="center" @click="po(ruleForm.cuserid)"><el-icon class="el-icon-s-promotion" ></el-icon>{{filter(ruleForm.cuserid)}}</el-button>
                    <el-button v-if="!dis" style="border: none" type="info" round align="center" @click="po(ruleForm.cuserid)"><el-icon class="el-icon-s-promotion" ></el-icon>{{filter(ruleForm.cuserid)}}</el-button>
                  </div>
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
      ruleForm: {},
      filtedCpy:[],
      resumeexist:'',
      dis:'',
      job:''
    };
  },
  methods: {
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/student_index')
    },
    filter(data){
      for (const dataKey in this.filtedCpy) {
        if( data === this.filtedCpy[dataKey]){
          this.dis = false
          return "已投递"
        }
      }
      this.dis = true
      return "投递简历"
    },
    po(cuserid) {
      let res = false
      for (const dataKey in this.filtedCpy) {
        if (cuserid === this.filtedCpy[dataKey]) {
          res = true
        }
      }
      if(!this.resumeexist){
        alert('你还未完善简历！请完善简历在进行投递！')
        this.$router.push('/student_resume')
      }else {
      if (!res) {
        const date = {
          crCuserid: cuserid,
          crStuid: this.$store.getters.getUser.userId,
          crTime: new Date().getTime()
        }
        const _this= this
        this.$axios.post('/company_remsg/save', date).then(res => {
          if (res.data.code === 200) {
            // _this.reload()
            alert("投递成功！")
            _this.filtedCpy.push(cuserid)
            return;
          } else {
            alert("投递失败！请再次尝试！")
          }
        })
      }
      }
    },
    get(){
      let sid = this.$route.params.cuserid
      const _this = this
      this.$axios.post("/company/detail",{"cuserid":sid}).then(res=>{
        _this.ruleForm = res.data.data
      })
      //获取已投递列表
      sid = this.$store.getters.getUser.userId
      this.$axios.post('/company_remsg/filter',{crStuid:sid}).then(res=>{
        if(res.data.data !== null)
          _this.filtedCpy = res.data.data
      })
      //获取简历完善信息
      this.$axios.post('/stuempinfo/studentresume',{seStuid:sid}).then(res=>{

        if(res.data.code !== 200){
          _this.resumeexist = false
        }else {
          _this.resumeexist = true
        }
      })
      sid = this.$route.params.cuserid
      this.$axios.get('/stuempinfo/companydetailjoblist?cuserid='+sid).then(res=>{
        if(res.data.data !== ''){
          _this.job = res.data.data
        }else {
          _this.job = '无'
        }

      })
    }

  },
  created() {
    this.get()
  }


}
</script>

<style scoped>
.el-main {
  background-color: #e9eef3;
  color: #333;
  /*line-height: 160px;*/
  height:auto;
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
}
.el-input{

}


</style>
