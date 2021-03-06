<template>
  <div>
    <div style="text-align: right">
      <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="backindex">返回</el-button>
    </div>
    <template>
      <el-table
          :data="tableData"
          style="width: 100%">
        <el-table-column type="expand">
          <template #default="props">
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
            prop="desc" align="center">
          <el-button>投递</el-button>
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
  name: "StuDelivery",
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      back: true

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
    }
    ,
    backindex(){
      this.$emit("back",this.back)
    }
  },
  created() {
        this.page(1)



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
.demo-table-expand {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>