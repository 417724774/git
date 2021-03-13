<template >

  <div>

    <el-container>
      <el-main>
        <div style="width: 100%" >
          <el-button class="back" type="primary" size="mini" icon="el-icon-back" @click="back" style="background-color: #6c6c6c;float: left;margin-left: 5px"></el-button>
          <el-button class="back" type="primary" size="mini" icon="el-icon-close" @click="close" style="background-color: #6c6c6c;float: right;margin-right: 5px;margin-bottom: 10px"></el-button>
        </div>

        <div v-for="(item,index) in this.tableData" style="margin-bottom: 40px;clear: both">

          <el-progress type="circle"
                       :format="format(index)"
                       :width="200"
                       :stroke-width="10"
                       :percentage="num[index]">

          </el-progress>
          <div style="margin-bottom: 20px">
            <span>招聘岗位：{{item.cjType}}</span>
          </div>

          <span >发布时间：{{item.cjPtime}}</span>
        </div>
        <div>

        </div>


      </el-main>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Company_statistic",
  data() {
    return {
      tableData:{},
      total:'',
      num:[],
      cjid:[]

    }

  },
  methods:{
    format(index){
      return ()=>{
        return '已投递人数：'+this.num[index]
      }
    },
    get(){
      const _this = this;
      const id = _this.$store.getters.getUser.userId
      _this.$axios.get("/company/joblist?id="+id).then(res=>{

        _this.tableData = res.data.data
        _this.total = res.data.data.total
        for (let i=0;i < _this.tableData.length;i++ ){
          _this.cjid.push(_this.tableData[i].cjId)

        }
        this.$axios.post('company_remsg/resumecount',_this.cjid).then(res=>{

          _this.num = res.data.data

        })
      })
    },
    back(){
      this.$router.back()
    },
    close(){
      this.$router.push('/company_index')
    },
  },
  created() {
    this.get()
  }
}
</script>

<style scoped>
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  /*line-height: 160px;*/
  height: 650px;
  max-height: 650px;
  padding: unset;
}


</style>
