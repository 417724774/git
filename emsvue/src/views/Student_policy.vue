<template>
  <div>
    <div style="width: 100%" >
      <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
      <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
    </div>
    <div class="block" style="margin-right: 45px;clear: both">
      <el-timeline>
        <el-timeline-item :timestamp="policy.pDate" placement="top" v-for="policy in policys" >
          <el-card >
            <router-link tag="el-link" class="ptitle" :to="{ name:'Student_policy_detail',params:{pid:policy.pid} }">{{policy.ptitle}}</router-link>
            <p>{{policy.pman}}  提交于  {{policy.pUpdate}}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script>
export default {
  name: "Student_prolicy",
  data(){
    return{
      policys:{}
    }
  },
  methods:{
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/student')
    }
  },
  created(){
    const _this = this;
    _this.$axios.get('/student/policylist',{
      headers: {
        Authorization: localStorage.getItem('token')
      }
    }).then(res =>{
      _this.policys = res.data.data

    })
  }
}
</script>

<style scoped>
.ptitle{
  font-size: 30px;
}
</style>