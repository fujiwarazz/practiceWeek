<template>
  <div style="line-height: 60px;display:flex">
  <div style="flex: 1; " >
    <span :class="collapseBtnClass" style="cursor: pointer;font-size: 18px" @click="collapse"></span>

    <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
      <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
      <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
    <span v-if="user.role==='student'">
      <div style="flex: 1;margin-left: 500px" @click="Notice">
        <img src="../assets/notice.png" style="width: 15px;margin-top: 20px;margin-left: 350px">
      </div>
    </span>

  <el-dropdown style="width: 180px;cursor: pointer;text-align: right">
    <div style="display: inline-block">
      <span>{{ user.username }}</span><i class="el-icon-arrow-down" style="margin-right: 5px;width: 30px"></i>
    </div>
    <el-dropdown-menu slot="dropdown" style="width: 100px;text-align: center ">
      <el-dropdown-item  >
<!--        <router-link to="/person">个人信息</router-link>-->
        <span @click="PersonMessage">个人信息</span>
      </el-dropdown-item>
      <el-dropdown-item style="font-size: 14px; padding: 5px 0">
<!--        <router-link to="/password">修改密码</router-link>-->
        <span @click="ChangePassword">修改密码</span>
      </el-dropdown-item>
      <el-dropdown-item>
        <span style="text-decoration: none" @click="logout">退出</span>
<!--        <router-link to="/login" style="text-decoration: none">退出</router-link>-->
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass:String,
    collapse:Function
  },
  computed: {
    currentPathName () {
      return this.$store.state.currentPathName;　　//需要监听的数据
    }
  },
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods:{
    logout(){
     this.$store.commit("logout")
      this.$message.success("退出成功");
    },
    PersonMessage(){
      this.$router.push("/person");
    },
    ChangePassword(){
      this.$router.push("/password");
    },
    Notice(){
      if(this.user.role=="student")
      {
        this.$router.push("/studentNotice")
      }
     else{
        this.$message.error("无权限访问")
      }
    }
  }
}
</script>

