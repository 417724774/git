<template >
  <el-carousel height="70px" style="width: 100%;">
    <el-carousel-item v-for="item in 4" :key="item">
      <h3 class="small">{{ blogs[item-1] }}</h3>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  name: "Logs",
  data() {
    return {
      blogs: ["生活就像海洋，只有意志坚强的人才能到达彼岸",
        "最值得学习的博客项目eblog",
        "博客项目eblog讲解视频上线啦，长达17个小时！！",
        "真正理解Mysql的四种隔离级别@"]
    }
  }
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