<template>
  <div>

    <div style="margin: 10px 0">

      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="你确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <!-- 添加表格列的线条-->
    <el-table :data="tableData" border stripe header-cell-class-name="headerBg"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50"></el-table-column>
      <el-table-column prop="requirementID" label="ID" width="60"></el-table-column>
      <el-table-column prop="studentName" label="申请人" width="340"></el-table-column>
      <el-table-column prop="studentID" label="学号" width="325"></el-table-column>
      <el-table-column prop="st" label="状态" width="320"></el-table-column>

      <el-table-column label="操作" width="178" align="center">

        <template slot-scope="scope">
          <el-button type="primary" @click="handleEdit(scope.row)">处理</el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="你确定删除吗？"
              @confirm="del(scope.row.requirementID)"
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
    <el-dialog title="申请信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="85px" size="small">
        <el-form-item label="目的地区">
          <el-input v-model="form.region" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="目的宿舍号">
          <el-input v-model="form.dormitoryName" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="目的宿舍号">
          <el-input v-model="form.bedNum" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="申请理由">
          <el-input  type="textarea" v-model="form.reason" disabled autocomplete="off"></el-input>
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="danger" @click="refuse">拒绝</el-button>
        <el-button type="primary" @click="save">同意</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "FeedBack",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      username: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      roomNumber: "",
      stunumber: "",
      reason: "",
      region: ""
    }
  },
  created() {
    //请求分页查询数据

    if (location.href.indexOf("#reloaded") == -1) {
      location.href = location.href + "#reloaded"
      location.reload();
    }
    this.load()
  },
  methods: {
    //请求后台页面数据的一个方法
    load() {

      //--------------------一个查看学生自己的住宿情况的接口，需要获取的参数加上roomNumber, bedNumber。
      this.request.get("/teacher/feedBack", {
        params: {
          majorName: this.user.majorName,
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }
      }).then(res => {
        console.log(res)
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total

          console.log(res.data.total)
        }
      })

      this.request.get("/role").then(res => {
        this.roles = res.data

      })

    },
    save() {
      this.request.post("/teacher/handleRequirement", this.form)
          .then(res => {
            if (res.code === '200') {
              this.$message.success("保存成功")
              this.dialogFormVisible = false
              this.load()
            } else {
              this.$message.error(res.msg)
            }
          })
    },
    refuse(){
      this.dialogFormVisible = false
      this.request.post("/teacher/refuse",{
        params:{
          requirementID:this.form.requirementID,
          studentID:this.form.studentID,
          teacherID:this.user.teacherID
        }
      }).then(res=>{
        if(res.code==="200"){
          this.$message.success("拒绝成功")
        }else{
          this.$message.error(res.msg)
        }
      })
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    del(id) {
      this.request.delete("/requirement/" + id)
          .then(res => {
            if (res) {
              this.$message.success("删除成功")
              this.load()
            } else {
              this.$message.error("删除失败")
            }
          })
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)//[{},{},{}]  => [1,2,3]
      this.request.post("/user/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    reset() {
      this.username = ""
      this.email = ""
      this.address = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
  }
}
</script>

