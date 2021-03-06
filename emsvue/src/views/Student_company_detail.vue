<template >

  <div>

              <el-container>

                <el-main>
                  <div style="width: auto" >

                    <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="margin-right: 1580px; background-color: #6c6c6c"></el-button>
                    <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c"></el-button>
                  </div>
                  <div align="center">
                  <el-form :model="ruleForm"  ref="ruleForm" label-width="100px" class="demo-ruleForm" style="margin: 10px" >
                    <el-form-item label="公司名称" prop="cunit">
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
                    <el-form-item label="招聘信息" prop="cjob">
                      <el-input type="textarea" readonly autosize v-model="ruleForm.cjob"></el-input >
                    </el-form-item>
                  </el-form>
                    <el-button type="info" round  align="center" @click="po(ruleForm.cuserid)"><el-icon class="el-icon-s-promotion" ></el-icon>{{filter(ruleForm.cuserid)}}</el-button>
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
      filtedCpy:[]
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
          return "已投递"
        }
      }
      return "投递简历"
    },
    po(cuserid) {
      let res = false
      for (const dataKey in this.filtedCpy) {
        if (cuserid === this.filtedCpy[dataKey]) {
          res = true
        }
      }
      if (!res) {
        const date = {
          crCid: cuserid,
          crStuid: this.$store.getters.getUser.userId
        }
        const _this= this
        this.$axios.post('/company-remsg/save', date).then(res => {
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
    },
    get(){
      let sid = this.$route.params.cuserid
      const _this = this
      this.$axios.post("/company/detail",{"cuserid":sid}).then(res=>{
        _this.ruleForm = res.data.data
      })
      //获取已投递列表
      sid = this.$store.getters.getUser.userId
      this.$axios.post('/company-remsg/filter',{crStuid:sid}).then(res=>{
        if(res !== null)
          _this.filtedCpy = res.data.data
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
  background-color: #E9EEF3;
  color: #333;
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
}
.el-input{
  background: white;

}


</style>
