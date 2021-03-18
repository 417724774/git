<template >

  <div>


    <template>
      <div style="width: 100%" >
        <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
        <el-button align="center" class="back" type="primary" size="mini" icon="el-icon-plus" @click="add" style=" background-color: #6c6c6c"></el-button>
        <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px"></el-button>
      </div>
      <div style="margin: 10px;text-align: left;clear: both">
        <span >发布人：</span>
        <el-input
            placeholder="请输入发布人搜索"
            prefix-icon="el-icon-search"
            style="width: 20%"
            @change="searchBy('tmman?tmman=')"
            clearable
            v-model="tmman">
        </el-input>
        <span style="margin-left: 100px">标题：</span>
        <el-input
            placeholder="请输入标题搜索"
            prefix-icon="el-icon-search"
            style="width: 20%"
            @change="searchBy('tmtitle?tmtitle=')"
            clearable
            v-model="tmtitle">
        </el-input>
      </div>
      <el-table
          row-key="date"
          ref="filterTable"
          :data="tableData"
          style="width: auto;
                 margin: 10px">
        <el-table-column
            prop="tmPtime"
            label="发布时间"
            sortable
            width="180"
            column-key="date"
        >
        </el-table-column>
        <el-table-column
            prop="tmMan"
            label="发布人"
            width="100px">
        </el-table-column>
        <el-table-column
            prop="tmTitle"
            label="标题"
            width="auto"
            align="center">
        </el-table-column>
        <el-table-column
            prop="tmType"
            label="类型"
            width="auto"
            align="center">
        </el-table-column>
        <el-table-column
            prop=""
            label="操作"
            width="200"
            align="center">
          <template slot-scope="scope" >
            <router-link tag="el-button" class="el-button--mini" :to="{ name:'Teacher_message_detail',params:{tmid:scope.row.tmId} }">查看</router-link>
            <el-button v-show="plyfilter(scope.row.tmUserid)" class="el-button--mini" @click="delMessage(scope.row.tmId,scope.row)">删除</el-button>
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
  name: "Teacher_message",
  inject:['reload'],
  components: {
  },
  data() {
    return {
      tableData: [],
      currentPage:1,
      total: 0,
      pageSize: 5,
      dis:'',
      tmman:'',
      tmtitle:'',
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
      this.$router.push('/teacher_message_add')
    },
    plyfilter(id){
        if( id === this.$store.getters.getUser.userId){

          return true
        }
      return false
    },
    delMessage(data,data1){
      const _this = this
      if(confirm("是否确认删除？")){
        _this.$axios.post('/teacher/messagedelete', {tmId:data},{
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
          // console.log(_this.tableData.length)
          const index = _this.tableData.indexOf(data1)
          _this.tableData.splice(index,1)
          if(_this.tableData.length === 0&&_this.currentPage!==1){
            _this.currentPage = _this.currentPage - 1
            _this.meName(_this.currentPage)
          }else {
            _this.currentPage = _this.currentPage
            _this.meName(_this.currentPage)
          }
          // console.log(_this.total)
          // _this.total -= 1
        })
      }


    },
    page(currentPage){
      const _this = this;
      _this.$axios.get("/teacher/messagelist?currentPage="+currentPage,{
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
      if(this.methodName === 'tmmam'){
        this.searchBy('tmman?tmman=')
      }
      if(this.methodName === 'tmtitle'){
        this.searchBy('tmtitle?tmtitle=')
      }
      else {
        this.page(currentPage)
      }
    },
    searchBy(data){
      const _this = this
      let url = ''
      if(data === 'tmman?tmman='){
        url = _this.tmman
        this.methodName = 'tmman'
      }else {
        url = _this.tmtitle
        this.methodName = 'tmtitle'
      }
      _this.$axios.get('/teacher/searchmsgby'+data+url+'&currentPage='+_this.currentPage,{
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
