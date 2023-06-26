<template>
  <div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入宿舍号" class="ml-5"
                v-model="dormitoryName"></el-input>
      <el-select style="margin-left: 8px" v-model="regions" placeholder="请选择地区">
        <el-option
            v-for="item in regionOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
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
    <el-table :data="tableData" border stripe header-cell-class-name="headerBg" style="text-align: center"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="65"></el-table-column>
      <el-table-column prop="dormitoryID" label="ID" width="80"></el-table-column>
      <el-table-column prop="region" label="地区" width="123"></el-table-column>
      <el-table-column prop="dormitoryName" label="宿舍号" width="180"></el-table-column>
      <el-table-column prop="first" label="床位一" width="150"></el-table-column>
      <el-table-column prop="second" label="床位二" width="150"></el-table-column>
      <el-table-column prop="third" label="床位三" width="150"></el-table-column>
      <el-table-column prop="fourth" label="床位四" width="150"></el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="你确定删除吗？"
              @confirm="del(scope.row.dormitoryID)">
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

    <el-dialog title="宿舍信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="地区">
          <el-select clearable v-model="form.newRegion" placeholder="请选择地区" style="width: 100%">
            <el-option v-for="item in regionOptions" :key="item.value" :label="item.label"
                       :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="宿舍号">
          <el-input v-model="form.newDormitoryName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="专业名">
          <el-select v-model="form.majorName" placeholder="请选择">
            <el-option v-for="(room,index) in majorNames" :key="index" :label="room" :value="room">
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
  name: "AdminRoomMessage",
  data() {
    return {
      tableData: [],
      RoomId: "",
      total: 0,
      pageNum: 1,
      pageSize: 5,
      stunember: "",
      username: "",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      roles: [],
      dormitoryName: "",
      bedNumber: "",
      majorNames: [],
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
      regions: "",
      region: "",
    }
  },
  created() {
    if (location.href.indexOf("#reloaded") == -1) {
      location.href = location.href + "#reloaded"
      location.reload();
    }
    //请求分页查询数据
    this.load()
  },
  methods: {
    //请求后台页面数据的一个方法
    load() {

      this.request.get("/admin/dormitoryMsg", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          dormitoryName: this.dormitoryName,
          regions: this.regions
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.data
        this.total = res.data.total
        this.majorNames = res.data.majorNames;
      })

      this.request.get("/role").then(res => {
        this.roles = res.data

      })

    },
    save() {
      this.request.post("/admin/editDormitory", this.form)
          .then(res => {
            if (res.code === '200') {
              this.$message.success("修改成功")
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
      this.request.delete("/admin/" + id)
          .then(res => {
            if (res.code === '200') {
              this.$message.success("删除成功")
              this.load()
            } else {
              this.$message.error(res.msg)
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
      this.roomNumber = ""
      this.regions = ""
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
