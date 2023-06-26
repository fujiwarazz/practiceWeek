<template>
  <div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入姓名" v-model="studentName"></el-input>
      <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入学号" class="ml-5" v-model="studentID"></el-input>
      <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入教师姓名" class="ml-5" v-model="teacherName"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button  type="warning" @click="reset">重置</el-button>
    </div>


    <div style="margin: 10px 0">
      <el-button  type="primary" @click="handleAddStudent">新增学生 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button  type="primary" @click="handleAddTeacher">新增导员 <i class="el-icon-circle-plus-outline"></i></el-button>
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
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="studentName" label="学生名" width="260"></el-table-column>
      <el-table-column prop="username" label="学号" width="240"></el-table-column>
      <el-table-column prop="majorName" label="专业" width="240"></el-table-column>
      <el-table-column prop="sex" label="性别" width="240"></el-table-column>
      <el-table-column label="操作" width="240" align="center">
        <template slot-scope="scope">
<!--          <el-button type="success" @click="handleEditStudent(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>-->
                    <el-popconfirm
                        class="ml-5"
                        confirm-button-text='确定'
                        cancel-button-text='取消'
                        icon="el-icon-info"
                        icon-color="red"
                        title="你确定删除吗？"
                        @confirm="delStudent(scope.row.username)"
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
          :total="StudentTotal">
      </el-pagination>
    </div>


    <el-table :data="tableData2" border stripe header-cell-class-name="headerBg"  style="margin-top: 50px" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="teacherName" label="教师名" width="260"></el-table-column>
      <el-table-column prop="username" label="账号" width="240"></el-table-column>
      <el-table-column prop="majorName" label="专业" width="240"></el-table-column>
      <el-table-column prop="sex" label="性别" width="240"></el-table-column>
      <el-table-column label="操作" width="240" align="center">
        <template slot-scope="scope">
<!--          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>-->
                    <el-popconfirm
                        class="ml-5"
                        confirm-button-text='确定'
                        cancel-button-text='取消'
                        icon="el-icon-info"
                        icon-color="red"
                        title="你确定删除吗？"
                        @confirm="delTeacher(scope.row.username)"
                    >
                      <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange2"
          @current-change="handleCurrentChange2"
          :current-page="pageNum2"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize2"
          layout="total, sizes, prev, pager, next, jumper"
          :total="TeacherTotal">
      </el-pagination>
    </div>


    <el-dialog title="新增/编辑 学生" :visible.sync="dialogFormVisible1" width="30%">
      <el-form label-width="80px"  size="small">
        <el-form-item label="学生姓名" >
          <el-input v-model="studentForm.studentName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学生学号" >
          <el-input v-model="studentForm.username" @blur="checkExist" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="学生年龄" >
          <el-input v-model="studentForm.age" autocomplete="off" @input="studentForm.age = studentForm.age.replace(/[^\d]/g,'')"></el-input>
        </el-form-item>

        <el-form-item label="专业名">
          <el-select v-model="studentForm.majorName" placeholder="请选择">
            <el-option v-for="(major,index) in majorName" :key="index" :label="major" :value="major">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学生年级">
          <el-select v-model="studentForm.grade" placeholder="请选择" style="color: black">
            <el-option value="1">
              大一
            </el-option>
            <el-option value="2">
              大二
            </el-option>
            <el-option value="3">
              大三
            </el-option>
            <el-option value="4">
              大四
            </el-option>
            <el-option value="5">
              大五
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="studentForm.sex" placeholder="请选择" style="color: black">
            <el-option value="男">
              男性
            </el-option>
            <el-option value="女">
              女性
            </el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="saveStudent">确 定</el-button>
      </div>
    </el-dialog>


    <el-dialog title="新增/编辑 导员" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px"  size="small">
        <el-form-item label="导员姓名" >
          <el-input v-model="form.teacherName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="导员账号" >
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="导员专业">
          <el-select v-model="form.majorName" placeholder="请选择">
            <el-option v-for="(major,index) in majorName" :key="index" :label="major" :value="major">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="导员性别">
          <el-select v-model="form.sex" placeholder="请选择" style="color: black">
            <el-option value="男">
              男性
            </el-option>
            <el-option value="女">
              女性
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveTeacher">确 定</el-button>
      </div>
    </el-dialog>



  </div>
</template>

<script>
export default {
  name: "User",
  data(){
    return{
      majorName:[],
      tableData:[],
      tableData2:[],
      TeacherTotal:0,
      StudentTotal:0,
      pageNum:1,
      pageSize:5,
      pageNum2:1,
      pageSize2:5,
      studentName:"",
      sex:"",
      studentID:"",
      teacherName:"",
      form :{},
      dialogFormVisible:false,
      multipleSelection:[],
      roles:[],
      major:"",
      role:"",
      dialogFormVisible1:false,
      studentForm:{},

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
  methods:{
    //请求后台页面数据的一个方法
    checkExist(){
      this.request.get("/admin/check",{
        params:{
          studentID:this.studentForm.username
        }
      }).then(res=>{
        if(!res){
          this.$message.error("已存在该学号！");
        }
      })
    },
    load(){
      this.request.get("/admin/getAll", {
        params:{
          pageNum: this.pageNum,
          pageSize : this.pageSize,
          pageNum2: this.pageNum2,
          pageSize2 : this.pageSize2,
          studentName :this.studentName,
          studentID:this.studentID,
          teacherName:this.teacherName
        }
      }).then(res =>{
        console.log(res)
        this.tableData=res.data.StudentList
        this.tableData2=res.data.TeacherList
        this.TeacherTotal =res.data.TeacherTotal
        this.StudentTotal = res.data.StudentTotal
        this.majorName = res.data.majorNames
      })

      this.request.get("/role").then(res=>{
        this.roles=res.data

      })

    },
    saveStudent(){

      this.request.post("/admin/addStudent",this.studentForm)
          .then(res =>{
            if(res.code==='200')
            {
              this.$message.success("保存成功")
              this.dialogFormVisible = false
              this.load()
            }
            else{
              this.$message.error(res.msg)
            }
          })
    },
    saveTeacher(){
      this.request.post("/admin/addTeacher",this.form)
          .then(res =>{
            if(res.code==='200')
            {
              this.$message.success("保存成功")
              this.dialogFormVisible = false
              this.load()
            }
            else{
              this.$message.error(res.msg)
            }
          })
    },
    handleAddTeacher(){
      this.dialogFormVisible=true
      this.form={}
    },
    handleAddStudent(){
      this.dialogFormVisible1=true
      this.studentForm={}
    },
    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },
    handleEditStudent(row){
      this.studentForm=row
      this.dialogFormVisible1=true
    },
    delStudent(id){
      this.request.delete("/student/studentD/"+id)
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
    delTeacher(id){
      this.request.delete("/teacher/teacherD/"+id)
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
      this.username=""
      this.stunember=""
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
    handleSizeChange2(pageSize2){
      this.pageSize2=pageSize2
      this.load()
    },
    handleCurrentChange2(pageNum2){
      this.pageNum2=pageNum2
      this.load()
    },
  }
}
</script>
