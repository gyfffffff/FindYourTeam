<template>
  <div class="main">
    <el-button type="primary" @click="dialogFormVisible = true" class="newTeamButton">新建团队</el-button>
    <el-dialog title="新增团队" v-model="dialogFormVisible" width="30%">
      <el-form ref="form" :model="form" status-icon :rules="rules" label-width="120px">
        <el-form-item label="团队名称" prop="groupName">
          <el-input v-model="form.groupName" style="width: 80%"></el-input>
        </el-form-item>
      </el-form>
      <template v-slot:footer>
        <div class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="save('form')" class="confirmButton">确 定</el-button>
        </div>
      </template>
    </el-dialog>
    <div style="width: 100%; padding: 20px;  border: 2px solid #dfdfdf; border-radius: 12px;">
      <el-table :data="tableData" border stripe style="width: 100%">
        <el-table-column prop="gid" label="团队ID"> </el-table-column>
        <el-table-column prop="groupName" label="团队名称"> </el-table-column>
        <el-table-column prop="role" label="角色">
        </el-table-column>
        <el-table-column label="操作" width="200">
          <template v-slot="scope">
            <el-button @click="openDetails(scope.row)" type="success" size="small"
              style="margin-left: 10px">查看</el-button>
            <el-button @click="gotoChat(scope.row)" type="success" size="small">团队群聊</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination @current-change="handleCurrentChange" v-model:current-page="currentPage" layout="prev, pager, next"
        :total="100" style="margin-top: 20px; margin-left: 220px; float: left">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import http from '@/http' // 引入新创建的http实例
export default {
  name: 'MyTeam',
  data() {
    return {
      teamArr: [],
      uid: '',
      tableData: [],
      currentPage: 1,
      form: {
        groupName: '',
        uid: '',
        resp: '',
      },
      dialogFormVisible: false,
      rules: {
        groupName: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { max: 10, message: '不能超过10位', trigger: 'blur' },
        ],
      },
    }
  },
  methods: {
    openDetails(row) {
      console.log(66, row)
      this.$router.push({
        path: '/group',
        query: {
          groupKey: row.groupKey,
        },
      })
    },
    gotoChat(row) {
      this.$router.push({
        path: '/chat',
        query: {
          groupKey: row.groupKey,
        },
      })
    },
    load() {
      http
        .get('/group/load', {
          params: {
            pageNum: this.currentPage,
            pageSize: 9,
            stuid: this.stuid,
          },
        })
        .then((res) => {
          // console.log(res)
          this.tableData = res.data.data.records
          this.tableData.filter((item) => {
            if (item.stuid === this.stuid) {
              item.role = '负责人'
            } else {
              item.role = '成员'
            }
          })
          this.teamArr = this.tableData.map((item) => {
            return {
              name: item.groupName,
              id: item.groupKey,
              proj: item.projectName,
              members: item.members,
            }
          })
        })
    },
    save(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.form.uid = this.uid
          this.form.resp = 1
          http.post('/group/add', this.form).then((res) => {
            // console.log(res)
            if (res.data.code === '0') {
              this.$message({
                type: 'success',
                message: '添加成功！',
              })
              this.load()
              this.dialogFormVisible = false
            } else {
              this.$message({
                type: 'error',
                message: res.msg,
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
  table-layout: fixed;
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
</style>
