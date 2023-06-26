<template>
  <div>
    <!--  <div style="margin-bottom: 30px">-->

    <!--  </div>-->
    <div style="margin: 10px 0">
      <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入姓名" v-model="studentName"></el-input>
      <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入学号" class="ml-5"
                v-model="studentID"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
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
      <el-table-column type="selection" width="68"></el-table-column>
      <el-table-column prop="bedID" label="床号ID" width="160"></el-table-column>
      <el-table-column prop="username" label="学号" width="200"></el-table-column>
      <el-table-column prop="studentName" label="姓名" width="200"></el-table-column>
      <el-table-column prop="region" label="地区" width="200"></el-table-column>
      <el-table-column prop="dormitoryNumber" label="宿舍号" width="200"></el-table-column>
      <el-table-column label="操作" width="260" align="center">
        <template slot-scope="scope">
          <!--          <el-popconfirm-->
          <!--              class="ml-5"-->
          <!--              confirm-button-text='确定'-->
          <!--              cancel-button-text='取消'-->
          <!--              icon="el-icon-info"-->
          <!--              icon-color="red"-->
          <!--              title="你确定删除吗？"-->
          <!--              @confirm="del(scope.row.bedId)"-->
          <!--          >-->
          <!--            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>-->
          <!--          </el-popconfirm>-->
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
    <el-dialog title="宿舍信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="学号">
          <el-select v-model="form.myStudentList" placeholder="请选择" @blur="getName">
            <el-option v-for="(student,index) in myStudentList" :key="index" :label="student" :value="student" @blur="getName">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="姓名">
          <el-input v-model="studentName" autocomplete="off" style="width: 205px"></el-input>
        </el-form-item>

        <el-form-item label="宿舍号">
          <el-select v-model="form.dormitoryNumber" placeholder="请选择">
            <el-option v-for="(room,index) in dormitoryNumber" :key="index" :label="room" :value="room">
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Teacher",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      tableData: [],
      bedId: "",
      total: 0,
      pageNum: 1,
      pageSize: 5,
      studentName: "",
      studentID: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      roles: [],
      dormitoryNumber: [],
      bedNum: "",
      regionOptions: [{
        value: '北区',
        label: '北区'
      }, {
        value: '南区',
        label: '南区'
      }, {
        value: '中区',
        label: '中区'
      }],
      region: "",
      regions: "",
      myStudentList: [],
    }
  },
  created() {
    if (location.href.indexOf("#reloaded") == -1) {
      location.href = location.href + "#reloaded"
      location.reload();
    }
    //请求分页查询数据
    this.getUsefulDormitory();
    this.getMyStudent();
    this.load();

  },
  //失效
  methods: {
    getName() {
      this.request.get("/teacher/findName", {
        params: {
          studentID: this.form.myStudentList
        }
      }).then(res => {
        this.studentName = res.data
      })
    },

    getMyStudent() {
      this.request.get("/teacher/ToFindMyStudents", {
        params: {
          majorName: this.user.majorName
        }
      }).then(res => {
        this.myStudentList = res.data
      })
    },

    getUsefulDormitory() {

      this.request.get("/teacher/getUsefulDormitory", {
        params: {
          majorName: this.user.majorName
        }
      }).then(res => {
        this.dormitoryNumber = res.data
      })
    },
    //请求后台页面数据的一个方法
    //数据发布出去?
    load() {

      this.request.get("/" + this.user.role + "/myStudents", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          majorName: this.user.majorName,
          studentName: this.studentName,
          studentID: this.studentID
        }
      }).then(res => {
            if (res.code === '200') {
              console.log(res)
              this.tableData = res.data.list
              this.total = res.data.total
            } else {
              this.tableData = []
              this.$message.error(res.msg)
            }
          }
      )

    },
    save() {
      this.form.studentName = this.studentName
      this.request.post("/teacher/addStudent", this.form)
          .then(res => {
            if (res.code === '200') {
              this.$message.success("新增成功")
              this.dialogFormVisible = false
              this.load()
            } else {
              this.$message.error(res.msg)
            }
          })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    del(id) {
      this.request.delete("/teacher/" + id)
          .then(res => {
            if (res.code === '200') {
              this.$message.success("删除成功")
              this.load()
            } else {
              this.$message.error("删除失败")
            }
          })
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)//[{},{},{}]  => [1,2,3]
      this.request.post("/teacher/del/batch", ids).then(res => {
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
      this.stunember = ""
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
