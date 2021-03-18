<template >

  <div>

    <div style="width: 100%" >
      <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
      <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
    </div>
    <template>
      <div style="width: auto;height: auto">
        <div style="margin: 10px;margin-left:10px;text-align: left;clear: both">
          <span >用户名：</span>
          <el-input
              placeholder="请输入用户名搜索"
              prefix-icon="el-icon-search"
              style="width: 20%"
              @change="searchBy('cuserid?cuserid=')"
              clearable
              v-model="cuserid">
          </el-input>
          <span style="margin-left: 100px">公司名称：</span>
          <el-input
              placeholder="请输入公司名称搜索"
              prefix-icon="el-icon-search"
              style="width: 20%"
              @change="searchBy('cunit?cunit=')"
              clearable
              v-model="cunit">
          </el-input>
        </div>
        <el-table
            :data="tableData"
            style="width: auto;
                 margin: 10px;
                 clear: both">
          <el-table-column type="expand">
            <template #default="props" >
              <el-form label-position="left"  inline class="demo-table-expand">
                <el-form-item label="公司名称" style="width: 30%">
                  <span>{{ props.row.cunit }}</span>
                </el-form-item>
                <el-form-item label="招聘岗位" style="width: 40%">
                  <span>{{ props.row.cjType }}</span>
                </el-form-item>
                <el-form-item label="基本要求" style="width: 30%">
                  <span>{{ props.row.cjDemand }}</span>
                </el-form-item>
                <el-form-item label="职位类型"style="width: 40%">
                  <span>{{ props.row.cjProperty }}</span>
                </el-form-item>
                <el-form-item label="工作地点" style="width: 30%">
                  <span>{{ props.row.cjAdress }}</span>
                </el-form-item>
                <el-form-item label="职位描述"style="width: auto">
                  <span>{{ props.row.cjDescription }}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column
              label="用户名"
              prop="cuserid"
              width="100">
          </el-table-column>
          <el-table-column
              label="公司名称"
              prop="cunit" >
          </el-table-column>
          <el-table-column
              label="招聘岗位"
              prop="cjType" >
          </el-table-column>
          <el-table-column
              label="薪资"
              prop="cjSalary">
          </el-table-column>
          <el-table-column
              label="审核状态"
              prop="cjStatus">
          </el-table-column>
          <el-table-column
              label="操作"
              prop="desc" align="right" >
            <template slot-scope="scope">
              <el-button size="mini" v-if="dis(scope.row.cjId)" @click="po(scope.row.cjId,'已通过',scope.row)" >通过</el-button>
              <el-button size="mini" v-if="dis1(scope.row.cjId)" @click="po(scope.row.cjId,'未通过',scope.row)" >不通过</el-button>
            </template>

          </el-table-column>

        </el-table>
        <el-pagination
            background
            layout="prev, pager, next"
            :current-page="currentPage"
            :page-size="pageSize"
            :total="total"
            @current-change=meName>
        </el-pagination>
      </div>
    </template>
  </div>

</template>

<script>
export default {
  name: "teacher_company_job",
  inject:['reload'],
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      filtedCpy: {},
      cunit:'',
      cuserid:'',
      methodName:''

    }
  },
  methods:{
    page(currentPage){
      const _this = this;
      _this.$axios.get("/teacher/teacomjoblist?currentPage="+currentPage,{
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
    dis(data1){
      for (const dataKey in this.filtedCpy) {
        if( data1 === this.filtedCpy[dataKey].cjId){

          if(this.filtedCpy[dataKey].cjStatus === '已通过'){
            return false
          }
          if(this.filtedCpy[dataKey].cjStatus === '未通过'){
            return true
          }
          if(this.filtedCpy[dataKey].cjStatus === '未审核'){
            return true
          }
        }
      }
    },
    dis1(data1){
      for (const dataKey in this.filtedCpy) {
        if( data1 === this.filtedCpy[dataKey].cjId){

          if(this.filtedCpy[dataKey].cjStatus === '已通过'){
            return true
          }
          if(this.filtedCpy[dataKey].cjStatus === '未通过'){
            return false
          }
          if(this.filtedCpy[dataKey].cjStatus === '未审核'){
            return true
          }
        }
      }
    }
    ,
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/teacher')
    },
    po(cjid,data,row) {
        const date = {
          cjId:cjid,
          cjStatus:data
        }
        const _this= this
        _this.$axios.post('/company/changestatus', date,{
          headers: {
            Authorization: localStorage.getItem('token')
          }
        }).then(res => {
          if(res.data.code === 200){
            localStorage.setItem("currentPage",_this.currentPage)
            localStorage.setItem("methodName",_this.methodName)
            localStorage.setItem("cname",_this.cname)
            localStorage.setItem("cuserid",_this.cuserid)
            _this.$router.replace( "/test");
            this.$notify({
              title: '操作成功！',
              type: 'success'
            })
          }else {
            this.$notify.error({
              title: '操作失败！请再次尝试！'
            })
          }
        })


    },
    get(){
      this.$axios.get('/company/getjobstatus',{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        if(res.data.data !== null)
        this.filtedCpy = res.data.data
      })

    },
    meName(currentPage){
      this.currentPage = currentPage
      if(this.methodName === 'cname'){
        this.searchBy('cname?cname=')
      }
      if(this.methodName === 'cuserid'){
        this.searchBy('cuserid?cuserid=')
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
        url = _this.cuserid
        this.methodName = 'cuserid'
      }
      _this.$axios.get('/company/searchjobby'+data+url+'&currentPage='+_this.currentPage,{
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
    this.methodName = localStorage.getItem('methodName')
    this.cuserid = localStorage.getItem('cuserid')
    this.cunit = localStorage.getItem('cunit')
    this.meName(Number(localStorage.getItem('currentPage'))||1)
    this.get()
    localStorage.removeItem('currentPage')
    localStorage.removeItem('methodName')
    localStorage.removeItem('cuserid')
    localStorage.removeItem('cunit')
  }
}
</script>

<style scoped>

.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item{
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}


</style>
