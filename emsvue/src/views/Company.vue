<template>

  <div>

    <Cheader v-on:choose="choose" v-on:msg="msg">
    </Cheader>
    <el-container>
      <Caside v-on:choose="choose"></Caside>
      <el-container class="containor">
        <el-drawer
            title="title()"
            :visible.sync="drawer"
            style="position: absolute;text-align: center"
            z-index="18"
            :direction="direction"
            :size="350"
        >
          <template>
          <el-tabs :stretch="true" v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="未读" name="未读"></el-tab-pane>
            <el-tab-pane label="已读" name="已读"></el-tab-pane>
          </el-tabs>
        </template>
          <el-table
              :data="tableData" :show-header="false" style="font-size: 2px">
            <el-table-column type="expand">
              <template #default="props">
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="" style="width: 100%">
                    <span>{{ props.row.tnMan }}：</span>
                  </el-form-item>
                  <el-form-item label="" style="width: 100%">
                    <span>{{ props.row.tnContent }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>
            <el-table-column
                prop="tnTitle" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column
                prop="tnPtime" :show-overflow-tooltip="true">
            </el-table-column>
            <el-table-column>
              <template #default="props">
              <el-link v-show="dis" type="info" @click="changeStatus(props.row.tnId)">标为已读...</el-link>
              </template>
            </el-table-column>
          </el-table>
        </el-drawer>
        <Logs></Logs>
        <el-main style="height: 682px">
          <div id="app">
            <router-view v-if="isRouterAlive"></router-view>
          </div>
        </el-main>

        <el-footer class="footer">版权所有©五邑大学-梁卓林</el-footer>
      </el-container>

    </el-container>
  </div>

</template>

<script>
import Cheader from "../components/Cheader";
import Caside from "../components/Caside";
import Logs from "../components/Logs";
export default {
  name: "Company",
  provide (){
    return {
      reload:this.reload
    }
  },
  components: { Cheader,Caside,Logs
  },
  data(){
    return {
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
    reload (){
      this.isRouterAlive = false
      this.$nextTick(function(){
        this.isRouterAlive = true
      })
    },
    msg(){
      this.getNoRead()
      this.drawer = true
    },
    changeStatus(data){

      const _this = this
      _this.$axios.post('/company/changenotificationstatus',{tnId:data,tnStatus:'已读'},{
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
      _this.$axios.get('/company/read?tnaccept='+_this.$store.getters.getUser.userId,{
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
      _this.$axios.get('/company/noread?tnaccept='+_this.$store.getters.getUser.userId,{
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
