<template>

  <div>
    <Sheader v-on:choose="choose" v-on:msg="msg">
    </Sheader>
    <el-container>
      <Saside v-on:choose="choose"></Saside>
      <el-container class="containor">
        <el-drawer
            :title="title()"
            :visible.sync="drawer"
            style="position: absolute;text-align: center"
            z-index="18"
            :direction="direction"
            :size="350"
        >
          <template>
            <el-tabs :stretch="true" active-name="activeName" @tab-click="handleClick">
              <el-tab-pane label="未读" name="未读"></el-tab-pane>
              <el-tab-pane label="已读" name="已读"></el-tab-pane>
            </el-tabs>
          </template>
          <el-table
              :data="tableData" :show-header="false" >
            <el-table-column type="expand">
              <template #default="props">
                <el-form label-position="center" inline class="demo-table-expand">
                  <el-form-item label="" style="width: 100%">
                    <span>{{ props.row.smMan }}：</span>
                  </el-form-item>
                  <el-form-item label="" style="width: 100%" >
                    {{ props.row.smContent }}
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column
                prop="smTitle" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column
                prop="smPtime" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column>
              <template #default="props">
                <el-link v-show="dis" type="info" @click="changeStatus(props.row.smId)">标为已读...</el-link>
              </template>
            </el-table-column>
          </el-table>
        </el-drawer>
        <Logs></Logs>
        <el-main style="height: 682px">
          <div id="app">
            <router-view></router-view>

          </div>
        </el-main>

        <el-footer class="footer">版权所有©五邑大学-梁卓林</el-footer>
      </el-container>

    </el-container>
  </div>

</template>

<script>
import Sheader from "../components/Sheader";
import Saside from "../components/Saside";
import Logs from "../components/Logs";
export default {
  name: "Student",
  components: { Sheader,Saside,Logs
  },
  data(){
    return {
      blogs:[],
      isRouterAlive:true,
      drawer: false,
      direction: 'rtl',
      tableData: [],
      activeName:'未读',
      dis:true,
      noRead:''
    }
  },
  computed:{

  },
  methods: {
    choose(data){
      this.$router.push('/'+data)
    },
    msg(){
      this.getNoRead()
      this.drawer = true
    },
    changeStatus(data){

      const _this = this
      _this.$axios.post('/student/changemessagestatus',{smId:data,smStatus:'已读'},{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{

        if(res.data.code === 200)
          this.getNoRead()
      })

    },
    handleClick(tab) {
      if(tab.name == '未读'){
        this.getNoRead()
        this.dis = true
      }else{
        this.dis = false
        this.getRead()
      }
    },
    getRead(){

      const _this = this
      _this.$axios.get('/student/read?smaccept='+_this.$store.getters.getUser.userId,{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{

        if(res.data.code === 200){

          _this.tableData = res.data.data
          return
        }

      })

    },
    getNoRead(){
      const _this = this
      _this.$axios.get('/student/noread?smaccept='+_this.$store.getters.getUser.userId,{
        headers: {
          Authorization: localStorage.getItem('token')
        }
      }).then(res=>{
        if(res.data.code === 200){
          _this.tableData = res.data.data
          _this.noRead = _this.tableData.length
        }

      })
    },
    title(){
      return '新消息（'+this.noRead+'）'
    }
  },
  created() {
    this.getNoRead()
  }
}
</script>

<style scoped>
.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  /*line-height: 160px;*/
  padding: unset;
  margin-top: 16px;
}
.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}
.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.containor{
  background: #E9EEF3;
  overflow: hidden;
  position: relative;
}
.footer{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 34px;
  max-height: 34px;
}


</style>
