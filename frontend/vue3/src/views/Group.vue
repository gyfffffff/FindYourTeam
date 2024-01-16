<template>
  <div>
    <el-row>
      <el-col :span="8">
        <div class="detailInfo">
          <div class="info">
            <p class="infoItem">
              <span class="label">团队名称：</span>
              <span class="value">{{ groupData.groupName }}</span>
            </p>
            <p class="infoItem">
              <span class="label">团队Key：</span><span class="value">{{ groupData.groupKey }}</span>
            </p>
            <p class="infoItem">
              <span class="label">角色：</span>
              <span v-if="groupData.stuid === this.stuid" class="value">团队负责人</span>
              <span v-if="groupData.stuid !== this.stuid" class="value">团队成员</span>
            </p>
            <p class="infoItem">
              <span class="label">关联项目：</span><a class="link" @click="gotoProj">{{ projectData.title }}</a>
            </p>

            <div v-if="groupData.resp === 1" class="infoItem">
              负责人操作：
              <el-button @click="addMember()" size="medium" style="
                  margin-left: 10px;
                  border-color: #b70031aa;
                  background-color: #b7003177;
                  color: white;
                ">添加成员</el-button>
            </div>
            <p class="infoItem">
              团队聊天：<a class="link" @click="gotoChat()">加入群聊</a>
            </p>
          </div>
        </div>
      </el-col>
      <el-col :span="16">
        <!--  团队成员展示部分  -->
        <div style="
            height: 600px;
            margin: 0 20px;
            margin-top: 20px;
            background-color: white;
            border-radius: 10px;
          ">
          <div style="
              padding-top: 10px;
              color: #606266;
              font-size: 23px;
              text-align: center;
            ">
            团队成员
          </div>
          <!--      表格展示-->
          <div style="padding: 20px">
            <el-table class="list" :data="tableData" border stripe style="width: 100%">
              <el-table-column prop="stuid" label="用户学/工号" width="250">{{ this.stuid }}
              </el-table-column>
              <el-table-column prop="name" label="姓名" width="250">{{ this.name }}
              </el-table-column>
              <el-table-column prop="role" label="角色" sortable>
              </el-table-column>
            </el-table>
            <!--        分页-->
            <el-pagination @current-change="handleCurrentChange" v-model:current-page="currentPage"
              layout="prev, pager, next" :total="100" style="margin-top: 20px; margin-left: 310px; float: left">
            </el-pagination>
          </div>
          <!--      新增弹窗-->
          <el-dialog title="添加成员" v-model:visible="dialogVisible" width="30%">
            <el-form ref="form" :model="form" status-icon :rules="rules" label-width="120px">
              <el-form-item label="用户学/工号" prop="stuid">
                <el-input v-model="form.stuid" style="width: 80%"></el-input>
              </el-form-item>
            </el-form>
            <template v-slot:footer>
              <div class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="save('form')" class="confirmButton">确 定</el-button>
              </div>
            </template>
          </el-dialog>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// import request from "@/utils/request";
import http from '@/http'
export default {
  name: 'Group',
  data() {
    return {
      uid: '',
      name: '',
      stuid: '',
      groupKey: '',
      groupData: [],
      projectData: [],
      currentPage: 1,
      tableData: [],
      dialogVisible: false,
      form: {
        stuid: '',
        resp: '',
        gid: '',
        pid: '',
        groupName: '',
      },
      rules: {
        uid: [
          {
            required: true,
            message: '请输入用户的学/工号',
            trigger: 'blur',
          },
        ],
      },
    }
  },
  created() {
    this.name = sessionStorage.getItem('name')
    this.stuid = sessionStorage.getItem('stuid')
    // this.check();
    this.getParams()
    this.load()
  },
  methods: {
    check() {
      if (!this.user) this.$router.push('/login')
    },
    getParams() {
      this.groupKey = this.$route.query.groupKey
    },
    load() {
      http
        .get('/group/bykey', { params: { key: this.groupKey } })
        .then((res) => {
          this.groupData = res.data.data
          if (this.groupData.pid == null) {
            alert('请先新建一个项目并关联该团队')
            return 
          }
          http
            .get('/project/byid', {
              params: { pid: this.groupData.pid },
            })
            .then((res) => {
              console.log(120, res)
              if (res.data.code == -1) {
                alert('请先新建一个项目并关联该团队')
              }
              this.projectData = res.data.data
              http
                .get('/group/bygid', {
                  params: {
                    pageNum: this.currentPage,
                    pageSize: 9,
                    gid: this.groupData.gid,
                  },
                })
                .then((res) => {
                  console.log(125, res)
                  this.tableData = res.data.data.records
                  this.tableData.filter((item) => {
                    if (item.stuid === this.stuid) {
                      item['role'] = '团队负责人'
                    } else {
                      item['role'] = '团队成员'
                    }
                    http
                      .get('user/byid', {
                        params: { stuid: item.stuid },
                      })
                      .then((res) => {
                        item['name'] = res.data.name
                        item['company'] = res.data.company
                      })
                  })
                })
            })
        })
    },
    gotoProj() {
      this.$router.push({
        path: '/project',
        query: {
          projectId: this.projectData.pid,
        },
      })
    },
    gotoChat() {
      this.$router.push({
        path: '/chat',
        query: {
          groupKey: this.groupKey,
        },
      })
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.load()
    },
    addMember() {
      this.dialogVisible = true
      this.form = {}
    },
    save(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.form.pid = this.groupData.pid

          this.form.resp = 0
          this.form.gid = this.groupData.gid
          this.form.groupName = this.groupData.groupName
          http.post('/group/add', this.form).then((res) => {
            console.log(175, this.form)
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
          alert('该成员不存在或者该成员已在当前团队里')
          return false
        }
      })
    },
  },
}
</script>

<style scoped>
.detailInfo {
  height: 540px;
  background-color: white;
  border-radius: 20px;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 20px;
}

.info {
  height: 100%;
  padding: 40px;
}

.infoItem {
  margin-bottom: 40px;
}

.link {
  color: #409eff;
  text-decoration: underline;
  font-size: 20px;
}

.link:hover {
  cursor: pointer;
}

.label {
  font-size: 20px;
  color: #b70031;
}

.value {
  font-size: 20px;
  color: #888;
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
