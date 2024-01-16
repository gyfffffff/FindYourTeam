<template>
    <div>
      <Header></Header>
      <el-row>
        <el-col :span="8">
          <div style="
              height: 627px;
              margin-left: 20px;
              margin-top: 10px;
              margin-right: 20px;
              background-color: floralwhite;
            ">
            <!-- <div style="margin-left: 30px; padding-top: 40px; font-size: large">
              <p class="infoItem">任务名称：{{ taskData.title }}</p>
              <p class="infoItem">任务简介：{{ taskData.intro }}</p>
              <p class="infoItem">创建时间：{{ taskData.startdate }}</p>
              <p class="infoItem">截止时间：{{ taskData.enddate }}</p>
              <p class="infoItem">状态：{{ taskData.done }}</p>
              <p class="infoItem">
                重要程度：
                <span>{{ taskData.importanceLevel }}</span>
              </p>
              <p class="infoItem">
                所属项目：<a class="link" @click="gotoProj">{{
                  projectData.title
                }}</a>
              </p>
              <div v-if="taskData.interfaceStuid === stuid">
                <p class="infoItem">
                  操作：
                  <el-button @click="doneTask" size="medium" type="success" style="margin-left: 10px">完成任务</el-button>
                  <el-button @click="endTask" size="medium" type="success" style="margin-left: 20px">终止任务</el-button>
                </p>
              </div>
            </div> -->
          </div>
        </el-col>
        <el-col :span="16">
          <!--    右边-->
          <div style="
              height: 627px;
              margin: 0 20px;
              margin-top: 10px;
              background-color: white;
            ">
            <!-- <div v-if="this.stuid !== this.taskData.executorStuid
              " style="
                color: #606266;
                font-size: 30px;
                text-align: center;
                margin-top: 250px;
              ">
              您没有查看权限！
            </div> -->
            <!-- <div v-else> -->
              <!-- <div class="uploadBox">
                <el-upload class="upload-demo" drag :action="'/api/file/upload/' + this.taskId" multiple>
                  <i class="el-icon-upload"></i>
                  <div class="el-upload__text">
                    将文件拖到此处，或<em>点击上传</em>
                  </div>
                </el-upload>
              </div> -->
  
              <!-- <div class="viewer">
                <div style="margin-top: 20px; text-align: center">
                  <a class="a" @click="gotoFileList">查看已上传的文件列表</a>
                </div>
                <div v-if="this.taskData.coop === 1 &&
                  this.user.uid === this.taskData.uid
                  " style="margin-top: 50px; margin-left: 480px">
                  <el-button @click="submitTask" size="medium" type="success">提交任务</el-button>
                </div>
                <div style="margin-top: 40px; text-align: center">
                  <a class="a" @click="gotoComment">查看审批记录</a>
                </div>
                <div v-if="this.taskData.coop === 1 && this.groupData.resp === 1"
                  style="margin-top: 30px; text-align: center">
                  项目负责人操作：
                  <el-button @click="approve" size="medium" type="success">审批任务</el-button>
                </div>
              </div> -->
            <!-- </div> -->
            <!--      审批弹窗-->
            <!-- <el-dialog title="审批任务" v-model:visible="dialogVisible" width="30%">
              <el-form ref="form" :model="form" status-icon :rules="rules" label-width="120px">
                <el-form-item label="审批操作" prop="pass">
                  <el-radio-group v-model="form.pass">
                    <el-radio label="通过"></el-radio>
                    <el-radio label="退回"></el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="留言" prop="comment">
                  <el-input v-model="form.comment" style="width: 80%"></el-input>
                </el-form-item>
              </el-form>
              <template v-slot:footer>
                <span class="dialog-footer">
                  <el-button @click="dialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="save('form')">确 定</el-button>
                </span>
              </template>
            </el-dialog> -->
          </div>
        </el-col>
      </el-row>
  
      <Footer></Footer>
    </div>
  </template>
  
  <script>
  import Header from '@/components/Header.vue'
  import Footer from '@/components/Footer.vue'
  import http from '@/http'
  
  export default {
    name: 'Task',
    components: { Footer, Header },
    data() {
      return {
        stuid: '',
        taskId: '',
        taskData: [],
        projectData: [],
        groupData: [],
        dialogVisible: false,
        form: {
          comment: '',
          tid: '',
          pass: '',
        },
        rules: {
          pass: [{ required: true, message: '请审批', trigger: 'blur' }],
          comment: [{ max: 30, message: '不能超过30位', trigger: 'blur' }],
        },
      }
    },
    created() {
      this.stuid = sessionStorage.getItem('stuid')
      // this.check();
      this.getParams()
      this.getTask()
    },
    methods: {
      check() {
        if (!this.user) this.$router.push('/login')
      },
      getParams() {
        this.taskId = this.$route.query.taskId
      },
      getTask() {
        http.get('/task/byid?tid=' + this.taskId).then((res) => {
          this.taskData = res.data.data
          // console.log(143, this.taskData)
          if (this.taskData.done === 0) this.taskData.done = '未完成'
          if (this.taskData.done === 1) this.taskData.done = '已完成'
          if (this.taskData.done === 2) this.taskData.done = '待审核'
          if (this.taskData.done === 3) this.taskData.done = '已终止'
          http.get('/project/byid?pid=' + this.taskData.pid).then((res) => {
            this.projectData = res.data.data
            http
              .get('/group/bypid?pid=' + this.taskData.pid + '&stuid=' + this.stuid)
              .then((res) => {
                this.groupData = res.data.data
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
      doneTask() {
        this.taskData.done = 1
        http.put('task/done', this.taskData).then((res) => {
          if (res.code === '0') {
            this.$message({
              type: 'success',
              message: '操作成功！',
            })
            this.getTask()
          }
        })
      },
      endTask() {
        this.taskData.done = 3
        http.put('task/end', this.taskData).then((res) => {
          if (res.code === '0') {
            this.$message({
              type: 'success',
              message: '操作成功！',
            })
            this.getTask()
          }
        })
      },
      submitTask() {
        this.taskData.done = 2
        http.put('task/submit', this.taskData).then((res) => {
          if (res.code === '0') {
            this.$message({
              type: 'success',
              message: '操作成功！',
            })
            this.getTask()
          }
        })
      },
      //项目负责人提交审批
      approve() {
        this.dialogVisible = true
        this.form = {}
      },
      save(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.form.tid = this.taskId
            if (this.form.pass === '打回') this.form.pass = 0
            if (this.form.pass === '通过') this.form.pass = 1
            if (this.form.pass === 0) {
              this.taskData.done = 0
              http.put('task/undo', this.taskData).then((res) => {
                this.getTask()
              })
            }
            if (this.form.pass === 1) {
              this.taskData.done = 1
              http.put('task/done', this.taskData).then((res) => {
                this.getTask()
              })
            }
            http.post('/comment/add', this.form).then((res) => {
              if (res.data.code === '0') {
                this.$message({
                  type: 'success',
                  message: '审批成功！',
                })
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
      //已提交的文件页
      gotoFileList() {
        this.$router.push({
          path: '/filelist',
          query: {
            taskId: this.taskId,
          },
        })
      },
      //审批记录页
      gotoComment() {
        this.$router.push({
          path: '/comment',
          query: {
            taskId: this.taskId,
          },
        })
      },
    },
  }
  </script>
  
  <style scoped>
  .infoItem {
    margin-bottom: 40px;
  }
  
  .upload-demo {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
  }
  
  .uploadBox {
    width: 100%;
    padding-top: 100px;
    position: relative;
  }
  
  .link {
    color: limegreen;
    text-decoration: underline;
  }
  
  .link:hover {
    cursor: pointer;
  }
  
  .a:hover {
    cursor: pointer;
    color: #409eff;
    text-decoration: underline;
  }
  
  .viewer {
    margin-top: 250px;
  }
  </style>
  