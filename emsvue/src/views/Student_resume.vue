<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
        </div>
        <div class="ruleFrom">
          <div style="float: right;margin: 20px;height: 10px;width: auto" title="照片">
            <el-upload
                ref="upload"
                class="avatar-uploader"
                action="http://localhost:8080/stuempinfo/studentPicture"
                :headers="headers"
                :show-file-list="false"
                :data="{userid:this.$store.getters.getUser.userId}"
                :on-success="display"
            >
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <div style="width: auto" class="el-upload__tip">只能上传 jpg/png 文件</div>
            <div style="width: auto" class="el-upload__tip">，且不超过 2M</div>
          </div>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="姓名" prop="seName" style="width: 60%">
              <el-input readonly v-model="studentinfo.sname"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="seSex" style="width: 60%">
              <el-input readonly v-model="studentinfo.ssex"></el-input>
            </el-form-item>
            <el-form-item label="专业" prop="seMajor" style="width: 60%">
              <el-input readonly v-model="ruleForm.seMajor = studentinfo.smajor" size="10"></el-input>
            </el-form-item>
            <el-form-item label="毕业院校" prop="seSchool" style="width: 60%">
              <el-input readonly v-model="ruleForm.seSchool = '五邑大学'" ></el-input>
            </el-form-item>
            <el-form-item label="目标岗位" prop="seIntention" style="width: 60%">
              <el-input  v-model="ruleForm.seIntention"></el-input >
            </el-form-item>
            <el-form-item label="联系地址" prop="seLocation">
              <el-input v-model="ruleForm.seLocation"></el-input>
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
              <el-input autosize type="textarea" style="max-width: 100%" v-model="ruleForm.seIntroduction"></el-input >
            </el-form-item>
            <el-form-item label="工作经验" prop="seExprience">
              <el-input autosize type="textarea" style="max-width: 100%" v-model="ruleForm.seExprience"></el-input >
            </el-form-item>
            <el-form-item label="教育经历" prop="seEducation">
              <el-input autosize type="textarea" style="max-width: 100%" v-model="ruleForm.seEducation"></el-input >
            </el-form-item>
            <el-form-item label="培训经历" prop="seTrain">
              <el-input autosize type="textarea" style="max-width: 100%" v-model="ruleForm.seTrain"></el-input >
            </el-form-item>
              <el-button type="primary" style="margin-left: 41%" @click="submitForm('ruleForm')">完善确认</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
              <el-button class="mini" @click="centerDialogVisible = true">简历预览</el-button>
          </el-form>
          <el-dialog
              :visible.sync="centerDialogVisible"
              width="80%"
              destroy-on-close
              center>
            <div class="ruleFrom1" style="border: 2px solid #cecbcb;width: 100%">
              <div style="float: right;height: 10px;margin: 15px" title="照片">
                <img v-if="this.ruleForm.sePicture" :src="this.ruleForm.sePicture" class="avatar">
              </div>
              <el-form :model="ruleForm" ref="ruleForm" label-width="20px" class="demo-ruleForm1">
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
              </el-form>
            </div>
          </el-dialog>
        </div>
      </el-main>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Student_resume",
  components: {
  },
  data() {
    return {
      ruleForm: {},
      studentinfo: {},
      imageUrl:'',
      dis:true,
      headers: {Authorization: localStorage.getItem('token')},
      centerDialogVisible: false,

      rules: {
        seEmail: [
          { type:'email', required: true, message: '请输入邮箱', trigger: 'blur' }
        ],
        seLocation: [
          { required: true, message: '请输入联系地址', trigger: 'blur' },
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
        sePhone: [
          { required: true, message: '请输入联系电话', trigger: 'change' },
          { min: 11, max: 11, message: '请输入 11 位手机号码', trigger: 'blur' }
        ],
        seTrain: [
          { required: true, message: '请输入培训经历', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          const _this = this
          _this.ruleForm.seName = _this.$store.getters.getUser.username
          _this.ruleForm.seSex = _this.studentinfo.ssex
          _this.ruleForm.seStuid = _this.$store.getters.getUser.userId
          _this.ruleForm.sePicture = _this.imageUrl
          this.$axios.post('/stuempinfo/stuemupdate',this.ruleForm,{
            headers: {
              Authorization: localStorage.getItem('token')
            }
          }).then(res => {
            if(res.data.code === 200){

              this.$notify({
                title: '完善简历成功！',
                type: 'success'
              })
            }else {
              this.$notify.error({
                title: '完善失败！请再次尝试！'
              })
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
      this.$router.push('/student')
    },
    get(){
      const sid = this.$store.getters.getUser.userId
      const _this = this
      _this.$axios.post("/stuempinfo/studentresume",{"seStuid":sid},{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        //console.log(res.data.data)
        if(res.data.code === 200){
          _this.ruleForm = res.data.data
          _this.imageUrl = _this.ruleForm.sePicture
        }else {
          console.log(res.data.data)
        }
      })

      _this.$axios.post("/student/studentinfo",{"suserid":sid},{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        _this.studentinfo = res.data.data
      })

    },
    display(response){
      this.imageUrl = response.url+ "?" + new Date().getTime();
    },
    preview(){

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
  max-width: 90%;
  padding-top: 20px;
  padding-bottom: 10px;
}
.ruleFrom{
  background-color: white;
  max-width: 50%;
  margin: 0 auto;
  clear: both;
}
.avatar-uploader{
  border: 2px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 160px;
  height: 160px;
  line-height: 160px;
  text-align: center;
}
.avatar {
  width: 160px;
  height: 160px;
  display: block;
}
.ruleFrom1{
  background-color: white;
  max-width: 50%;
  margin: 0 auto;
  clear: both;
}
/* 利用穿透，设置input边框隐藏 */
.demo-ruleForm1>>>.el-input__inner {
  max-width: 90%;
  height: auto;
  border: 0;
}
/* 如果你的 el-input type 设置成textarea ，就要用这个了 */
.demo-ruleForm1>>>.el-textarea__inner {
  border: 0;
  resize: none;/* 这个是去掉 textarea 下面拉伸的那个标志，如下图 */
}
</style>
