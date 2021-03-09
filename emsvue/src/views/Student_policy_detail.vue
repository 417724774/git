<template>
  <div>
    <div style="width: 100%" >
      <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="margin-right: 93.8%; background-color: #6c6c6c"></el-button>
      <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c"></el-button>
    </div>
    <div class="block" style="margin-right: 45px;width: 100%" align="center">
      <el-timeline >
        <el-timeline-item  placement="top"  >
          <el-card style="width: 800px">
            <el-link class="ptitle">{{detail.ptitle}}</el-link>
            <div style="border-bottom:2px solid red;">
              <p>{{detail.pDate}} 来源：{{detail.psource}}</p>
            </div>
            <h2>{{detail.pcontent}}</h2>
            <p>{{detail.pman}}  提交于  {{detail.pUpdate}}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script>
export default {
  name: "Policy_detail",
  data(){
    return{
      detail:{}
    }
  },
  methods:{
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/student_index')
    }
  },
  created() {
    const pid = this.$route.params.pid
    this.$axios.post("/student/policy_detail",{"pid":pid}).then(res=>{
      this.detail = res.data.data
    })
  }
}
</script>

<style scoped>
.ptitle{
  font-size: 30px;
}
</style>