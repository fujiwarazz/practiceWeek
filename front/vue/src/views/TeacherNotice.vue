<template>
  <div>

    <div style="margin: 10px 0">
      <el-button  type="primary" @click="handleAdd">发布通知<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="你确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button  type="danger"  slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <!-- 添加表格列的线条-->
    <el-table :data="tableData" border stripe header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="65"></el-table-column>
      <el-table-column prop="noticeID" label="ID" width="80"></el-table-column>
      <el-table-column prop="studentNames" label="发布对象" width="310"></el-table-column>
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
              @confirm="del(scope.row.noticeID)"
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
    <el-dialog title="公告信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="85px"  size="small">
        <el-form-item label="对象">
          <el-button  type="primary" @click="handleAddStudents">选择对象</el-button>
        </el-form-item>
        <el-form-item label="发布时间" >
          <el-date-picker
              v-model="pickdate"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="通知内容" >
          <el-input type="textarea" v-model="form.detail" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="upload">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="选择学生" :visible.sync="dialogStudentFormVisible" width="40%">
      <el-form label-width="85px"  size="small">
        <el-form-item label="对象">
          <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
          <div style="margin: 15px 0;"></div>
          <el-checkbox-group v-model="checkedStudents" @change="handleCheckedStudentsChange">
            <el-checkbox v-for="student in students" :label="student" :key="student">{{student}}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogStudentFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveStudent(checkedStudents)">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

export default {

  name: "TeacherNotice",
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
      dialogStudentFormVisible:false,
      multipleSelection:[],
      roles:[],
      roomNumber:"",
      bedNumber:"",
      notice:"",
      Objects:"",
      region:"",
      pickdate: '',
      checkAll: false,
      checkedStudents: [],
      students: [],
      isIndeterminate: true
    }
  },
  created() {
    this.load()
  },
  mounted() {
    //请求分页查询数据
    this.load()
    if (location.href.indexOf("#reloaded") == -1) {
      location.href = location.href + "#reloaded"
      location.reload();
    }
  },
  methods:{
    load(){
      this.request.get("/teacher/myNotification", {
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          teacherID:this.user.teacherID
        }
      }).then(res =>{
        console.log(res)
        this.tableData=res.data.data
        this.total =res.data.total
      })

      this.request.get("/teacher/getAllStudentName",{
        params:{
          majorName:this.user.majorName
        }
      }).then(res=>{
        this.students=res.data
      })
    },

    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    handleAddStudents(){
      this.dialogStudentFormVisible=true
      this.form={}
    },
    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },
    del(id){
      this.request.delete("/teachernotice/"+id)
          .then(res =>{
            if(res)
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
    handleCheckAllChange(val) {
      this.checkedStudents = val ?  this.students : [];
      this.isIndeterminate = false;
    },
    handleCheckedStudentsChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.students.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.students.length;
    },
    saveStudent(students){
      console.log(students)
      this.dialogStudentFormVisible=false
      this.checkedStudents = students
    },
    upload(){
      this.form.studentList  =this.checkedStudents
      this.form.createtime = this.pickdate
      this.form.teacherID = this.user.teacherID
      this.request.post("/teacher/uploadNotice",this.form).then(res=>{
          if(res.code==='200'){
            this.$message.success("发布成功")
          }else{
            this.$message.error(res.msg)
          }
          this.dialogFormVisible = false
      })
      this.request.post("/teachernotice",this.form)
    }
  }
}
</script>



