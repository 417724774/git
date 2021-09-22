<template >

  <div>


    <template>
      <div style="width: 100%" >
        <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
        <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
      </div>
      <div style="margin: 10px;text-align: left;clear: both">
        <span >公司名称：</span>
        <el-input
            placeholder="请输入公司名称搜索"
            prefix-icon="el-icon-search"
            style="width: 20%"
            @change="searchBy('cunit?cunit=')"
            clearable
            v-model="cunit">
        </el-input>
        <span style="margin-left: 100px">公司地址：</span>
        <el-input
            placeholder="请输入公司地址搜索"
            prefix-icon="el-icon-search"
            style="width: 20%"
            @change="searchBy('caddress?caddress=')"
            clearable
            v-model="caddress">
        </el-input>
      </div>
      <el-table
          row-key="date"
          ref="filterTable"
          :data="tableData"
          style="width: auto;
                 margin: 10px;
                 clear: both">
        <el-table-column
            prop="cUpdate"
            label="注册日期"
            sortable
            width="180"
            column-key="date"
        >
        </el-table-column>
        <el-table-column
            prop="cunit"
            label="公司名称"
            width="180">
        </el-table-column>
        <el-table-column
            prop="caddress"
            label="公司地址" >
        </el-table-column>
        <el-table-column
            prop="csalary"
            label="平均薪酬">
        </el-table-column>
        <el-table-column
            prop=""
            label="操作"
            width="100"
            align="center">
          <template slot-scope="scope" >
            <router-link tag="el-button" :to="{ name:'Student_company_detail',params:{cuserid:scope.row.cuserid} }">查看</router-link>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total"
          @current-change=meName
      >
      </el-pagination>
    </template>

  </div>

</template>

<script>
export default {
  name: "Student_company",
  components: {
  },
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      cunit:'',
      caddress:'',
      methodName:''
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
      this.$router.push('/student')
    },
    page(currentPage){
      const _this = this;
      _this.$axios.get("/companylist?currentPage="+currentPage,{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        _this.tableData = res.data.data.records
        _this.currentPage = res.data.data.current
        _this.total = res.data.data.total
        _this.pageSize = res.data.data.size
      })
    },
    meName(currentPage){
      this.currentPage = currentPage
      if(this.methodName === 'cunit'){
        this.searchBy('cunit?cunit=')
      }
      if(this.methodName === 'caddress'){
        this.searchBy('caddress?caddress=')
      }
      else {
        this.page(currentPage)
      }
    },
    searchBy(data){
      const _this = this
      let url = ''
      if(data === 'cunit?cunit='){
        url = _this.cunit
        this.methodName = 'cunit'
      }else {
        url = _this.caddress
        this.methodName = 'caddress'
      }
      _this.$axios.get('/company/searchby'+data+url+'&currentPage='+_this.currentPage,{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        if(res.data.code === 200){
          _this.tableData = res.data.data.records
          _this.currentPage = res.data.data.current
          _this.total = res.data.data.total
          _this.pageSize = res.data.data.size
        }else {
          _this.$notify.error({
            title: res.data.msg
          })
        }
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
