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
            <span>个人简历</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1" @click.native="choose('student_resume')" >简历管理</el-menu-item>
            <el-menu-item index="1-2" @click.native="choose('student_delivery')" >快速投递</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template #title>
            <i class="el-icon-office-building"></i>
            <span>求职信息</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="2-1" @click.native="choose('student_company')" >企业信息</el-menu-item>
            <el-menu-item index="2-2" @click.native="choose('student_policy')" >就业政策</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
          <template #title>
            <i class="el-icon-tickets"></i>
            <span>就业信息</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="3-1" @click.native="choose('student_register')" >资料登记</el-menu-item>
          </el-menu-item-group>
        </el-submenu>

      </el-menu>
    </el-col>
  </el-aside>
</template>

<script>
export default {
  name: "Saside",
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