<template>
  <div>
    <!-- 添加表格列的线条-->
    <el-table :data="tableData" border stripe header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="65"></el-table-column>
      <el-table-column prop="noticeID" label="ID" width="80"></el-table-column>
      <el-table-column prop="teacherName" label="发布人" width="310"></el-table-column>
      <el-table-column prop="detail" label="发布内容" width="310"></el-table-column>
      <el-table-column prop="createtime" label="发布时间" width="310"></el-table-column>
      <el-table-column label="操作" width="213" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="你确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "StudentNotice",
  data(){
    return{

      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},

      tableData:[],
      RoomId:"",
      total:0,
      pageNum:1,
      pageSize:5,
      stunember:"",
      username:"",
      form :{},
      dialogFormVisible:false,
      multipleSelection:[],
      roles:[],
      roomNumber:"",
      bedNumber:"",
      notice:"",
      studentObjects: [{
        value: 'AllStudents',
        label: '全体学生'
      }, {
        value: 'MyStudents',
        label: '我的学生'
      }],
      Objects:"",
      region:"",
      pickdate: '',
    }
  },
  created() {
    //请求分页查询数据
    this.load()
    if (location.href.indexOf("#reloaded") == -1) {
      location.href = location.href + "#reloaded"
      location.reload();
    }

  },
  methods:{
    //请求后台页面数据的一个方法
    load(){
      this.request.get("/student/myNotification", {
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          studentID:this.user.username
        }
      }).then(res =>{
        console.log(res)
        this.tableData=res.data.data
        this.total =res.data.total
      })
    },
    save(){
      this.request.post("/user",this.form)
          .then(res =>{
            if(res.code==='200')
            {
              this.$message.success("保存成功")
              this.dialogFormVisible = false
              this.load()
            }
            else{
              this.$message.error("保存失败")
            }
          })
    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },
    del(id){
      this.request.delete("/user/"+id)
          .then(res =>{
            if(res.code==='200')
            {
              this.$message.success("删除成功")
              this.load()
            }
            else{
              this.$message.error("删除失败")
            }
          })
    },
    delBatch(){
      let ids =this.multipleSelection.map(v => v.id)//[{},{},{}]  => [1,2,3]
      this.request.post("/user/del/batch",ids).then(res =>{
        if(res.code==='200')
        {
          this.$message.success("批量删除成功")
          this.load()
        }
        else{
          this.$message.error("批量删除失败")
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection=val
    },
    reset(){
      this.roomNumber=""
      this.regions=""
      this.load()
    },
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum=pageNum
      this.load()
    },
  }
}
</script>
