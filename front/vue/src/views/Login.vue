<template>
  <div class="wrapper">
    <div
        style="margin: 150px auto; background-color: #fff; width: 380px; height: 450px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>宿舍管理系统</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password
                    v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item prop="code">
          <el-input size="medium" style="float: left;width: 200px;padding-top: 6px" v-model="user.code" placeholder="请输入验证码" clearable></el-input>
          <div style="float: left;padding-top: 10px;padding-left: 10px" ><el-image  :src="imgUrl" @click="getVerify()"/></div>
        </el-form-item>
        <el-form-item>
          <el-select v-model="user.role" placeholder="请选择">
            <el-option
                v-for="item in roleOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-checkbox style=" text-align: left;margin-top:10px " v-model="checked">记住我</el-checkbox>
        </el-form-item>


        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {setRoutes} from "@/router";
import qs from "qs";


const Base64 = require("js-base64").Base64

export default {
  name: "Login",
  data() {
    return {
      user: {
        username: "",
        password: "",
        role: "",
        code: ""
      },
      pageNum: 1,
      rules: {
        code: [
          {
            required: true,
            message: "请输入验证码",
            trigger: "blur",
          },
        ],
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 5, max: 8, message: '长度在 5 到 8 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        ],
      },
      checked: false,
      roleOptions: [{
        value: 'admin',
        label: '管理员'
      }, {
        value: 'teacher',
        label: '辅导员'
      }, {
        value: 'student',
        label: '学生'
      }],
      value: '',
      menus0: [
        {
          "id": 1,
          "name": "主页",
          "path": "/home",
          "icon": "el-icon-house",
          "description": null,
          "children": [],
          "pid": null,
          "pagePath": "Home"
        }, {
          "id": 2,
          "name": "系统管理",
          "path": null,
          "icon": "el-icon-s-grid",
          "description": "",
          "children": [
            {
              "id": 3,
              "name": "用户管理",
              "path": "/user",
              "icon": "el-icon-user",
              "description": null,
              "children": null,
              "pid": 2,
              "pagePath": "User"
            },
            {
              "id": 4,
              "name": "管理宿舍信息",
              "path": "/adminRoomMessage",
              "icon": "el-icon-user",
              "description": null,
              "children": null,
              "pid": 2,
              "pagePath": "AdminRoomMessage"
            },
            {
              "id": 5,
              "name": "床位信息",
              "path": "/bed",
              "icon": "el-icon-house",
              "description": null,
              "children": null,
              "pid": 2,
              "pagePath": "Bed"
            }
          ],
          "pid": null,
          "pagePath": null
        }
      ],
      menus1: [
        {
          "id": 1,
          "name": "主页",
          "path": "/home",
          "icon": "el-icon-house",
          "description": null,
          "children": [],
          "pid": null,
          "pagePath": "Home"
        }, {
          "id": 2,
          "name": "系统管理",
          "path": null,
          "icon": "el-icon-s-grid",
          "description": "",
          "children": [
            {
              "id": 6,
              "name": "本专业学生住宿信息",
              "path": "/teacher",
              "icon": "el-icon-user",
              "description": null,
              "children": null,
              "pid": 2,
              "pagePath": "Teacher"
            },
            {
              "id": 7,
              "name": "床位信息",
              "path": "/bed",
              "icon": "el-icon-house",
              "description": null,
              "children": null,
              "pid": 2,
              "pagePath": "Bed"
            },
            {
              "id": 8,
              "name": "交换宿舍信息处理",
              "path": "/feedBack",
              "icon": "el-icon-house",
              "description": null,
              "children": null,
              "pid": 2,
              "pagePath": "FeedBack"
            },
            {
              "id": 11,
              "name": "公告发布",
              "path": "/teacherNotice",
              "icon": "el-icon-house",
              "description": null,
              "children": null,
              "pid": 2,
              "pagePath": "TeacherNotice"
            }
          ],
          "pid": null,
          "pagePath": null
        }
      ],
      menus2: [
        {
          "id": 1,
          "name": "主页",
          "path": "/home",
          "icon": "el-icon-house",
          "description": null,
          "children": [],
          "pid": null,
          "pagePath": "Home"
        }, {
          "id": 2,
          "name": "系统管理",
          "path": null,
          "icon": "el-icon-s-grid",
          "description": "",
          "children": [
            {
              "id": 9,
              "name": "床位信息",
              "path": "/bed",
              "icon": "el-icon-house",
              "description": null,
              "children": null,
              "pid": 2,
              "pagePath": "Bed"
            },
            {
              "id": 10,
              "name": "申请交换宿舍",
              "path": "/change",
              "icon": "el-icon-house",
              "description": null,
              "children": null,
              "pid": 2,
              "pagePath": "Change"
            },
          ],
          "pid": null,
          "pagePath": null
        }
      ],
      imgUrl: "",
    }
  },

  mounted() {

    this.imgUrl = "http://localhost:9090/Code/getVerify";
    //this.getVerify()
    let username = localStorage.getItem("username");
    if (username) {
      this.user.username = localStorage.getItem("username")
      this.user.password = Base64.decode(localStorage.getItem("password"));// base64解密
      this.checked = true
    }
  },

  methods: {
    login() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.request.get("/Code/checkVerify", {
            params: {
              verifyInput: this.user.code,
            }
          }).then(res => {
            if(res.code!=='200'){
              this.$message.error("验证码错误");
            }else if(res.code==='200'){
              this.request.post("/" + this.user.role + "/login", this.user).then(res => {
                if (res.code === '200') {
                  localStorage.setItem("user", JSON.stringify(res.data))//存储用户信息到到浏览器
                  if (this.user.role == "admin") {
                    localStorage.setItem("menus", JSON.stringify(this.menus0))//存储菜单数组到到浏览器
                  }
                  if (this.user.role == "teacher") {
                    localStorage.setItem("menus", JSON.stringify(this.menus1))//存储菜单数组到到浏览器
                  }
                  if (this.user.role == "student") {
                    localStorage.setItem("menus", JSON.stringify(this.menus2))//存储菜单数组到到浏览器
                  }
                  // console.log(JSON.stringify(res.data.menus))
                  if (this.checked) {
                    let password = Base64.encode(this.user.password); // base64加密
                    localStorage.setItem("username", this.user.username);
                    localStorage.setItem("password", password);
                  }
                  //动态设置当前用户的路由
                  setRoutes()
                  this.$router.push("/")
                  this.$message.success("登录成功")
                } else {
                  this.$message.error("密码账号错误或者是权限错误")
                }
              })
            }
          })
        }
      });
    },
    getVerify() {
      this.imgUrl = "http://localhost:9090/Code/getVerify?" + Math.random();
    },
    aVerify() {
      this.request.get("/Code/checkVerify", {
        params: {
          verifyInput: this.user.code,
        }
      }).then(res => {
        return res.code === '200';
      })
    },
    resetForm() {
      this.$refs["elForm"].resetFields();
    },
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
  overflow: hidden;
}
</style>