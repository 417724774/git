<template >

  <div>


    <template>
      <div style="width: 100%" >
        <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
        <el-button align="center" class="back" type="primary" size="mini" icon="el-icon-plus" @click="add" style=" background-color: #6c6c6c"></el-button>
        <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
      </div>
      <el-table
          row-key="date"
          ref="filterTable"
          :data="tableData"
          style="width: auto;
                 margin: 10px">
        <el-table-column
            prop="cjPtime"
            label="发布日期"
            sortable
            width="180"
            column-key="date"
        >
        </el-table-column>
        <el-table-column
            prop="cjType"
            label="职位类型"
            width="auto">
        </el-table-column>
        <el-table-column
            prop="cjSalary"
            label="平均薪酬">
        </el-table-column>
        <el-table-column
            prop="cjStatus"
            label="审核状态">
        </el-table-column>
        <el-table-column
            prop=""
            label="操作"
            width="200"
            align="center">
          <template slot-scope="scope" >
            <router-link tag="el-button" class="el-button--mini" :to="{ name:'Company_job_detail',params:{id:scope.row.cjId} }">查看</router-link>
            <el-button class="el-button--mini" @click="deljob(scope.row.cjId,scope.row)">删除</el-button>
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
  name: "Company_job",
  inject:['reload'],
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
      this.$router.push('/company')
    },
    add(){
    // :to="{ name:'Company_job_detail',params:{id:scope.row.cjId} }"
      this.$router.push('/company_job_add')
    },
    deljob(data,data1){
      const _this = this
      if(confirm("是否确认删除？")){
        this.$axios.get('/company/jobdelete?cjid='+data,{
          headers: {
            Authorization: localStorage.getItem('token')
          }
        }).then(res=>{
          if(res.data.code === 200){

            this.$notify({
              title: '删除成功！',
              type: 'success'
            })
          }else {
            this.$notify.error({
              title: '删除失败！请重新尝试！'
            })
          }

        }).finally(()=>{

          const index = _this.tableData.indexOf(data1)
          _this.tableData.splice(index,1)
          if(_this.tableData.length === 0&&_this.currentPage!==1){
            _this.currentPage = _this.currentPage - 1
            this.page(_this.currentPage)
          }else {
            _this.currentPage = _this.currentPage
            this.page(_this.currentPage)
          }
        })
      }
    },
    page(currentPage){
      const _this = this;
      const id = _this.$store.getters.getUser.userId
      _this.$axios.get("/company/joblistbyid?currentPage="+currentPage+"&id="+id,{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
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
