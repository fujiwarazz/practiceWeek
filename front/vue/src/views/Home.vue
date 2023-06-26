<template>
  <div style="color: #666;font-size: 14px;">
    <div style="padding-bottom: 20px">
      <span v-if="user.role==='teacher'">
      <b style="font-size: 20px">欢迎你！{{ user.teacherName }}</b>
      </span>
      <span v-if="user.role==='student'">
      <b style="font-size: 20px">欢迎你！{{ user.studentName }}</b>
      </span>
      <span v-if="user.role==='admin'">
      <b style="font-size: 20px">欢迎你！{{ user.username }}</b>
      </span>
    </div>
    <el-card style="font-size: 100px;text-align: center">
     欢迎使用本系统
    </el-card>
    <div style="height: 1px; background: #ddd; margin: 20px 0"/>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  }
}
</script>
