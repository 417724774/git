<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button align="center" class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;margin-right: 5px"></el-button>
        </div>
        <el-form :model="ruleForm" ref="ruleForm" label-width="110px" class="demo-ruleForm">
          <el-form-item label="职位性质：" prop="tmType">
            <el-input v-model="ruleForm.tmType"></el-input>
          </el-form-item>
          <el-form-item label="发布时间：" prop="tmPtime">
            <el-input v-model="ruleForm.tmPtime"></el-input>
          </el-form-item>
          <el-form-item label="发布人：" prop="tmMan">
            <el-input v-model="ruleForm.tmMan"></el-input>
          </el-form-item>
          <el-form-item label="标题：" prop="tmTitle">
            <el-input v-model="ruleForm.tmTitle"></el-input>
          </el-form-item>
          <el-form-item label="正文：" prop="tmContent">
            <el-input autosize type="textarea" v-model="ruleForm.tmContent"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="last()">上一条</el-button>
            <el-button @click="next()">下一条</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Message_detail",
  components: {
  },
  data() {
    return {
      ruleForm: {},
      list: {}

    };
  },
  methods: {
    close(){
      this.$router.back()
    },
  getindex(){
      for (const key in this.list){
        if(this.ruleForm.tmId === this.list[key].tmId)
          return key
      }
  },
    last(){

      const index = this.getindex()
      if(Number(index) !==0) {
        this.ruleForm = this.list[Number(index)-1]
        return
      }
      else
        alert("已是第一条！")
    },
    next(){
      const index = this.getindex()
      if(Number(index) < this.list.length-1) {
        this.ruleForm = this.list[Number(index) + 1]
        return
      }
      else
        alert("已是最后一条！")
    }
  },
  created() {
    const _this = this
    const tmid = _this.$route.params.tmid
    _this.$axios.get('/teacher/messagedetail?tmid='+tmid).then(res=>{

      if(res.data.code === 200){
        _this.ruleForm = res.data.data

      }else {
        alert("查询失败！")
      }

    }).then(()=>{

      _this.$axios.get('teacher/messagelogs').then(res=>{

        _this.list = res.data.data

      })

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
}


</style>
