<template >

  <div>
    <div style="width: 100%" >
      <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
      <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
    </div>
        <div style="width: 100%;clear: both" >
        <div style="width: 40%;height: 100%;float: left">
          <Tpie></Tpie>
        </div>
        <div style="width: 60%;max-height: 100%;float: right">
          <div style="margin: 10px;clear: both">
            <span >学号：</span>
            <el-input
                placeholder="请输入学号搜索"
                prefix-icon="el-icon-search"
                style="width: 25%"
                @change="searchBy('swstuid?swStuid=')"
                clearable
                v-model="swStuid">
            </el-input>
            <span style="margin-left: 100px">姓名：</span>
            <el-input
                placeholder="请输入姓名搜索"
                prefix-icon="el-icon-search"
                style="width: 25%"
                @change="searchBy('sname?sname=')"
                clearable
                v-model="sname">
            </el-input>

          </div>
          <template style="width: auto;">
            <el-table
                :data="tableData"
                style="width: auto;
                 margin: 10px" max-height="500px">
              <el-table-column type="expand" >
                <template #default="props" >
                  <el-form label-position="left"  inline class="demo-table-expand" >
                    <el-form-item label="公司名称" style="width: 40%">
                      <span>{{ props.row.swName }}</span>
                    </el-form-item>
                    <el-form-item label="公司地址" style="width: 40%">
                      <span>{{ props.row.swAddress }}</span>
                    </el-form-item>
                    <el-form-item label="合同性质"style="width: 40%">
                      <span>{{ props.row.swAgreement }}</span>
                    </el-form-item>
                    <el-form-item label="薪酬" style="width: 40%">
                      <span>{{ props.row.swSalary }}</span>
                    </el-form-item>
                    <el-form-item label="工作性质" style="width: 40%">
                      <span>{{ props.row.swPorperty }}</span>
                    </el-form-item>
                    <el-form-item label="合同期限" style="width: 40%">
                      <span>{{ props.row.swDeadline }}</span>
                    </el-form-item>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column
                  label="学号"
                  prop="swStuid" >
              </el-table-column>
              <el-table-column
                  label="姓名"
                  prop="sname" >
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
        </div>
  </div>

</template>

<script>
import Tpie from "@/components/Tpie";

export default {
  name: "Teacher_statistic",
  components:{Tpie},
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 10,
      sname:'',
      swStuid:'',
      methodName:''
    }

  },
  methods:{
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/teacher')
    },
    page(currentPage){
      const _this = this
      _this.$axios.get("/teacher/stuworklist?currentPage="+currentPage,{
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
      if(this.methodName === 'sname'){
        this.searchBy('sname?sname=')
      }
      if(this.methodName === 'swStuid'){
        this.searchBy('swstuid?swStuid=')
      }
      else {
        this.page(currentPage)
      }
    },
    searchBy(data){
      const _this = this
      let url = ''
      console.log(data)
      if(data === 'sname?sname='){
        url = _this.sname
        this.methodName = 'sname'
      }else {
        url = _this.swStuid
        this.methodName = 'swStuid'
      }
      _this.$axios.get('/studentwork/searchby'+data+url+'&currentPage='+_this.currentPage,{
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
  height: auto;
  padding: unset;
}


</style>
