<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入宿舍号" class="ml-5"
                v-model="dormitoryName"></el-input>
      <el-select style="margin-left: 8px" v-model="region" placeholder="请选择地区">
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


    <!-- 添加表格列的线条-->
    <el-table :data="tableData" border stripe header-cell-class-name="headerBg">
      <el-table-column prop="region" label="地区" width="250"></el-table-column>
      <el-table-column prop="dormitoryName" label="宿舍号" width="250"></el-table-column>
      <el-table-column prop="studentnumber" label="现有人数" width="245"></el-table-column>
      <el-table-column prop="first" label="一号床" width="100"></el-table-column>
      <el-table-column prop="second" label="二号床" width="100"></el-table-column>
      <el-table-column prop="third" label="三号床" width="100"></el-table-column>
      <el-table-column prop="fourth" label="四号床" width="100"></el-table-column>

      <el-table-column prop="sex" label="性别" width="300"></el-table-column>

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
  name: "Bed",
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
      roles: [],
      majorID: "",
      dormitoryName: "",
      sex: "",
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

      this.request.get("/" + this.user.role + "/emptySite", {
        params: {
          region: this.region,
          dormitoryName: this.dormitoryName,
          sex: this.user.sex,
          majorID: this.user.majorID,
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }
      }).then(res => {
        console.log(res)
        if (res.code === '200') {
          this.tableData = res.data.data
          this.total = res.data.total
        } else {
          this.$message(res.msg)

        }
      })
    },
    reset() {
      this.dormitoryName = ""
      this.region = ""
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

<style>.headerBg {
  background-color: #eee !important;
}
</style>