<template>
  <div>
    <div style="text-align: right">
      <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="backindex">返回</el-button>
    </div>
    <template>
      <el-table
          row-key="date"
          ref="filterTable"
          :data="tableData"
          style="width: 100%">
        <el-table-column
            prop="cUpdate"
            label="发布日期"
            sortable
            width="180"
            column-key="date"
        >
        </el-table-column>
        <el-table-column
            prop="cunit"
            label="公司名称"
            width="180">
        </el-table-column>
        <el-table-column
            prop="caddress"
            label="公司地址" >
        </el-table-column>
        <el-table-column
            prop="csalary"
            label="平均薪酬">
        </el-table-column>
        <el-table-column
            prop="tag"
            label="操作"
            width="100"
            align="center">
          <template #default="scope">
            <el-tag style="background: #ffffff" >
              <el-link >查看</el-link>
                </el-tag>
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
  name: "StuCompany",
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total: 0,
      pageSize: 5,
      back: true
    }
  },
  methods: {
    resetDateFilter() {
      this.$refs.filterTable.clearFilter('date');
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
    backindex(){
      this.$emit("back",this.back)
    },
    page(currentPage){
      const _this = this;
      _this.$axios.get("/companylist?currentPage="+currentPage).then(res=>{
        _this.tableData = res.data.data.records
        _this.currentPage = res.data.data.current
        _this.total = res.data.data.total
        _this.pageSize = res.data.data.size
      })
    }
  },
  created() {
    this.page(1)
  }
}
</script>

<style scoped>

</style>