<template >

  <div>


    <template>
      <div style="width: 100%" >
        <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
        <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
      </div>
      <div style="margin: 10px;clear: both">
        <span >学号：</span>
        <el-input
            placeholder="请输入学号搜索"
            prefix-icon="el-icon-search"
            style="width: 10%"
            @change="searchBy('userid?userId=')"
            clearable
            v-model="userid">
        </el-input>
        <span style="margin-left: 100px">姓名：</span>
        <el-input
            placeholder="请输入姓名搜索"
            prefix-icon="el-icon-search"
            style="width: 10%"
            @change="searchBy('name?username=')"
            clearable
            v-model="name">
        </el-input>
      </div>
      <el-table
          row-key="userId"
          ref="dataTable"
          :data="tableData"
          @selection-change="handleSelectionChange"
          style="margin: 10px auto;width:98%;"
          >
        <el-table-column
            type="selection"
            :reserve-selection="true"
            width="55">
        </el-table-column>
        <el-table-column
            prop="userId"
            label="用户名" width="120">
        </el-table-column>
        <el-table-column
            prop="username"
            label="姓名" width="100">
        </el-table-column>
        <el-table-column
            prop="password"
            label="密码" width="auto" align="center">
        </el-table-column>
        <el-table-column
            prop="type"
            label="身份" align="center">
        </el-table-column>
        <el-table-column
            prop=""
            label="操作"
            width="auto"
            align="right">
          <template slot-scope="scope" >
            <el-button v-show="dis" @click="update(scope.row.id)" class="el-button--mini" tag="el-button" >修改</el-button>
            <el-button @click="deleteUser(scope.row.id,scope.row)" class="el-button--mini" tag="el-button" >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          id="change"
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
  name: "Administrator_userlist",
  components: {
  },
  data() {
    return {
      tableData: [],
      selectData:[],
      dis:true,
      name:'',
      userid:'',
      currentPage: 1,
      total: 0,
      pageSize: 10,
      methodName:''
    }
  },
  methods: {
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/administrator')
    },
    page(currentPage){
      const _this = this;
      _this.$axios.get("/user/userlist?currentPage="+currentPage,{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        _this.tableData = res.data.data.records
        _this.currentPage = res.data.data.current
        _this.total = res.data.data.total
        _this.pageSize = res.data.data.size
        _this.selectData = []
      })
    },
    update(data){
      this.$router.push('/administrator_updateuser/'+data)
    },
    deleteUser(data,data1){
      const _this = this
      let value = null
      const id = data
      let list = []
      let token = {
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }
      if(confirm("是否确认删除？")){
        if(this.selectData.length === 0) {
          list.push({id: id})
          value = list
        }else {
          value = _this.selectData
        }
          _this.$axios.post('/user/delete',value,token).then(res=>{
            if(res.data.code === 200){
              this.$refs.dataTable.clearSelection();
              _this.$notify({
                title: '删除成功！',
                type: 'success'
              })
            }else {
              _this.$notify.error({
                title: '删除失败！'
              })
            }
          }).finally(()=>{

            const index = _this.tableData.indexOf(data1)
            _this.tableData.splice(index,1)
            if(_this.tableData.length === 0&&_this.currentPage!==1){
              _this.currentPage = _this.currentPage - 1
              this.meName(_this.currentPage)
            }else {
              _this.currentPage = _this.currentPage
              this.meName(_this.currentPage)
            }
          })
        // else {
        //   _this.$axios.post('/user/delete',_this.selectData,token).then(res=>{
        //     if(res.data.code === 200){
        //       this.$refs.dataTable.clearSelection();
        //       _this.$notify({
        //         title: '删除成功！',
        //         type: 'success'
        //       })
        //     }else {
        //       _this.$notify.error({
        //         title: '删除失败！'
        //       })
        //     }
        //   }).finally(()=>{
        //
        //     const index = _this.tableData.indexOf(data1)
        //     _this.tableData.splice(index,1)
        //     if(_this.tableData.length === 0&&_this.currentPage!==1){
        //       _this.currentPage = _this.currentPage - 1
        //       this.meName(_this.currentPage)
        //     }else {
        //       _this.currentPage = _this.currentPage
        //       this.meName(_this.currentPage)
        //     }
        //   })
        // }
      }

    },
    handleSelectionChange(val){
      this.selectData = val
      if (this.selectData.length === 0){
        this.dis = true
      }else {
        this.dis = false
      }
    },
    searchBy(data){
      const _this = this
      let url = ''
      if(data === 'name?username='){
        url = _this.name
        this.methodName = 'name'
      }else {
        url = _this.userid
        this.methodName = 'userid'
      }
        _this.$axios.get('/user/searchby'+data+url+'&currentPage='+_this.currentPage,{
            headers: {
              Authorization: localStorage.getItem('token')
            }
          }).then(res=>{
          if(res.data.code === 200){
            _this.tableData = res.data.data.records
            _this.currentPage = res.data.data.current
            _this.total = res.data.data.total
            _this.pageSize = res.data.data.size
            _this.selectData = []
          }else {
            _this.$notify.error({
              title: res.data.msg
            })
          }
        })
    },
    // searchByUserid(data){
    //   const _this = this
    //   _this.$axios.get('/user/searchbyuserid?userId='+data+'&currentPage='+_this.currentPage,{
    //     headers: {
    //       Authorization: localStorage.getItem('token')
    //     }
    //   }).then(res=>{
    //     if(res.data.code === 200){
    //       _this.tableData = res.data.data.records
    //       _this.currentPage = res.data.data.current
    //       _this.total = res.data.data.total
    //       _this.pageSize = res.data.data.size
    //       _this.selectData = []
    //       this.methodName = 'userid'
    //     }else {
    //       _this.$notify.error({
    //         title: res.data.msg
    //       })
    //     }
    //   })
    //
    // },
    meName(currentPage){
      this.currentPage = currentPage
      if(this.methodName === 'name'){
        this.searchBy('name?username=')
      }
      if(this.methodName === 'userid'){
        this.searchBy('userid?userId=')
      }
      else {
        this.page(currentPage)
      }
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


</style>
