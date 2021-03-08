<template >

  <div>


    <template>
      <div style="width: 100%" >
        <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="margin-right: 45.5%; background-color: #6c6c6c"></el-button>
        <el-button class="back" type="primary" size="mini" icon="el-icon-plus" @click="add" style="margin-right: 45%; background-color: #6c6c6c"></el-button>
        <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c"></el-button>
      </div>
      <el-table
          row-key="date"
          ref="filterTable"
          :data="tableData"
          style="width: auto;
                 margin: 10px">
        <el-table-column
            prop="jfDate"
            label="时间"
            sortable
            width="180"
            column-key="date"
        >
        </el-table-column>
        <el-table-column
            prop="jfTitle"
            label="标题"
            width="auto">
        </el-table-column>
        <el-table-column
            prop=""
            label="操作"
            width="200"
            align="center">
          <template slot-scope="scope" >
            <router-link tag="el-button" class="el-button--mini" :to="{ name:'Teacher_jobfair_detail',params:{id:scope.row.jfId} }">查看</router-link>
            <el-button v-if="jffilter(scope.row.jfId)" class="el-button--mini" @click="deljobfair(scope.row.jfId)">删除</el-button>
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
  name: "Teacher_jobfair",
  inject:['reload'],
  components: {
  },
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      dis:'',
      filtedjf:[]
    }
  },
  methods: {
    resetDateFilter() {
      this.$refs.filterTable.clearFilter('date');
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/teacher_index')
    },
    add(){
      this.$router.push('/teacher_jobfair_add')
    },
    jffilter(data){
      for (const dataKey in this.filtedjf) {
        if( data === this.filtedjf[dataKey]){
          return true
        }
      }
      return false
    },
    deljobfair(data){
      this.$axios.get('/teacher/jobfairdelete?jfid='+data).then(res=>{
        if(res.data.code === 200){
          alert("删除成功！")
          // this.$router.go(0)
          this.reload()
        }else {
          alert("删除失败！请重新尝试！")
        }

      })
    },
    page(currentPage){
      const _this = this;
      const id = _this.$store.getters.getUser.userId
      _this.$axios.get("/teacher/jobfairlist?currentPage="+currentPage).then(res=>{
        _this.tableData = res.data.data.records
        _this.currentPage = res.data.data.current
        _this.total = res.data.data.total
        _this.pageSize = res.data.data.size

        const tuserid = _this.$store.getters.getUser.userId
        _this.$axios.post('/teacher/jobfairfilter',{jfTuserid:tuserid}).then(res=>{
          if(res.data.data !== null)
            this.filtedjf = res.data.data
        })

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
