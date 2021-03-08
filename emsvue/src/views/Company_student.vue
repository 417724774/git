<template >

  <div>


    <template>
      <div style="width: 100%" >
        <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="margin-right: 93.8%; background-color: #6c6c6c"></el-button>
        <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c"></el-button>
      </div>
      <el-table
          row-key="date"
          ref="filterTable"
          :data="tableData"
          style="width: auto;
                 margin: 10px">
        <el-table-column
            prop="sname"
            label="姓名"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="scollege"
            label="学部"
            width="auto">
        </el-table-column>
        <el-table-column
            label="专业"
            prop="smajor"
            width="auto">
        </el-table-column>
        <el-table-column
            prop=""
            label="操作"
            width="100"
            align="center">
          <template slot-scope="scope" >
            <router-link tag="el-button" :to="{ name:'Company_student_detail',params:{id:scope.row.suserid} }">查看</router-link>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total"
          @current-change=page
      >
      </el-pagination>
    </template>

  </div>

</template>

<script>
export default {
  name: "Company_student",
  components: {
  },
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5
    }
  },
  methods: {
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/company_index')
    },
    page(currentPage){
      const _this = this;
      const cid = _this.$store.getters.getUser.userId
      _this.$axios.get("/studentlist?currentPage="+currentPage+"&cid="+cid).then(res=>{
        _this.tableData = res.data.data.records
        _this.currentPage = res.data.data.current
        _this.total = res.data.data.total
        _this.pageSize = res.data.data.size
      })
    }
  },
  created() {
    this.page(1)
  }
}
</script>

<style scoped>
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
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
  margin-left: 32%;

}


</style>
