<template >

  <div>

    <div style="width: 100%" >
      <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
      <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
    </div>
    <template>
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
            label="公司名称"
            prop="cunit" >
        </el-table-column>
        <el-table-column
            label="注册时间"
            prop="cRtime" >
        </el-table-column>
        <el-table-column
            label="账户状态" >
          <template slot-scope="scope">
          {{filterStatus(scope.row.cuserid)}}
          </template>
        </el-table-column>
        <el-table-column
            label="操作"
            prop="desc" align="center" >
          <template slot-scope="scope">
            <el-button id="btu" @click="po(scope.row.cuserid)" >{{filter(scope.row.cuserid)}}</el-button>
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
  name: "teacher_company",
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      filtedCpy:[],
      resumeexist:''

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
    filterStatus(data){
      for (const dataKey in this.filtedCpy) {
        if( data === this.filtedCpy[dataKey]){
          return "已审核"
        }
      }
      return "未审核"
    },
    filter(data){
      for (const dataKey in this.filtedCpy) {
          if( data === this.filtedCpy[dataKey]){
            return "冻结"
          }
      }
      return "通过"
    }
    ,
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/teacher')
    },
    po(cuserid) {
      let boolean = true
      for (const dataKey in this.filtedCpy){
        if( cuserid === this.filtedCpy[dataKey]){
          boolean = false
        }
      }
        const date = {
          cuserid:cuserid
        }
        const _this= this
        this.$axios.post('/company/companycheck', date,{
          headers: {
            Authorization: localStorage.getItem('token')
          }
        }).then(res => {
          if(res.data.code === 200){
            if(boolean)
              _this.filtedCpy.push(cuserid)
            else {
              let index = _this.filtedCpy.indexOf(cuserid)
              _this.filtedCpy.splice(index,1)
            }
            return;
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
      const sid = this.$store.getters.getUser.userId
      const _this = this
      this.$axios.get('/company/companyfilter',{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        if(res.data.data !== null)
        this.filtedCpy = res.data.data
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
