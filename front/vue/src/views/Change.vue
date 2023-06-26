<template>
  <el-card style="width: 500px">
    <el-form label-width="80px" size="small">

      <el-form-item label="申请人">
        <el-input style="width: 205px;" v-model="form.studentName" disabled :value="this.studentName">
        </el-input>
      </el-form-item>

      <el-form-item label="申请人" hidden>
        <el-input style="width: 205px;"  v-model="form.majorName"  :value="this.user.majorName">
        </el-input>
      </el-form-item>

      <el-form-item label="宿舍号">
        <el-select v-model="form.changeRoom" placeholder="请选择">
          <el-option v-for="(room,index) in changeRoom" :key="index" :label="room" :value="room">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="床位">
        <el-select v-model="form.bedNum" placeholder="请选择" style="color: black">
          <el-option value=1>
            一号床
          </el-option>
          <el-option value=2>
            二号床
          </el-option>
          <el-option value=3>
            三号床
          </el-option>
          <el-option value=4>
            四号床
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="申请理由">
        <el-input type="textarea" v-model="form.reason" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "Change",

  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      bedNum:'',
      form: {},
      changeRoom: [],
      regions: [],
      reason: "",
      studentName: '',
      majorName:''
    }
  },
  created() {

    if (location.href.indexOf("#reloaded") == -1) {
      location.href = location.href + "#reloaded"
      location.reload();
    }
    this.load()
  },
  methods: {
    load() {

      this.request.get("/student/change",{
        params:{
          studentName:this.user.username,
          sex:this.user.sex,
          majorName:this.user.majorName,
          dormitoryName:this.user.dormitoryNumber
        }
      }).then(res => {
        if (res.code === '200') {
          this.regions = res.data.regions
          this.changeRoom = res.data.dormitoryNames
          this.form.studentName = this.user.username
          this.form.majorName = this.user.major

        }else {
          this.$message.error(res.msg)
        }
      })
    },
    save() {
      this.request.post("/student/uploadChange", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("申请成功")
          this.form={}
        } else {
          this.$message.error(res.msg)
        }
      })
    },

  }
}
</script>
