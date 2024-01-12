<template>
  <div class="main">
    <el-button type="primary" @click="add" class="newTeamButton">新建任务</el-button>
    <el-dialog title="新增任务" v-model="dialogVisible" width="30%" >
      <el-form ref="form" :model="form" status-icon :rules="rules" label-width="120px">
        <el-form-item label="任务名称" prop="title">
          <el-input v-model="form.title" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="任务描述" prop="intro">
          <el-input v-model="form.intro" type="textarea" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="创建时间" prop="startdate">
          <el-date-picker type="date" placeholder="选择日期" v-model="form.startdate" style="width: 80%"></el-date-picker>
        </el-form-item>
        <el-form-item label="截止时间" prop="enddate">
          <el-date-picker type="date" placeholder="选择日期" v-model="form.enddate" style="width: 80%"></el-date-picker>
        </el-form-item>
        <el-form-item label="重要程度" prop="importance_level">
          <el-radio-group v-model="form.importanceLevel">
            <el-radio label="非常重要"></el-radio>
            <el-radio label="重要"></el-radio>
            <el-radio label="普通"></el-radio>
            <el-radio label="不重要"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="对接人学号" prop="interface_stuid">
          <el-input v-model="form.interfaceStuid" style="width:80%"></el-input>
        </el-form-item>
      </el-form>
      <template v-slot:footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="save('form')" class="confirmButton">确 定</el-button>
        </div>
      </template>
    </el-dialog>
    <div style="width: 100%; padding: 20px; border: 2px solid #dfdfdf; border-radius: 12px;">
      <el-table class="list" :data="tableData" border stripe style="width: 100%" @row-click="openDetails">
        <el-table-column prop="title" label="名称"> </el-table-column>
        <el-table-column prop="startdate" label="创建时间" sortable>
        </el-table-column>
        <el-table-column prop="enddate" label="截止时间" sortable>
        </el-table-column>
        <el-table-column prop="done" label="状态" width="110" :filters="[
          { text: '已完成', value: '已完成' },
          { text: '未完成', value: '未完成' },
          { text: '待审核', value: '待审核' },
          { text: '已终止', value: '已终止' },
        ]" :filter-method="filterDone">
        </el-table-column>
        <el-table-column prop="coop" label="任务类型" width="140" :filters="[
          { text: '项目任务', value: '项目任务' },
          { text: '个人任务', value: '个人任务' },
        ]" :filter-method="filterCoop">
        </el-table-column>
        <el-table-column prop="important_level" label="重要程度" width="130" :filters="[
          { text: '非常重要', value: '非常重要' },
          { text: '重要', value: '重要' },
          { text: '普通', value: '普通' },
          { text: '不重要', value: '不重要' },
        ]" :filter-method="filterEmer">
        </el-table-column>
        <el-table-column>

        </el-table-column>
      </el-table>
      <el-pagination @current-change="handleCurrentChange" v-model:current-page="currentPage" layout="prev, pager, next"
        :total="100" style="margin-top: 20px; margin-left: 220px; float: left">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import http from '@/http'
export default {
  name: 'MyTeam',
  data() {
    return {
      tasks: [],
      uid: '',
      tableData: [],
      currentPage: 1,
      dialogVisible: false,
      form: {
        title: '',
        intro: '',
        executorStuid: '',
        startdate: '',
        enddate: '',
        importanceLevel: '',
        interfaceStuid: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { max: 10, message: '不能超过10位', trigger: 'blur' },
        ],
        intro: [{ max: 30, message: '不能超过30位', trigger: 'blur' }],
        startdate: [{ required: true, message: '请选择日期', trigger: 'blur' }],
        enddate: [{ required: true, message: '请选择日期', trigger: 'blur' }],
        important_level: [
          {
            required: true,
            message: '请选择重要程度',
            trigger: 'blur',
          },
        ],
      },
    }
  },
  methods: {
    check() {
      if (!this.uid) this.$router.push('/login')
    },
    filterDone(value, row) {
      return row.done === value
    },
    filterCoop(value, row) {
      return row.coop === value
    },
    filterEmer(value, row) {
      return row.emer === value
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.load()
    },
    openDetails(row) {
      this.$router.push({
        path: '/task',
        query: {
          taskId: row.tid,
        },
      })
    },
    load() {
      http
        .get('/task/load', {
          params: {
            pageNum: this.currentPage,
            pageSize: 9,
            stuid: this.stuid,
          },
        })
        .then((res) => {
          this.tableData = res.data.data.records
          console.log(this.tableData)
          this.tableData.filter((item) => {
            if (item.done === 0) item.done = '未完成'
            if (item.done === 1) item.done = '已完成'
            if (item.done === 2) item.done = '待审核'
            if (item.done === 3) item.done = '已终止'
            if (item.coop === 0) item.coop = '个人任务'
            if (item.coop === 1) item.coop = '项目任务'
            if (item.emer === 0) item.emer = '非常重要'
            if (item.emer === 1) item.emer = '重要'
            if (item.emer === 2) item.emer = '普通'
            if (item.emer === 3) item.emer = '不重要'
          })
          function formatDate(dateString) {
            const date = new Date(dateString)
            const year = date.getFullYear()
            const month = String(date.getMonth() + 1).padStart(2, '0')
            const day = String(date.getDate()).padStart(2, '0')
            const hour = String(date.getHours()).padStart(2, '0')
            const minute = String(date.getMinutes()).padStart(2, '0')
            const second = String(date.getSeconds()).padStart(2, '0')
            return `${year}-${month}-${day} ${hour}:${minute}:${second}`
          }
          // 更改 startdate 和 enddate 的格式
          this.tableData.forEach((item) => {
            item.startdate = formatDate(item.startdate)
            item.enddate = formatDate(item.enddate)
          })
        })
    },
    add() {
      this.dialogVisible = true
      this.form = {}
    },
    save(formName) {
      function formatDate(dateString) {
            const date = new Date(dateString)
            const year = date.getFullYear()
            const month = String(date.getMonth() + 1).padStart(2, '0')
            const day = String(date.getDate()).padStart(2, '0')
            const hour = String(date.getHours()).padStart(2, '0')
            const minute = String(date.getMinutes()).padStart(2, '0')
            const second = String(date.getSeconds()).padStart(2, '0')
            return `${year}-${month}-${day} ${hour}:${minute}:${second}`
          }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.form.executorStuid = sessionStorage.getItem('stuid')
          this.form.startdate = formatDate(this.form.startdate)
          this.form.enddate = formatDate(this.form.enddate)
          http.post('/task/addIndp', this.form).then((res) => {
            if (res.data.code === '0') {
              this.$message({
                type: 'success',
                message: '添加成功！',
              })
              this.load()
              this.dialogVisible = false
            } else {
              this.$message({
                type: 'error',
                message: res.data.msg,
              })
            }
          })
        } else {
          alert('请检查！！')
          return false
        }
      })
    },
  },
  created() {
    this.stuid = sessionStorage.getItem('stuid')

    // this.check()
    this.load()
  },
}
</script>

<style scoped>
.main {
  position: relative;
  padding-top: 50px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  text-align: center;
  vertical-align: middle;
  padding: 10px;
}

th {
  background-color: #e4ecf3;
  color: black;
  font-weight: bold;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

tr:hover {
  background-color: #ddd;
}

.newTeamButton {
  position: absolute;
  width: 150px;
  height: 40px;
  background-color: #b70031 !important;
  line-height: 40px !important;
  font-size: 20px;
  font-weight: 600;
  top: 0px;
  right: 20px;
  padding: 0 10px;
}

.newTeamButton:hover {
  border: 1px solid #b70031 !important;
}

.dialog-footer {
  height: 40px;
  align-items: right;
}

.confirmButton {
  height: 40px;
  margin-left: 20px !important;
  padding: 0 20px;
  line-height: 40px !important;
  border: 1px solid #b70031 !important;
  border-radius: 5px !important;
}

.finishTag {
  width: 60px;
  height: 30px;
  background-color: #b70031;
  color: white;
  line-height: 30px;
  font-size: 12px;
  border-radius: 5px;
  border: 0;
}
</style>
