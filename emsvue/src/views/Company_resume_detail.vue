<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
        </div>
        <div class="ruleFrom" style="border: 2px solid #cecbcb;width: 100%">
          <div style="float: right;height: 10px;margin: 20px" title="照片">
            <img v-if="this.ruleForm.sePicture" :src="this.ruleForm.sePicture" class="avatar">
          </div>
          <el-form :model="ruleForm" ref="ruleForm" label-width="20px" class="demo-ruleForm">
            <el-form-item prop="seName" style="text-align: left;width: 70%">
              <el-input readonly v-model="ruleForm.seName" style="font-size: 50px;height: 50px;margin-bottom: 20px"></el-input>
              <div >
                <span style="width: 10%">电话：</span>
                <el-input readonly v-model="ruleForm.sePhone" style="font-size: 17px;width: 40%"></el-input>
                <span style="width: 25%;margin-left: 10%">性别：</span>
                <el-input readonly v-model="ruleForm.seSex" style="font-size: 17px;width: 25%"></el-input>
              </div>
              <div >
                <span style="width: 10%">邮箱：</span>
                <el-input readonly v-model="ruleForm.seEmail" style="font-size: 17px;width: 40%"></el-input>
                <span style="width: 25%;margin-left: 10%">专业：</span>
                <el-input readonly v-model="ruleForm.seMajor" style="font-size: 17px;width: 25%"></el-input>
              </div>
              <div>
                <span style="width: 10%">求职意向：</span>
                <el-input readonly v-model="ruleForm.seIntention" style="font-size: 17px;width: 40%"></el-input >
                <span style="width: 25%;margin-left: 5%">学校：</span>
                <el-input readonly v-model="ruleForm.seSchool" style="font-size: 17px;width: 25%"></el-input>
              </div>
            </el-form-item>
            <div style="border-bottom:5px solid #266f76;width: 95%;margin-left: 3%"></div>
            <el-form-item prop="seExprience" style="text-align: left">
              <span style="font-size: 20px">工作经验：</span>
              <el-input readonly autosize type="textarea" style="max-width: 100%;margin-bottom: 20px" v-model="ruleForm.seExprience"></el-input >
              <div style="border-bottom:5px solid #266f76;width: 98%"></div>
            </el-form-item>
            <el-form-item prop="seEducation" style="text-align: left">
              <span style="font-size: 20px">教育经历：</span>
              <el-input readonly autosize type="textarea" style="max-width: 100%;margin-bottom: 20px" v-model="ruleForm.seEducation"></el-input >
              <div style="border-bottom:5px solid #266f76;width: 98%"></div>
            </el-form-item>
            <el-form-item prop="seTrain" style="text-align: left">
              <span style="font-size: 20px">培训经历：</span>
              <el-input readonly autosize type="textarea" style="max-width: 100%;margin-bottom: 20px" v-model="ruleForm.seTrain"></el-input >
              <div style="border-bottom:5px solid #266f76;width: 98%"></div>
            </el-form-item>
            <el-form-item prop="seCerdificate" style="text-align: left;">
              <span style="font-size: 20px">证书：</span>
              <el-input readonly autosize type="textarea" v-model="ruleForm.seCerdificate"style="max-width: 100%;margin-bottom: 20px"></el-input >
              <div style="border-bottom:5px solid #266f76;width: 98%"></div>
            </el-form-item>
            <el-form-item prop="seIntroduction" style="text-align: left">
              <span style="font-size: 20px">自我介绍：</span>
              <el-input readonly autosize type="textarea" style="max-width: 100%;margin-bottom: 20px" v-model="ruleForm.seIntroduction"></el-input >
              <div style="border-bottom:5px solid #266f76;width: 98%"></div>
            </el-form-item>
            <el-button style="background-color: #49e363;border: none;margin-bottom: 10px" type="info" round align="center" @click="send(ruleForm.seStuid)">
              <el-icon class="el-icon-s-promotion" ></el-icon>发送消息
            </el-button>
            <el-popover
                placement="top"
                width="auto"
                trigger="click"
            >
              <el-table :data="this.historyMsg">
                <el-table-column width="200" property="smTitle" label="标题"></el-table-column>
                <el-table-column width="200" property="smContent" label="正文"></el-table-column>
                <el-table-column width="200" property="smPtime" label="时间"></el-table-column>
              </el-table>
              <el-button @click="hasSend(ruleForm.seStuid)" style="margin-left: 10px;border: none;background: none;color: #6c6c6c;" class="el-button--mini" slot="reference">历史消息</el-button>
            </el-popover>
          </el-form>
        </div>
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
      ruleForm: {},
      historyMsg:[]
    };
  },
  methods: {
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/company')
    },
    send(data){

      this.$router.push('/company_resume_send/'+data)

    },
    hasSend(data){

      const _this= this
      const studentMessage = {smAccept: data,smManid: _this.$store.getters.getUser.userId}
      _this.$axios.post('/student/hassend',studentMessage,{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{

        if(res.data.code === 200){

          _this.historyMsg = res.data.data

        }else {
          console.log("无数据！")
        }
      })

    }
  },
  created() {
    const sid = this.$route.params.id
    const _this = this
    _this.$axios.post("/stuempinfo/studentresume",{"seStuid":sid},{
      headers: {
        Authorization: localStorage.getItem('token')
      }
    }).then(res=>{
      //console.log(res.data.data)
      _this.ruleForm = res.data.data
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
  padding-top: 20px;
  padding-bottom: 10px;
}
.ruleFrom{
  background-color: white;
  max-width: 50%;
  margin: 0 auto;
  clear: both;
}
/* 利用穿透，设置input边框隐藏 */
.demo-ruleForm>>>.el-input__inner {
  max-width: 90%;
  height: auto;
  border: 0;
}
/* 如果你的 el-input type 设置成textarea ，就要用这个了 */
.demo-ruleForm>>>.el-textarea__inner {
  border: 0;
  resize: none;/* 这个是去掉 textarea 下面拉伸的那个标志，如下图 */
}


</style>
