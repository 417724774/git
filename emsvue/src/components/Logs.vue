<template >
<!--  <el-carousel height="70px" style="width: 100%;" :interval="5000">-->
<!--    <el-carousel-item v-for="item in blogs.length" :key="item" >-->
<!--      <h3 class="small">即将举办：“{{blogs[item-1].jfTitle}}”···· 时间：{{blogs[item-1].jfDate}} ···· 地点：{{blogs[item-1].jfAdress}}</h3>-->
<!--    </el-carousel-item>-->
<!--  </el-carousel>-->
  <el-carousel height="70px" style="width: 100%;" :interval="5000">
    <el-carousel-item v-for="item in blogs.length" :key="item" >
<!--      <h2  @click="choose(blogs[item-1].tmId)"></h2>-->
      <h1 align="center"><router-link style="font-size: 28px" tag="el-link" class="ptitle" :to="{ name:'Message_detail',params:{tmid:blogs[item-1].tmId} }" >{{blogs[item-1].tmType}}：“{{blogs[item-1].tmTitle}}”······························{{blogs[item-1].tmPtime}}</router-link></h1>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  name: "Logs",
  data() {
    return {
      blogs: []
    }
  },
  created() {
    const _this = this
    _this.$axios.get('/teacher/messagelogs',{
      headers: {
        Authorization: localStorage.getItem("token")
      }
    }).then(res=>{

      _this.blogs = res.data.data

    })
  },
  methods:{
    choose(data){
      this.$emit("choose",'teacher_message_detail/'+data)
    },
    close(){
      this.history.back()
    }
  },
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 20px;
  opacity: 0.75;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>