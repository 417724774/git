<template >

  <div>


    <template>
      <div style="width: 100%" >
        <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
        <el-button align="center" class="back" type="primary" size="mini" icon="el-icon-plus" @click="add" style="background-color: #6c6c6c;margin-left: 5px"></el-button>
        <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
      </div>
      <div style="margin: 10px;text-align: left;clear: both">
        <span >负责人：</span>
        <el-input
            placeholder="请输入负责人搜索"
            prefix-icon="el-icon-search"
            style="width: 20%"
            @change="searchBy('jfman?jfman=')"
            clearable
            v-model="jfman">
        </el-input>
        <span style="margin-left: 100px">标题：</span>
        <el-input
            placeholder="请输入标题搜索"
            prefix-icon="el-icon-search"
            style="width: 20%"
            @change="searchBy('jftitle?jftitle=')"
            clearable
            v-model="jftitle">
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
            prop="jfDate"
            label="时间"
            sortable
            width="180"
            column-key="date"
        >
        </el-table-column>
        <el-table-column
            prop="jfMan"
            label="负责人"
            width="100">
        </el-table-column>
        <el-table-column
            prop="jfTitle"
            label="标题"
            width="auto">
        </el-table-column>
        <el-table-column
            prop=""
            label="操作"
            width="auto"
            align="right">
          <template slot-scope="scope" >
            <router-link tag="el-button" class="el-button--mini" :to="{ name:'Teacher_jobfair_notification',params:{id:scope.row.jfId,name:scope.row.jfTitle} }">提醒</router-link>
            <router-link tag="el-button" class="el-button--mini" :to="{ name:'Teacher_jobfair_detail',params:{id:scope.row.jfId} }">查看</router-link>
            <el-button v-if="jffilter(scope.row.jfId)" class="el-button--mini" @click="deljobfair(scope.row.jfId)">删除</el-button>
            <el-popover
                placement="right"
                width="auto"
                trigger="click"
                >
              <el-table :data="status">
                <el-table-column width="200" property="cunit" label="公司"></el-table-column>
                <el-table-column width="200" property="cstatus" label="参与情况"></el-table-column>
              </el-table>
              <el-button @click="jion(scope.row.jfId)" style="margin-left: 10px" class="el-button--mini" slot="reference">参与情况</el-button>
            </el-popover>
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
      filtedjf:[],
      status:[],
      jfman:'',
      jftitle:'',
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
      this.$router.push('/teacher')
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
      const _this = this
      if(confirm("是否确认删除？")){
        this.$axios.get('/teacher/jobfairdelete?jfid='+data,{
          headers: {
            Authorization: localStorage.getItem('token')
          }
        }).then(res=>{
          if(res.data.code === 200){
            localStorage.setItem("currentPage",_this.currentPage)
            localStorage.setItem("methodName",_this.methodName)
            localStorage.setItem("jfman",_this.jfman)
            localStorage.setItem("jftitle",_this.jftitle)
            _this.$router.replace( "/test");
            this.$notify({
              title: '删除成功！',
              type: 'success'
            })
          }else {
            this.$notify.error({
              title: '删除失败！请再次尝试！'
            })
          }
        })
      }
    },
    page(currentPage){
      const _this = this;
      const id = _this.$store.getters.getUser.userId
      _this.$axios.get("/teacher/jobfairlist?currentPage="+currentPage,{
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
    jion(data){
      this.$axios.post('/jion/jionlist',{jtjid:data},{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        if(res.data.code === 200)
          this.status = res.data.data
      })
    },
    get(){
      const _this = this
      const tuserid = _this.$store.getters.getUser.userId
      _this.$axios.post('/teacher/jobfairfilter',{jfTuserid:tuserid},{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        if(res.data.data !== null)
          this.filtedjf = res.data.data
      })
    },
    meName(currentPage){
      this.currentPage = currentPage
      if(this.methodName === 'jfman'){
        this.searchBy('jfman?jfman=')
      }
      if(this.methodName === 'jftitle'){
        this.searchBy('jftitle?jftitle=')
      }
      else {
        this.page(currentPage)
      }
    },
    searchBy(data){
      const _this = this
      let url = ''
      if(data === 'jfman?jfman='){
        url = _this.jfman
        _this.methodName = 'jfman'
      }else {
        url = _this.jftitle
        _this.methodName = 'jftitle'
      }
      _this.$axios.get('/teacher/searchjfby'+data+url+'&currentPage='+_this.currentPage,{
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
    this.jftitle = localStorage.getItem('jftitle')
    this.jfman = localStorage.getItem('jfman')
    this.meName(Number(localStorage.getItem('currentPage'))||1)
    this.get()
    localStorage.removeItem('currentPage')
    localStorage.removeItem('methodName')
    localStorage.removeItem('jftitle')
    localStorage.removeItem('jfman')
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
