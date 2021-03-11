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
                 clear: both">
        <el-table-column type="expand">
          <template #default="props" >
            <el-form label-position="left"  inline class="demo-table-expand">
              <el-form-item label="地点" style="width: 30%">
                <span>{{ props.row.jfAdress }}</span>
              </el-form-item>
              <el-form-item label="联系人" style="width: 40%">
                <span>{{ props.row.jfMan }}</span>
              </el-form-item>
              <el-form-item label="联系方式" style="width: 30%">
                <span>{{ props.row.jfPhone }}</span>
              </el-form-item>
              <el-form-item label="正文"style="width: auto">
                <span>{{ props.row.jfBody }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
            label="时间"
            prop="jfDate" >
        </el-table-column>
        <el-table-column
            label="标题"
            prop="jfTitle" >
        </el-table-column>
        <el-table-column
            label="操作"
            prop="desc" align="center" >
          <template slot-scope="scope">
            <el-button id="btu" @click="po(scope.row.jfId)" >{{filter(scope.row.jfId)}}</el-button>
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
import StuDelivery from "@/components/abandon/StuDelivery";
export default {
  name: "Company_jion",
  components: {
    StuDelivery
  },
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
      _this.$axios.get("/company/jobfairlist?currentPage="+currentPage).then(res=>{
        _this.tableData = res.data.data.records
        _this.currentPage = res.data.data.current
        _this.total = res.data.data.total
        _this.pageSize = res.data.data.size
      })
    },
    filter(data){
      for (const dataKey in this.filtedCpy) {
          if( data === this.filtedCpy[dataKey]){
            return "已参与"
          }
      }
      return "参与"
    }
    ,
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/company_index')
    },
    po(cjId) {
      let res = false
      for (const dataKey in this.filtedCpy) {
        if (cjId === this.filtedCpy[dataKey]) {
          res = true
        }
      }
      if (!res) {
        const date = {
          jtjid:cjId,
          jcuserid: this.$store.getters.getUser.userId,
        }
        const _this= this
        this.$axios.post('/company/jionjobfair', date).then(res => {
          if (res.data.code === 200) {
            // _this.reload()
            alert("参与成功！")
            _this.filtedCpy.push(cjId)
            return;
          } else {
            alert("参与失败！请再次尝试！")
          }
        })
      }
    },
    get(){
      const sid = this.$store.getters.getUser.userId
      const _this = this
      this.$axios.post('/jion/jionfilter',{jcuserid:sid}).then(res=>{
        if(res.data.data !== null)
          _this.filtedCpy = res.data.data
        else {
          _this.filtedCpy = []
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
