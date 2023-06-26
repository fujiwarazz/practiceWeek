<template>
    <el-container style="min-height: 100vh" >
      <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
       <Aside :is-collapse="isCollapse" :logoTextShow="logoTextShow"/>
      </el-aside>

      <el-container>
<!--        设置顶部导航栏-->
<!--        line-height是垂直居中的-->
        <el-header style=" border-bottom: 1px solid #cccccc"
        >
          <Header :collapse-btn-class="collapseBtnClass" :collapse="collapse" :user="user"  />
        </el-header>
        <el-main>
<!--          表示当前页面的子路由会在 router-view 里面展示-->
          <router-view @refreshUser="getUser" />
        </el-main>
      </el-container>
    </el-container>
</template>

<script>
//把侧边和头部封装成两个组件，导入到主页中
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'Home',
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth:220,
      logoTextShow:true,
      user:  {}//**^^**
    }
  },
  components:{
    Aside,
    Header
},
  created() {
    if (location.href.indexOf("#reloaded") == -1) {
      location.href = location.href + "#reloaded"
      location.reload();
    }
    //从后台获取最新的User数据
    this.getUser();
  },
  methods:{
    // 点击收缩按钮触发
    collapse(){
    this.isCollapse=!this.isCollapse
      // 菜单收缩
      if(this.isCollapse){
        this.sideWidth=65
        this.collapseBtnClass='el-icon-s-unfold'
        this.logoTextShow=false
      }
      // 菜单展开
      else{
        this.sideWidth=220
        this.collapseBtnClass='el-icon-s-fold'
        this.logoTextShow=true
      }
    },
    getUser() {
      if (location.href.indexOf("#reloaded") == -1) {
        location.href = location.href + "#reloaded"
        location.reload();
      }
      let username =localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : ""
      //从后台获取User数据
      this.request.get("/user/username/" + username).then(res =>{
        //重新赋值后台的最新User数据 **^^**
        this.user=res.data
      })
    }
  }
}
</script>
