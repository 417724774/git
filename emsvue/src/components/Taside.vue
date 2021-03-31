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
            <span>发布管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1" @click.native="choose('teacher_jobfair')" >招聘会信息</el-menu-item>
            <el-menu-item index="1-2" @click.native="choose('Teacher_message')" >宣传或通知</el-menu-item>
            <el-menu-item index="1-3" @click.native="choose('teacher_policy')" >就业政策</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template #title>
            <i class="el-icon-office-building"></i>
            <span>审核信息</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="2-1" @click.native="choose('teacher_company')" >企业注册</el-menu-item>
            <el-menu-item index="2-2" @click.native="choose('teacher_company_job')" >企业招聘</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
          <template #title>
            <i class="el-icon-tickets"></i>
            <span>学生信息</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="3-1" @click.native="choose('teacher_statistic')" >就业统计</el-menu-item>
            <el-menu-item index="3-2" @click.native="choose('teacher_student')" >学生信息</el-menu-item>
          </el-menu-item-group>
        </el-submenu>

      </el-menu>
    </el-col>
  </el-aside>
</template>

<script>
export default {
  name: "Taside",
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