<template>
  <el-aside width="11%">
    <el-col :span="12" style="width: 100%">
      <el-menu
          :uniqueOpened="true"
          default-active="2"
          class="el-menu-vertical-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
        <el-submenu index="1">
          <template #title>
            <i class="el-icon-user"></i>
            <span>招聘管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1" @click.native="choose('company_job')" >招聘信息</el-menu-item>
            <el-menu-item index="1-2" @click.native="choose('company_resume')" >简历信箱</el-menu-item>
            <el-menu-item index="1-3" @click.native="choose('company_jion')" >招聘会信息</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template #title>
            <i class="el-icon-office-building"></i>
            <span>查询学生</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="2-1" @click.native="choose('company_student')" >学生信息</el-menu-item>
            <el-menu-item index="2-2" @click.native="choose('company_statistic')" >学生统计</el-menu-item>
          </el-menu-item-group>
        </el-submenu>

      </el-menu>
    </el-col>
  </el-aside>
</template>

<script>
export default {
  name: "Caside",
  methods:{
    choose(data){
      this.$emit("choose",data)
    }
  }
}
</script>

<style scoped>
.el-aside {
  background-color: #545c64;
  color: #333;
  text-align: center;
  line-height: 200px;
  max-width: 100%;
}
.el-menu-vertical-demo{
  width: 99%;
  border: none;
  height: auto;
  text-align: left;
}
</style>