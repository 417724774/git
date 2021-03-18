<template >

  <div>

    <div style="width: 100%" >
      <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
      <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
    </div>
    <template>
      <div style="margin: 10px;text-align: left;clear: both">
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
                 clear: both"
                 max-height="550px;">
        <el-table-column type="expand">
          <template #default="props" >
            <el-form label-position="left"  inline class="demo-table-expand">
              <el-form-item label="公司名称" style="width: 30%">
                <span>{{ props.row.cunit }}</span>
              </el-form-item>
              <el-form-item label="申请人" style="width: 30%">
                <span>{{ props.row.cname }}</span>
              </el-form-item>
              <el-form-item label="联系电话"style="width: 30%">
                <span>{{ props.row.cphone }}</span>
              </el-form-item>
              <el-form-item label="邮箱" style="width: 30%">
                <span>{{ props.row.cemail }}</span>
              </el-form-item>
              <el-form-item label="公司规模" style="width: 30%">
                <span>{{ props.row.csize }}</span>
              </el-form-item>
              <el-form-item label="公司性质" style="width: 30%">
                <span>{{ props.row.cproperty }}</span>
              </el-form-item>
              <el-form-item label="公司地址" style="width: 30%">
                <span>{{ props.row.caddress }}</span>
              </el-form-item>
              <el-form-item label="公司简介"style="width: auto">
                <span>{{ props.row.cintroduction }}</span>
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
            label="注册时间"
            prop="cRtime" >
        </el-table-column>
        <el-table-column
            label="账户状态"
            prop="cstatus">
<!--          <template slot-scope="scope">-->
<!--          {{filterStatus(scope.row.cuserid)}}-->
<!--          </template>-->
        </el-table-column>
        <el-table-column
            label="操作"
            prop="desc" align="right" >
          <template slot-scope="scope">
            <el-button v-if="filter(scope.row.cuserid)" class="el-button--mini" @click="change(scope.row)" >通过</el-button>
            <el-button v-if="filter(scope.row.cuserid)" class="el-button--mini" @click="del(scope.row)" >不通过</el-button>
            <el-button v-if="!filter(scope.row.cuserid)" v-show="jugement(scope.row.cstatus)" class="el-button--mini" @click="change(scope.row)" >冻结</el-button>
            <el-button v-if="!filter(scope.row.cuserid)" v-show="!jugement(scope.row.cstatus)" class="el-button--mini" @click="change(scope.row)" >恢复</el-button>
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
  name: "teacher_company",
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      filtedCpy:[],
      resumeexist:'',
      cunit:'',
      cuserid:'',
      methodName:''

    }
  },
  methods:{
    page(currentPage){
      const _this = this;
      _this.$axios.get("/company/companylist?currentPage="+currentPage,{
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
    // filterStatus(data){
    //   for (const dataKey in this.filtedCpy) {
    //     if( data === this.filtedCpy[dataKey]){
    //       return "已审核"
    //     }
    //   }
    //   return "未审核"
    // },
    filter(data){
      for (const dataKey in this.filtedCpy) {
          if( data === this.filtedCpy[dataKey]){
            return true
          }else
            return  false
      }
    },
    del(data){
      const _this= this
      if(confirm('是否确定继续进行？')){
        _this.$axios.post('/company/delete',data,{
          headers: {
            Authorization: localStorage.getItem('token')
          }
        }).then(res=>{
          if(res.data.code === 200){
            _this.$notify({
              title: '操作成功！',
              type: 'success'
            })
            _this.meName(_this.currentPage)
          }else {
            _this.$notify.error({
              title: '操作失败！请再次尝试！'
            })
          }
        })
      }
    },
    jugement(data){
      if (data === '已冻结'){
        return false
      }else {
        return true
      }
    },
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/teacher')
    },
    change(data) {
        const _this= this
        _this.$axios.post('/company/companycheck',data,{
          headers: {
            Authorization: localStorage.getItem('token')
          }
        }).then(res => {
          if(res.data.code === 200){
            _this.meName(_this.currentPage)
            _this.$notify({
              title: '操作成功！',
              type: 'success'
            })
          }else {
            _this.$notify.error({
              title: '操作失败！请再次尝试！'
            })
          }
        })
    },
    get(){
      this.$axios.get('/company/companyfilter',{
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
      if(this.methodName === 'cunit'){
        this.searchBy('cunit?cunit=')
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
    this.page(this.currentPage)
    this.get()

  }
}
</script>

<style>

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
