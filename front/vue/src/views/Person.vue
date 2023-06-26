<template>
  <el-card style="width: 500px">
    <el-form label-width="80px" size="small">
      <el-form-item label="用户名">
        <el-input v-model="this.user.studentName" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="学号">
        <el-input v-model="this.user.username" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="专业">
        <el-input v-model="this.user.majorName" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="this.user.sex" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地区">
        <el-input v-model="this.user.region" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="宿舍号">
        <el-input v-model="this.user.dormitoryNumber" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="床位号">
        <el-input v-model="this.user.bedNum" disabled autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "Person",
  data(){
    return{
      form:{},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    // this.getUser().then(res => {
    //   console.log(res)
    //   this.form = res
    // })
  },
  methods:{
    // async getUser() {
    //   return (await this.request.get("/"+this.user.role+"/getPersonInfo/" + this.user.username)).data
    // },

  }
}
</script>
