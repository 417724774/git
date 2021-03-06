<template >

  <div>

    <div style="width: 100%" >
      <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="margin-right: 1600px; background-color: #6c6c6c"></el-button>
      <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c"></el-button>
    </div>
    <template>
      <el-table
          :data="tableData"
          style="width: auto;
                 margin: 10px">
        <el-table-column type="expand">
          <template #default="props" >
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="公司名称">
                <span>{{ props.row.cunit }}</span>
              </el-form-item>
              <el-form-item label="公司性质">
                <span>{{ props.row.cproperty }}</span>
              </el-form-item>
              <el-form-item label="公司规模">
                <span>{{ props.row.csize }}</span>
              </el-form-item>
              <el-form-item label="公司地址">
                <span>{{ props.row.caddress }}</span>
              </el-form-item>
              <el-form-item label="公司简介">
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
            label="公司规模"
            prop="csize" >
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
import StuDelivery from "@/components/abandon/StuDelivery";
export default {
  name: "Student_delivery",
  inject: ["reload"],
  components: {
    StuDelivery
  },
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      filtedCpy:[]

    }
  },
  methods:{
    page(currentPage){
      const _this = this;
      _this.$axios.get("/companylist?currentPage="+currentPage).then(res=>{
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
      this.$router.push('/student_index')
    },
    po(cuserid) {
      let res = false
      for (const dataKey in this.filtedCpy) {
        if (cuserid === this.filtedCpy[dataKey]) {
          res = true
        }
      }
      if (!res) {
        const date = {
          crCid: cuserid,
          crStuid: this.$store.getters.getUser.userId
        }
        const _this= this
        this.$axios.post('/company-remsg/save', date).then(res => {
          if (res.data.code === 200) {
            // _this.reload()
            alert("投递成功！")
            _this.filtedCpy.push(cuserid)
            return;
          } else {
            alert("投递失败！请再次尝试！")
          }
        })

      }
    },
    get(){
      const sid = this.$store.getters.getUser.userId
      this.$axios.post('/company-remsg/filter',{crStuid:sid}).then(res=>{
        if(res !== null)
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
