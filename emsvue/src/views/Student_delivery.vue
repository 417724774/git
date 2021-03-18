<template >

  <div>

    <div style="width: 100%" >
      <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
      <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
    </div>
    <template>
      <div style="margin: 10px;text-align: left;clear: both">
        <span >职位类型：</span>
        <el-input
            placeholder="请输入职位类型搜索"
            prefix-icon="el-icon-search"
            style="width: 20%"
            @change="searchBy('cjtype?cjtype=')"
            clearable
            v-model="cjtype">
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
              <el-form-item label="人事姓名" style="width: 30%">
                <span>{{ props.row.cname }}</span>
              </el-form-item>
              <el-form-item label="联系电话"style="width: 40%">
                <span>{{ props.row.cphone }}</span>
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
            label="公司名称"
            prop="cunit" >
        </el-table-column>
        <el-table-column
            label="招聘岗位"
            prop="cjType" >
        </el-table-column>
        <el-table-column
            label="薪资"
            prop="cjSalary" >
        </el-table-column>
        <el-table-column
            label="操作"
            prop="desc" align="center" >
          <template slot-scope="scope">
            <el-button id="btu" @click="po(scope.row.cjId,scope.row.cuserid)" >{{filter(scope.row.cuserid)}}</el-button>
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
  name: "Student_delivery",
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      filtedCpy:[],
      resumeexist:'',
      cjtype:'',
      methodName:''

    }
  },
  methods:{
    page(currentPage){
      const _this = this;
      _this.$axios.get("/stuempinfo/comjoblist?currentPage="+currentPage,{
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
    filter(data){
      for (const dataKey in this.filtedCpy) {
          if( data === this.filtedCpy[dataKey]){
            return "已投递"
          }
      }
      return "投递"
    }
    ,
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/student')
    },
    po(cjId,cuserid) {
      let res = false
      for (const dataKey in this.filtedCpy) {
        if (cuserid === this.filtedCpy[dataKey]) {
          res = true
        }
      }
      if(!this.resumeexist){
        alert('你还未完善简历！请完善简历在进行投递！')
        this.$router.push('student_resume')
      }else {
      if (!res) {
        const date = {
          crCuserid:cuserid,
          crCjid:cjId,
          crStuid: this.$store.getters.getUser.userId,
          crTime: new Date().getTime()
        }
        const _this= this
        this.$axios.post('/company_remsg/save', date,{
          headers: {
            Authorization: localStorage.getItem('token')
          }
        }).then(res => {
          if(res.data.code === 200){
            _this.filtedCpy.push(cuserid)
            this.$notify({
              title: '投递成功！',
              type: 'success'
            })
          }else {
            this.$notify.error({
              title: '投递失败！请再次尝试！'
            })
          }
        })
      }
     }
    },
    get(){
      const sid = this.$store.getters.getUser.userId
      const _this = this
      this.$axios.post('/company_remsg/filter',{crStuid:sid},{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        if(res.data.data !== null)
        this.filtedCpy = res.data.data
      }).finally(()=>{
        _this.$axios.post('/stuempinfo/studentresume',{seStuid:sid},{
          headers: {
            Authorization: localStorage.getItem('token')
          }
        }).then(res=>{
          if(res.data.code !== 200){
            _this.resumeexist = false
          }else {
            _this.resumeexist = true
          }
        })
      })

    },
    meName(currentPage){
      this.currentPage = currentPage
      if(this.methodName === 'cjtype'){
        this.searchBy('cjtype?cjtype=')
      }
      else {
        this.page(currentPage)
      }
    },
    searchBy(data){
      const _this = this
      let url = ''
      if(data === 'cjtype?cjtype='){
        url = _this.cjtype
        this.methodName = 'cjtype'
      }
      _this.$axios.get('/company/searchjoballby'+data+url+'&currentPage='+_this.currentPage,{
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
